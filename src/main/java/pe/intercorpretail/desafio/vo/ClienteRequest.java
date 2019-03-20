package pe.intercorpretail.desafio.vo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

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

	@NotBlank(message = "Debe ingresar su nombre")
    private String nombre;
	@NotBlank(message = "Debe ingresar su apellido")
    private String apellido;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date fechanacimiento;

}
