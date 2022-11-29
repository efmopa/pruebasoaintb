package co.claro.adapter.consultasaldo.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


/**
 * adapter-consulta-saldo
 * Item.java
 * Oct 31, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */
@Getter
@Setter
public class Item implements Serializable {

	private static final long serialVersionUID = 7273566685172729424L;
	
	public String ehfRef;
	public String ehfDesc;
 

	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Item [ehfRef=");
		builder.append(ehfRef);
		builder.append(", ehfDesc=");
		builder.append(ehfDesc);
		builder.append("]");
		return builder.toString();
	}

	public Item(String ehfRef, String ehfDesc) {
		super();
		this.ehfRef = ehfRef;
		this.ehfDesc = ehfDesc;
	}
}
