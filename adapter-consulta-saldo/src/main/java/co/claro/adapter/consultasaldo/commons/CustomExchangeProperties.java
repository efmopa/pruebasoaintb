package co.claro.adapter.consultasaldo.commons;

/**
 * adapter-consulta-saldo
 * CustomExchangeProperties.java
 * Oct 31, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */
public class CustomExchangeProperties {

    public CustomExchangeProperties() {
        throw new UnsupportedOperationException("Utility class.");
    }


    public static final String ORIGINAL_REQUEST = "originalRequest";
    public static final String URI = "GenericUri";
    public static final String SUCCESSFUL_RESPONSE = "successfulResponse";
    
    public static final String WWW_AUTHENTICATE_HEADER_VALUE = "Basic realm=\"Access to MS Adapter Layer\"";
    
    public static final String ORIGINAL_MESSAGE_PROPERTY = "originalMessage";
    public static final String UNMARSHALED_MESSAGE_PROPERTY = "unmarshaledMessage";
    public static final String SECURITY_VIOLATION_MESSAGE_VALUE = "SECURITY VIOLATION";
    public static final String CONVERSATION_ID_HEADER = "conversationID";
    public static final String MESSAGE_ID_HEADER = "messageID";
    public static final String RESULT_CODE_HEADER = "resultCode";
    public static final String ROUTE_CODE_HEADER = "routeCode";
    public static final String T24_ERROR_VALUE = "T24Error";
    public static final String SLASH = "/";
    public static final String EMPTY = "";

    public static final String CALLER_IP = "clientIPAddress";
    public static final String CHANNEL_CODE_HEADER = "channelCode";
    
    public static final String SERVICE_SECURITY_DEFINITION_BASIC_VALUE = "Basic";   
    public static final String SERVICE_AUTHORIZATION_HEADER_NAME = "Authorization";

    public static final String REMOVE_HEADERS_PATTERN = "*";
}