package co.claro.adapter.consultasaldo.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


/**
 * adapter-consulta-saldo
 * ResponseWrapper.java
 * Oct 31, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */
@Getter
@Setter
public class ResponseWrapper implements Serializable {
	/** Serial version UID */
	private static final long serialVersionUID = 3547641363206949943L;

	private ResponseHeader header;
	private ResponsePayload responsePayload;

	public ResponseWrapper() {
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResponseWrapper [header=");
		builder.append(header);
		builder.append(", responsePayload=");
		builder.append(responsePayload);
		builder.append("]");
		return builder.toString();
	}
}