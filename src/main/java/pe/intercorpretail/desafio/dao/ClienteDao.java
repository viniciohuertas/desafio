package pe.intercorpretail.desafio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.intercorpretail.desafio.model.Cliente;

/**
 * Nombre Clase: ClienteDao.java
 * <br>
 * Fecha Creaci&oacute;n:	 2019/03/20
 * <br>
 * Fecha Modificaci&oacute;n:
 * <br>
 * Clase @Repository de ClientDao
 * <br>
 * Cambios Importantes:
 * <br>
 * @author  Vinicio Huertas
 * @version 1.0.0
 * @since jdk 1.8
**/

@Repository
public interface ClienteDao extends JpaRepository<Cliente, Long> {

}
