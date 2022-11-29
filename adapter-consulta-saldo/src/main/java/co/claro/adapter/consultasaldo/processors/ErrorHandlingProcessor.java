package co.claro.adapter.consultasaldo.processors;

import static co.claro.adapter.consultasaldo.commons.CustomExchangeProperties.ORIGINAL_REQUEST;
import static co.claro.adapter.consultasaldo.commons.ErrorCodesEnum.OSP1000;
import static co.claro.adapter.consultasaldo.commons.ErrorCodesEnum.OSP1004;
import static co.claro.adapter.consultasaldo.commons.ErrorCodesEnum.OSP1014;
import static co.claro.adapter.consultasaldo.commons.ErrorCodesEnum.OSP1999;
import static co.claro.adapter.consultasaldo.commons.TransactionStatusEnum.FAILURE;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangeTimedOutException;
import org.apache.camel.Processor;
import org.apache.camel.ValidationException;
import org.apache.camel.component.jsonvalidator.JsonValidationException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import co.claro.adapter.consultasaldo.exceptions.DataValidationException;
import co.claro.adapter.consultasaldo.exceptions.SystemAuthenticationException;
import co.claro.adapter.consultasaldo.models.EhfInfo;
import co.claro.adapter.consultasaldo.models.Item;
import co.claro.adapter.consultasaldo.models.RequestWrapper;
import co.claro.adapter.consultasaldo.models.ResponseHeader;
import co.claro.adapter.consultasaldo.models.ResponsePayload;
import co.claro.adapter.consultasaldo.models.ResponseWrapper;
import lombok.extern.log4j.Log4j2;


/**
 * adapter-consulta-saldo
 * ErrorHandlingProcessor.java
 * Nov 2, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */
@Log4j2
@Component
public class ErrorHandlingProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
		
		log.info(exception.getClass());
        RequestWrapper originalRequest = exchange.getProperty(ORIGINAL_REQUEST, RequestWrapper.class);

		String routeCode = "";
		String routeName = "";
		String channelCode = "";
		String channelName = "";
		String messageID = "";
		String conversationID = "";
		
		ResponseWrapper responseWrapper = new ResponseWrapper();

		if (null != originalRequest) {
			ResponsePayload responsePayload = new ResponsePayload();
			responsePayload.setPrimaryData(originalRequest.getRequestPayload().getPrimaryData());
			responseWrapper.setResponsePayload(responsePayload);
			
			routeCode = originalRequest.getHeader().getRouteCode();
			routeName = originalRequest.getHeader().getRouteName();
			channelCode = originalRequest.getHeader().getChannelCode();
			channelName = originalRequest.getHeader().getChannelName();
			messageID = originalRequest.getHeader().getMessageID();
			conversationID = originalRequest.getHeader().getConversationID();
		}

		Item entry = null;
		List<Item> listofItems = new ArrayList<>();

		EhfInfo ehfInfo = new EhfInfo();

		if (exception instanceof DataValidationException ||
				exception instanceof UnrecognizedPropertyException ||
				exception instanceof ValidationException || 
				exception instanceof JsonValidationException) {

			entry = new Item();
			entry.setEhfDesc(OSP1014.getMessage());
			entry.setEhfRef(OSP1014.getCode());
			listofItems.add(entry);
		} else if (exception instanceof ExchangeTimedOutException ||
				exception instanceof SocketTimeoutException) {			
			entry = new Item();
			entry.setEhfDesc(OSP1004.getMessage());
			entry.setEhfRef(OSP1004.getCode());
			listofItems.add(entry);
		} else if (exception instanceof SystemAuthenticationException) {			
			entry = new Item();
			entry.setEhfDesc(OSP1000.getMessage());
			entry.setEhfRef(OSP1000.getCode());
			listofItems.add(entry);
		} else {
			entry = new Item();
			entry.setEhfDesc(OSP1999.getMessage());
			entry.setEhfRef(OSP1999.getCode());
			listofItems.add(entry);
		}
		
		ehfInfo.setItem(listofItems);
		ResponseHeader header = new ResponseHeader();
		header.setEhfInfo(ehfInfo);

		header.setMessageID(messageID);
		header.setConversationID(conversationID);
		header.setTargetSystemID("NotAvailable");
		header.setChannelCode(channelCode);
		header.setChannelName(channelName);
		header.setRouteCode(routeCode);
		header.setRouteName(routeName);
		header.setStatusCode(FAILURE.getCode());
		header.setStatusDescription(FAILURE.getDescription());

		responseWrapper.setHeader(header);
		exchange.getIn().setBody(responseWrapper, ResponseWrapper.class);
        exchange.getIn().setHeader(Exchange.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        
		// Logs all required information about the error
		log.error("Adapter::Error::MessageID [{}]::Exception [{}]",
				messageID,
				exception.getMessage(),
				exception.getCause() != null ? exception.getCause() : exception);
	}
}
