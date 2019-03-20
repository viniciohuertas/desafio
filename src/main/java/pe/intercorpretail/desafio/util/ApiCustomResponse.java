package pe.intercorpretail.desafio.util;

import java.util.Map;

import lombok.Data;

/**
 * Nombre Clase: ApiCustomResponse.java
 * <br>
 * Fecha Creaci&oacute;n:	 2019/03/20
 * <br>
 * Fecha Modificaci&oacute;n:
 * <br>
 * Clase para respuestas personalizadas de los API
 * <br>
 * Cambios Importantes:
 * <br>
 * @author  Vinicio Huertas
 * @version 1.0.0
 * @since jdk 1.8
**/

@Data
public class ApiCustomResponse {
    private Boolean success;
    private String message;

    public ApiCustomResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}
