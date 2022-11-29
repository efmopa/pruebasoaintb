package co.claro.adapter.consultasaldo.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * adapter-consulta-saldo
 * AdditionalData.java
 * Oct 31, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */
@Getter
@Setter
public class AdditionalData implements Serializable {
	/** Serial version */
	private static final long serialVersionUID = -6445866824539143515L;

	private String accountNumber; 
	private String uniqueRef;
	private String sessionID; 
	private String ledgerBalance;
	private String netBalance;
	private String companyCode;
	private String rrn; 


	public AdditionalData() {
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AdditionalData [companyCode=");
		builder.append(companyCode);
		builder.append(", sessionID=");
		builder.append(sessionID);
		builder.append(", uniqueRef=");
		builder.append(uniqueRef);
		builder.append(", rrn=");
		builder.append(rrn);
		builder.append(", ledgerBalance=");
		builder.append(ledgerBalance);
		builder.append(", netBalance=");
		builder.append(netBalance);
		builder.append("]");
		return builder.toString();
	}
}