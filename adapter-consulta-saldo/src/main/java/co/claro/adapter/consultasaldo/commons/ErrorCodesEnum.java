package co.claro.adapter.consultasaldo.commons;

/**
 * adapter-consulta-saldo
 * ErrorCodesEnum.java
 * Oct 31, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */
public enum ErrorCodesEnum {
	
	OSP00("Success","00"),
	OSP06("General Error","06"),
	OSP63("Security violation","63"),
	OSP92("Exception","92"),
	OSP94("Duplicate ref","94"),

	OSP1000("Caller authentication error","OSP-1000"),
	OSP1002("Processed Successfully","OSP-1002"),
	OSP1004("System unavailable","OSP-1004"),
	OSP1013("Duplicate message identifier","OSP-1013"),
	OSP1014("Message validation failed","OSP-1014"),
	OSP1016("Request received and acknowledged","OSP-1016"),
	OSP1024("Route not configured in HCV","OSP-1024"),
	OSP1999("Unknown error","OSP-1999"),
	OSP2013("Generic business error","OSP-2013"),
	OSP2027("Target system not able to reply. Reconcilation advised","OSP-2027"),
	OSP2047("Backend not available","OSP-2047"),
	OSP2138("No condition match found. Request rejected","OSP-2138");

	private String message;
	private String code;
	/**
	 * @param description
	 * @param code
	 */
	private ErrorCodesEnum(String message, String code) {
		this.message = message;
		this.code = code;
	}
	/**
	 * @return the description
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
}
