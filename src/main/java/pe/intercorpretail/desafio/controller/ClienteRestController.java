package pe.intercorpretail.desafio.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.apachecommons.CommonsLog;
import pe.intercorpretail.desafio.service.IClienteService;
import pe.intercorpretail.desafio.vo.ClienteRequest;
import pe.intercorpretail.desafio.vo.ClienteResponse;
import pe.intercorpretail.desafio.vo.KpiResponse;

/**
 * Nombre Clase: ClienteRestController.java
 * <br>
 * Fecha Creaci&oacute;n:	 2019/03/20
 * <br>
 * Fecha Modificaci&oacute;n:
 * <br>
 * Clase @RestController de ClienteRestController
 * <br>
 * Cambios Importantes:
 * <br>
 * @author  Vinicio Huertas
 * @version 1.0.0
 * @since jdk 1.8
**/

@CommonsLog(topic="ClienteRestController")
@RestController
public class ClienteRestController {
	
	@Autowired
	IClienteService clienteService;
	
	ClienteResponse clienteResponse = null;
	KpiResponse kpiResponse = null;
	List<ClienteResponse> clientes = null;
	
	private Map<String, Object> response = null;
	
	/***
	 * Metodo que permite registrar un cliente
	 * @param clienteRequest parametro Json que contiene la informacion del registro a persistir
	 * @return Retorno un Map con los datos almacenados, o el error correspondiente
	 */
	@PostMapping("/creacliente")
	@ApiOperation(value = "Permite registrar un cliente", notes = "Retorno un Map con los datos almacenados, o el error correspondiente")
	@ApiResponses(value = {@ApiResponse(code=201,message="Acción correcta"),
			@ApiResponse(code=404, message = "Resultados no encontrados")})
	public ResponseEntity<?> creaCliente(@Valid @RequestBody ClienteRequest clienteReq, BindingResult result){
		response = new HashMap<>();
		HttpStatus httpStatus;
		if (result.hasErrors()) {
			List<String> errores = result.getFieldErrors()
					.stream().map(error ->  "El campo '" + error.getField() +"' "+ error.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errores", errores);		
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			clienteResponse = clienteService.creaCliente(clienteReq);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al crear el registro en la BDD");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (clienteResponse != null) {
			response.put("mensaje", "El cliente se creo con éxito");
			response.put("Cliente", clienteResponse.getNombre()+" "+clienteResponse.getApellido());
			httpStatus = HttpStatus.CREATED;
		}
		else {
			response.put("mensaje", "Error al crear el cliente");
			httpStatus = HttpStatus.CONFLICT;
		}
		return new ResponseEntity<Map<String, Object>>(response, httpStatus);
	}
	
	/***
	 * Metodo que permite obtener kpiDeClientes
	 * @return Retorno un Map con los resultados encontrados, o el error correspondiente
	 */
	@GetMapping("/kpideclientes")
	@ApiOperation(value = "Permite obtener kpiDeClientes (Promedio de edad, Desviación estandar de la edad)", notes = "Retorno un Map con los resultados encontrados, o el error correspondiente")
	@ApiResponses(value = {@ApiResponse(code=201,message="Consulta correcta"),
			@ApiResponse(code=404, message = "Resultados no encontrados")})
	public ResponseEntity<?> kpiDeClientes(){
		response = new HashMap<>();
		try {
			kpiResponse = clienteService.kpiDeClientes();
		} catch (DataAccessException e) {
			log.error(e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put("mensaje", "Error al realizar la consulta en la BDD");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (kpiResponse != null) {
			response.put("Promedio edad",kpiResponse.getPromedioEdad());
			response.put("Desviación estándar",kpiResponse.getDesEstEdad());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}else {
			response.put("mensaje","No se encontraron resultados en la BDD.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
	}
	
	/***
	 * Metodo que permite obtener la lista de clientes
	 * @return Retorno un Map con los resultados encontrados, o el error correspondiente
	 */
	@GetMapping("/listclientes")
	@ApiOperation(value = "Permite obtener la lista de clientes", notes = "Retorno un Map con los resultados encontrados, o el error correspondiente")
	@ApiResponses(value = {@ApiResponse(code=201,message="Consulta correcta"),
			@ApiResponse(code=404, message = "Resultados no encontrados")})
	public ResponseEntity<?> listClientes(){
		response = new HashMap<>();
		try {
			clientes = clienteService.listClientes();
		} catch (DataAccessException e) {
			log.error(e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put("mensaje", "No se encontraron resultados en la BDD.");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (clientes != null && clientes.size() > 0) {
			response.put("mensaje", "Consulta exitosa");
			response.put("Clientes", clientes);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}else {
			response.put("mensaje","No se encontraron resultados en la BDD.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
	}
	
	/***
	 * Metodo que permite limpiar la DB de clientes
	 * @return Retorno un mensaje de confirmación, o el error correspondiente
	 */
	@DeleteMapping("/limpiarDBClientes")
	@ApiOperation(value = "Permite limpiar la DB de clientes", notes = "Retorno un mensaje de confirmación, o el error correspondiente")
	@ApiResponses(value = {@ApiResponse(code=201,message="Consulta correcta"),
			@ApiResponse(code=404, message = "Resultados no encontrados")})
	public ResponseEntity<?> limpiarDBClientes(){
		response = new HashMap<>();
		try {
			clienteService.limpiarDBClientes();
		} catch (DataAccessException e) {
			log.error(e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			response.put("mensaje", "Error al realizar la consulta en la BDD");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","Limpieza de DB realizada con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
