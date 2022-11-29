package co.claro.adapter.consultasaldo.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * adapter-consulta-saldo
 * RequestPayload.java
 * Oct 31, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */
@Getter
@Setter
public class RequestPayload implements Serializable {
	/** Serial version */
	private static final long serialVersionUID = 7868310611900741033L;

    private PrimaryData primaryData;  
    private AdditionalData additionalData;
    
    public RequestPayload() {
		super();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RequestPayload [primaryData=");
		builder.append(primaryData);
		builder.append(", additionalData=");
		builder.append(additionalData);
		builder.append("]");
		return builder.toString();
	}
}
