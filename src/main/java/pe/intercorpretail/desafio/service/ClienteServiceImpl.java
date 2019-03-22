package pe.intercorpretail.desafio.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.intercorpretail.desafio.dao.ClienteDao;
import pe.intercorpretail.desafio.model.Cliente;
import pe.intercorpretail.desafio.util.AppConstants;
import pe.intercorpretail.desafio.util.Utilitarios;
import pe.intercorpretail.desafio.vo.ClienteRequest;
import pe.intercorpretail.desafio.vo.ClienteResponse;
import pe.intercorpretail.desafio.vo.KpiResponse;

/**
 * Nombre Clase: ClienteServiceImpl.java
 * <br>
 * Fecha Creaci&oacute;n:	 2019/03/20
 * <br>
 * Fecha Modificaci&oacute;n:
 * <br>
 * Clase que implementa @Service de cliente
 * <br>
 * Cambios Importantes:
 * <br>
 * @author  Vinicio Huertas
 * @version 1.0.0
 * @since jdk 1.8
**/

@Service
public class ClienteServiceImpl implements IClienteService {
	
	@Autowired
	ClienteDao clienteDao;
	
	Cliente cliente = null;
	ClienteResponse clienteRes = null;
	List<Cliente> clientes = null;
	List<ClienteResponse> clientesRes = null;
	KpiResponse kpiResponse = null;
	public double promedioEdad = 0.f;
	public double desEstEdad = 0.f;
	
	@Override
	public ClienteResponse creaCliente(ClienteRequest clienteReq) {
		cliente = clienteDao.save(clienteReqToCliente(clienteReq));
		return clienteToClienteRes(cliente);
	}
	
	public Cliente clienteReqToCliente(ClienteRequest clienteReq) {
		cliente = new Cliente(clienteReq.getNombre(), clienteReq.getApellido(), clienteReq.getFechanacimiento());
		cliente.setEdad(Utilitarios.edad(clienteReq.getFechanacimiento()));
		return cliente;
	}
	
	public ClienteResponse clienteToClienteRes(Cliente cliente) {
		return new ClienteResponse(cliente.getNombre(), cliente.getApellido(), cliente.getEdad(), cliente.getFechanacimiento());
	}

	@Override
	public KpiResponse kpiDeClientes() {
		kpiResponse = new KpiResponse();
		clientes = clienteDao.findAll();
		if (clientes != null && clientes.size() > 0) {
			kpiResponse.setPromedioEdad(calcularPromedioEdad(clientes));
			kpiResponse.setDesEstEdad(calcularDesEstEdad(clientes));
			return kpiResponse;
		}
		else
			return null;
		
	}
	
	public double calcularPromedioEdad(List<Cliente> clientes) {
		promedioEdad = 0.f;
		for (Cliente cliente : clientes) {
			promedioEdad += cliente.getEdad().floatValue();
		}
		return promedioEdad/clientes.size();
	}
	
	public double calcularDesEstEdad(List<Cliente> clientes) {
		double distancia = 0.f;
		double sumatoria = 0.f;
		double media = calcularPromedioEdad(clientes); //Media del conjunto de datos
		for (Cliente cliente : clientes) {				//Desviaciones a cada dato y sumatoria
			distancia = cliente.getEdad() - media;
			sumatoria += Math.pow(distancia,2);
		}
		desEstEdad = sumatoria/clientes.size(); //División de sumatoria para el número de clientes
		return Math.sqrt(desEstEdad); //Raiz cuadrada para obtener la desviación estandar
	}

	@Override
	public List<ClienteResponse> listClientes() {
		clientesRes = new ArrayList<ClienteResponse>();
		clientes = clienteDao.findAll();
		for (Cliente cliente : clientes) {
			clienteRes = clienteToClienteRes(cliente);
			clienteRes.setFechaProbMuerte(calcularFechaProbMuerte(cliente.getFechanacimiento()));
			clientesRes.add(clienteRes);
		}
		return clientesRes;
	}
	
	public Date calcularFechaProbMuerte(Date fechaNacimiento) {
		return Utilitarios.sumarAños(fechaNacimiento, AppConstants.ESPERANZA_VIDA_CONTINENTE);
	}

	@Override
	public void limpiarDBClientes() {
		clienteDao.deleteAll();
	}

}
