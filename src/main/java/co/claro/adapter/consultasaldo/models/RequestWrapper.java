package co.claro.adapter.consultasaldo.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * adapter-consulta-saldo
 * RequestWrapper.java
 * Oct 31, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */
@Getter
@Setter
public class RequestWrapper implements Serializable {
	/** Serial version UID */
	private static final long serialVersionUID = 6464903968440189419L;

	private RequestHeader header;
	private RequestPayload requestPayload;

	public RequestWrapper() {
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RequestWrapper [header=");
		builder.append(header);
		builder.append(", requestPayload=");
		builder.append(requestPayload);
		builder.append("]");
		return builder.toString();
	}
}
