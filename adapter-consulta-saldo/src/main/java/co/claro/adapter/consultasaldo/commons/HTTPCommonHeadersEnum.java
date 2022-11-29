package co.claro.adapter.consultasaldo.commons;


/**
 * adapter-consulta-saldo
 * HTTPCommonHeadersEnum.java
 * Oct 31, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 * @implNote Only for the most common headers related to this project
 * 
 */
public enum HTTPCommonHeadersEnum {
	ACCEPT("Accept"),
	CONTENT_TYPE("Content-Type"),
	AUTHORIZATION("Authorization"),
	WWW_AUTHENTICATE("WWW-Authenticate");
	
	private String name;
	
	private HTTPCommonHeadersEnum(String name) {
		this.name = name;
	};
	
	public String getName() {
		return name;
	}
}