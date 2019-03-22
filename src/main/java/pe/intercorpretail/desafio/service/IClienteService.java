package pe.intercorpretail.desafio.service;

import java.util.List;

import pe.intercorpretail.desafio.vo.ClienteRequest;
import pe.intercorpretail.desafio.vo.ClienteResponse;
import pe.intercorpretail.desafio.vo.KpiResponse;

/**
 * Nombre Clase: IClienteService.java
 * <br>
 * Fecha Creaci&oacute;n:	 2019/03/20
 * <br>
 * Fecha Modificaci&oacute;n:
 * <br>
 * Interface del servicio de cliente
 * <br>
 * Cambios Importantes:
 * <br>
 * @author  Vinicio Huertas
 * @version 1.0.0
 * @since jdk 1.8
**/

public interface IClienteService {
	public ClienteResponse creaCliente(ClienteRequest clienteReq);
	public KpiResponse kpiDeClientes();
	public List<ClienteResponse> listClientes();
	public void limpiarDBClientes();
}
