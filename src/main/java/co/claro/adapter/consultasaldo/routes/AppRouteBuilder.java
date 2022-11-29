package co.claro.adapter.consultasaldo.routes;

import static co.claro.adapter.consultasaldo.commons.CustomExchangeProperties.ORIGINAL_MESSAGE_PROPERTY;
import static co.claro.adapter.consultasaldo.commons.CustomExchangeProperties.SERVICE_SECURITY_DEFINITION_BASIC_VALUE;
import static co.claro.adapter.consultasaldo.commons.HTTPCommonHeadersEnum.CONTENT_TYPE;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

import co.claro.adapter.consultasaldo.models.RequestWrapper;
import co.claro.adapter.consultasaldo.models.ResponseWrapper;


/**
 * adapter-consulta-saldo
 * AppRouteBuilder.java
 * Oct 31, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * @implNote This class configures all routes to dispatch events from rest services
 * 
 */
@Component
public class AppRouteBuilder extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		
        onException(Exception.class)
	        .handled(true)
			.removeHeaders("*", "messageID")
	        .process("errorHandlingProcessor");
    
		rest("/be/consultaSaldo")
            .securityDefinitions()
                .basicAuth(SERVICE_SECURITY_DEFINITION_BASIC_VALUE)
            .end()
			.post()
			.description("Atomic REST Service EFTS")
				.consumes(APPLICATION_JSON_VALUE)
				.type(RequestWrapper.class)
	            .description("POST Endpoint 2 Send a Message to Consulta Saldo Orchestration Interface")
	            	.param().name("body").required(true).type(RestParamType.body).endParam()
	                .param().name("Authorization").description("The Authorization:Basic User/Password ").type(RestParamType.header).endParam()
	                .param().name("messageID").description("The messageID ").type(RestParamType.header).endParam()
	            .security(SERVICE_SECURITY_DEFINITION_BASIC_VALUE)
				.produces(APPLICATION_JSON_VALUE)
				.outType(ResponseWrapper.class)
				.responseMessage()
					.code(OK.value())
				.endResponseMessage()
				.to("seda:dispatchRequest");

		/* Routes Configuration */
		from("seda:dispatchRequest").routeId("co.claro.adapter.request.dispatchRequest")
			.noStreamCaching().noMessageHistory().noTracing()
			.to("direct:begin")
			.to("direct:callAtomic");

		from("direct:begin").routeId("co.claro.adapter.request.begin")
			.noStreamCaching().noMessageHistory().noTracing()
			.log(LoggingLevel.INFO, "Consulta Saldo Adapter::REST Request::MessageID [${headers.messageID}]::Payload [${body}]")
			.setProperty(ORIGINAL_MESSAGE_PROPERTY, simple("${body})"))
			.process("headersSetterProcessor")
			.marshal().json(JsonLibrary.Gson, RequestWrapper.class)
			.to("json-validator:classpath:RequestPayloadSchema.json?contentCache=false");
	
		from("direct:callAtomic").routeId("co.claro.adapter.request.callAtomic")
			.noStreamCaching().noMessageHistory().noTracing()
			.setHeader(ACCEPT, constant(APPLICATION_JSON_VALUE))
			.setHeader(CONTENT_TYPE.getName(), constant(APPLICATION_JSON_VALUE))
			// .setBody(simple("${exchangeProperty.originalMessage}"))
			// .marshal().json(JsonLibrary.Gson, RequestWrapper.class)
			.log(LoggingLevel.INFO, "Consulta Saldo Adapter::Request Atomic::MessageID [${headers.messageID}]::Payload [${body}]")
			.enrich().simple("{{atomic.uri}}").id("callAtomicService")
			.removeHeaders("*", "messageID")
			.unmarshal().json(JsonLibrary.Gson, ResponseWrapper.class)
			.log(LoggingLevel.INFO, "Consulta Saldo Adapter::Response Atomic::MessageID [${headers.messageID}]::Payload [${body}]");
		
	}
}