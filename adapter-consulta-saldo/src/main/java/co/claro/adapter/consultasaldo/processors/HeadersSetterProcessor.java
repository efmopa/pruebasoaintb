package co.claro.adapter.consultasaldo.processors;

import static co.claro.adapter.consultasaldo.commons.CustomExchangeProperties.CALLER_IP;
import static co.claro.adapter.consultasaldo.commons.CustomExchangeProperties.EMPTY;
import static co.claro.adapter.consultasaldo.commons.CustomExchangeProperties.MESSAGE_ID_HEADER;
import static co.claro.adapter.consultasaldo.commons.CustomExchangeProperties.ORIGINAL_REQUEST;
import static co.claro.adapter.consultasaldo.commons.HTTPCommonHeadersEnum.AUTHORIZATION;

import java.util.Base64;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import co.claro.adapter.consultasaldo.exceptions.SystemAuthenticationException;
import co.claro.adapter.consultasaldo.models.RequestWrapper;
import lombok.extern.log4j.Log4j2;


/**
 * adapter-consulta-saldo
 * HeadersSetterProcessor.java
 * Nov 2, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */
@Log4j2
@Component
public class HeadersSetterProcessor implements Processor {

	private static final String USER = "claro-206";
	private static final String PASSWORD = "claro@123";
	private static final String CALLER_IP_MESSAGE = "CALLER_IP_ADDRESS is ::[{}]";

	@Override
	public void process(Exchange exchange) throws Exception {

		//Get Caller IP
		HttpServletRequest request = exchange.getIn().getBody(HttpServletRequest.class);
		RequestWrapper requestWrapper = exchange.getIn().getBody(RequestWrapper.class);
        exchange.setProperty(ORIGINAL_REQUEST, requestWrapper);

		String ipRemote = Objects.isNull(request) ? EMPTY : request.getRemoteAddr();

		log.info(CALLER_IP_MESSAGE, ipRemote);

		/*************************************************************************************************/
		String authorizationHeader = Optional.ofNullable(exchange.getIn().getHeader(AUTHORIZATION.getName(), String.class)).orElse(EMPTY).trim();
		
		if(EMPTY.equals(authorizationHeader)) throw new SystemAuthenticationException();
			
		String[] decodedCredentials = new String(Base64.getDecoder().decode(authorizationHeader.split(" ")[1])).split(":");

		if (!USER.equals(decodedCredentials[0]) || !PASSWORD.equals(decodedCredentials[1])) {
			throw new SystemAuthenticationException();
		}
		/*************************************************************************************************/

		exchange.getIn().setHeader(CALLER_IP, ipRemote);
		exchange.getIn().setHeader(MESSAGE_ID_HEADER, requestWrapper.getHeader().getMessageID());
	}
}
