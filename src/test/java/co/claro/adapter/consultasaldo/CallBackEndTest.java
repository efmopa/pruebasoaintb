package co.claro.adapter.consultasaldo;

import static co.claro.adapter.consultasaldo.commons.CustomExchangeProperties.ORIGINAL_MESSAGE_PROPERTY;
import static co.claro.adapter.consultasaldo.constant.Examples.REQUEST_EXAMPLE;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.camel.Exchange;
import org.apache.camel.builder.ExchangeBuilder;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.UseAdviceWith;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import co.claro.adapter.consultasaldo.component.CamelRouterTestSupport;
import co.claro.adapter.consultasaldo.models.RequestWrapper;

/**
 * adapter-consulta-saldo
 * CallBackEndTest.java
 * Nov 3, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */
@CamelSpringBootTest
@UseAdviceWith
@SpringBootTest(classes = Application.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class CallBackEndTest extends CamelRouterTestSupport {

	private static final Logger logger = LoggerFactory.getLogger(CallBackEndTest.class);


	/**
	 * Test to validate channel authentication against vault credentials - success
	 * 
	 * @throws Exception
	 */
	@Test
	public void callServiceSuccessTest() throws Exception {

		logger.info("callServiceSuccessTest");

		Long messageID = System.currentTimeMillis();
		
		String bodyrequest = REQUEST_EXAMPLE.replaceAll("KCB_MESSAGEID", messageID.toString());
		
		RequestWrapper requestWrapper = CallBackEndTest.payloadObjectMapper.readValue(bodyrequest, RequestWrapper.class);
		
		// Sends the message with valid credentials header
		Exchange exchange = this.getProducerTemplate().send("direct:callAtomic",
				ExchangeBuilder.anExchange(this.getContext())
				.withBody(bodyrequest.getBytes())
				.withHeader("messageID", messageID.toString())
				.withProperty(ORIGINAL_MESSAGE_PROPERTY, requestWrapper).build());
		
//		ResponseWrapper responseWrapper = exchange.getIn().getBody(ResponseWrapper.class);

		// Case 1: no DataValidationException
		assertNotNull(exchange);
	}
}