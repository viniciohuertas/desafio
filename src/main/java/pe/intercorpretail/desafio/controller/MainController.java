package pe.intercorpretail.desafio.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Nombre Clase: MainController.java
 * <br>
 * Fecha Creaci&oacute;n:	 2019/03/21
 * <br>
 * Fecha Modificaci&oacute;n:
 * <br>
 * Clase para la bienvenida a la API REST desafío Intercorp Retail
 * <br>
 * Cambios Importantes:
 * <br>
 * @author  Vinicio Huertas
 * @version 1.0.0
 * @since jdk 1.8
**/

@RestController
public class MainController {
	
	@RequestMapping("/")
	public String index() {
		String response = "Bienvenido al Desafío Intercorp Retail <a href='http://52.24.85.226:8080/swagger-ui.html'>continuar</a>";
		return response;
	}
	
}