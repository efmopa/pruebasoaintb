package co.claro.adapter.consultasaldo.models;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Error Detail Model (for Error Handling Framework)
 * adapter-consulta-saldo
 * EhfInfo.java
 * Oct 31, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */
@Getter
@Setter
public class EhfInfo implements Serializable {
	/** Serial version UID */
	private static final long serialVersionUID = -8647643527147121819L;
	private List<Item> item;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EhfInfo [item=");
		builder.append(item);
		builder.append("]");
		return builder.toString();
	}
}