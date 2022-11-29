package co.claro.adapter.consultasaldo.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * adapter-consulta-saldo
 * ResponsePayload.java
 * Oct 31, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */
@Getter
@Setter
public class ResponsePayload implements Serializable {
	/** Serial version */
	private static final long serialVersionUID = 353652223581858774L;

	private PrimaryData primaryData; 
	private AdditionalData additionalData;

	public ResponsePayload() {
		super();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResponsePayload [primaryData=");
		builder.append(primaryData);
		builder.append(", additionalData=");
		builder.append(additionalData);
		builder.append("]");
		return builder.toString();
	}
}
