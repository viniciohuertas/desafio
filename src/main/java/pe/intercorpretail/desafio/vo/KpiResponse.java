package pe.intercorpretail.desafio.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * Nombre Clase: KpiResponse.java
 * <br>
 * Fecha Creaci&oacute;n:	 2019/03/20
 * <br>
 * Fecha Modificaci&oacute;n:
 * <br>
 * Clase Responde para KpiCliente
 * <br>
 * Cambios Importantes:
 * <br>
 * @author  Vinicio Huertas
 * @version 1.0.0
 * @since jdk 1.8
**/

@Data
public class KpiResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double promedioEdad;
	private double desEstEdad;

}
