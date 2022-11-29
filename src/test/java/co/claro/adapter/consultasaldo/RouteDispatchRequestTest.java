package co.claro.adapter.consultasaldo;

import static co.claro.adapter.consultasaldo.constant.Examples.REQUEST_EXAMPLE;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.EndpointInject;
import org.apache.camel.component.mock.MockEndpoint;
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
 * adapter-consulta-saldo RequestHeadersValidationTest.java Nov 3, 2022
 *
 * @author avazquez | Claro
 * @version 1.0.0
 * 
 */
@CamelSpringBootTest
@UseAdviceWith
@SpringBootTest(classes = Application.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class RouteDispatchRequestTest extends CamelRouterTestSupport {

	@EndpointInject("mock:callAtomicCall")
	MockEndpoint mockCallAtomicCall;

	private static final Logger logger = LoggerFactory.getLogger(RouteDispatchRequestTest.class);
    
	/**
	 * Test to validate channel authentication against vault credentials - success
	 * 
	 * @throws Exception
	 */
	@Test
	public void requestSuccessTest() throws Exception {

		logger.info("-------------------requestSuccessTest--------------");

		Long messageID = System.currentTimeMillis();

		RequestWrapper request = CamelRouterTestSupport.payloadObjectMapper
				.readValue(REQUEST_EXAMPLE.replaceAll("KCB_MESSAGEID", messageID.toString()), RequestWrapper.class);

		Map<String, Object> headerCamel = new HashMap<String, Object>();

		headerCamel.put("messageID", messageID.toString());
		headerCamel.put("Content-Type", "application/json");
		headerCamel.put("Accept", "application/json");
		headerCamel.put("Authorization", "Basic Y2xhcm8tMjA2OmNsYXJvQDEyMw==");

		mockCallAtomicCall.expectedMessageCount(1);

		// sending data to route consumer
		String case1 = this.sendMessage("seda:dispatchRequest", request, headerCamel);

		mockCallAtomicCall.assertIsSatisfied();
		assertNotNull(case1);
		assertTrue(case1.contains("KCB_MESSAGEID"));
	}

	@Test
	public void requestNotHeaderTest() throws Exception {

		logger.info("-------------------requestNotHeaderTest--------------");

		Long messageID = System.currentTimeMillis();

		RequestWrapper request = RouteDispatchRequestTest.payloadObjectMapper
				.readValue(REQUEST_EXAMPLE.replaceAll("KCB_MESSAGEID", messageID.toString()), RequestWrapper.class);

		Map<String, Object> headerCamelH = new HashMap<String, Object>();

		headerCamelH.put("messageID", messageID.toString());

		mockCallAtomicCall.expectedMessageCount(0);

		String case1 = this.sendMessage("seda:dispatchRequest", request, headerCamelH);
		
		mockCallAtomicCall.assertIsSatisfied();

		// Case 1: no DataValidationException
		assertNotNull(case1);
		assertTrue(case1.contains("Caller authentication error"));
	}

}