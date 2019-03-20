package pe.intercorpretail.desafio.model;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * Nombre Clase: Cliente.java
 * <br>
 * Fecha Creaci&oacute;n:	 2019/03/20
 * <br>
 * Fecha Modificaci&oacute;n:
 * <br>
 * Clase @Entity de Cliente
 * <br>
 * Cambios Importantes:
 * <br>
 * @author  Vinicio Huertas
 * @version 1.0.0
 * @since jdk 1.8
**/

@Data
@Entity
public class Cliente implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank
    @Column(name = "nombre", nullable = false)
    private String nombre;
 
	@NotBlank
    @Column(name = "apellido", nullable = true)
    private String apellido;
 
    @Column(name = "edad")
    private Integer edad;
    
    @Column(name = "fechanacimiento", nullable = false)
    @Temporal(TemporalType.DATE)
	private Date fechanacimiento;
    
    public Cliente() {
//		super();
	}

	public Cliente(@NotBlank String nombre, @NotBlank String apellido, @NotBlank Date fechanacimiento) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechanacimiento = fechanacimiento;
	}



	
    
}
