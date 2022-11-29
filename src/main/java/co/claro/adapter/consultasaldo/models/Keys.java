package co.claro.adapter.consultasaldo.models;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


/**
 * adapter-consulta-saldo
 * Keys.java
 * Oct 31, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */
@Getter
@Setter
public class Keys implements Serializable {


	private static final long serialVersionUID = 1724083858859234133L;
	
	private List<StringBuilder> keys;
	
	public Keys(List<StringBuilder> keys) {
		super();
		this.keys = keys;
	}
	
	public Keys() {
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Keys [keys=");
		builder.append(keys);
		builder.append("]");
		return builder.toString();
	}

}
