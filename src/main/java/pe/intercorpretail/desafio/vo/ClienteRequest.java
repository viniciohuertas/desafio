package pe.intercorpretail.desafio.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

/**
 * Nombre Clase: ClienteRequest.java
 * <br>
 * Fecha Creaci&oacute;n:	 2019/03/20
 * <br>
 * Fecha Modificaci&oacute;n:
 * <br>
 * Clase Request para cliente
 * <br>
 * Cambios Importantes:
 * <br>
 * @author  Vinicio Huertas
 * @version 1.0.0
 * @since jdk 1.8
**/

@Data
public class ClienteRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "no debe estar vacío")
    private String nombre;
	@NotBlank(message = "no debe estar vacío")
    private String apellido;
	@DateTimeFormat(iso = ISO.DATE)
	@Temporal(TemporalType.DATE)
	@NotBlank(message = "no debe estar vacío")
	private Date fechanacimiento;

}
