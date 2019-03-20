package pe.intercorpretail.desafio.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * Nombre Clase: ClienteResponse.java
 * <br>
 * Fecha Creaci&oacute;n:	 2019/03/20
 * <br>
 * Fecha Modificaci&oacute;n:
 * <br>
 * Clase Response para cliente
 * <br>
 * Cambios Importantes:
 * <br>
 * @author  Vinicio Huertas
 * @version 1.0.0
 * @since jdk 1.8
**/

@Data
public class ClienteResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private String nombre;
    private String apellido;
    private Integer edad;
	private Date fechaNacimiento;
	private Date fechaProbMuerte;
	
	public ClienteResponse(String nombre, String apellido, Integer edad, Date fechaNacimiento) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.fechaNacimiento = fechaNacimiento;
	}

}
