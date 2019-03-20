package pe.intercorpretail.desafio.util;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

/**
 * Nombre Clase: ApiCustomResponseMap.java
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
public class ApiCustomResponseMap {
    private Map<Boolean, String> response = new HashMap<>();

    public ApiCustomResponseMap(Map<Boolean, String> response) {
        this.response = response;
    }

    
}
