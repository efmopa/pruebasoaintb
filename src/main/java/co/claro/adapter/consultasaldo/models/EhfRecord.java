package co.claro.adapter.consultasaldo.models;

import lombok.Data;

/**
 * adapter-consulta-saldo
 * EhfRecord.java
 * Oct 31, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */
@Data
public class EhfRecord {
    private String statusCode;
    private String statusDescription;
    private String businessDescription;
    private String ehfRef;
    private String ehfDesc;
}
