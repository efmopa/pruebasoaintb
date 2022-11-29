package co.claro.adapter.consultasaldo.models;

import java.util.List;
import lombok.Data;

/**
 * adapter-consulta-saldo
 * EhfResponse.java
 * Nov 1, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */
@Data
public class EhfResponse {

    private Boolean success;
    private String detail;
    private List<EhfObject> records;

}