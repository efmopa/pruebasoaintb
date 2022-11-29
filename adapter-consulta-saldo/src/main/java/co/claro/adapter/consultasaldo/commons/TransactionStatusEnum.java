package co.claro.adapter.consultasaldo.commons;

/**
 * adapter-consulta-saldo
 * TransactionStatusEnum.java
 * Oct 31, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * @implNote Only for response
 * 
 */
public enum TransactionStatusEnum {
	FAILURE("1", "Failure"), 
	SUCCESS("0", "Success");

	private String code;
	private String description;

	private TransactionStatusEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return this.code;
	}
	
	public String getDescription() {
		return this.description;
	}
}