package co.claro.adapter.consultasaldo.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * adapter-consulta-saldo
 * PrimaryData.java
 * Oct 31, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */
@Getter
@Setter
public class PrimaryData implements Serializable {

    /** Serial version UID */
    private static final long serialVersionUID = -8478984558316046120L;

    private String businessKey;
    private String businessKeyType;

    public PrimaryData() {}

    @Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PrimaryData [businessKey=");
		builder.append(businessKey);
		builder.append(", businessKeyType=");
		builder.append(businessKeyType);
		builder.append("]");
		return builder.toString();
	}
}
