package pe.intercorpretail.desafio.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Nombre Clase: Utilitarios.java
 * <br>
 * Fecha Creaci&oacute;n:	 2019/03/20
 * <br>
 * Fecha Modificaci&oacute;n:
 * <br>
 * Clase de Utilitarios para todo el proyecto
 * <br>
 * Cambios Importantes:
 * <br>
 * @author  Vinicio Huertas
 * @version 1.0.0
 * @since jdk 1.8
**/

public class Utilitarios {
	
	/**
	 * Metodo que devuelve la edad a partir de la fecha de nacimiento.
	 * @param fechaNacimiento Fecha de nacimiento
	 * @return Edad calculada en base a la fecha de nacimiento.
	 */
	public static int edad(Date fechaNacimiento) { 

		int anios=0;

		Calendar cfechaHoy = Calendar.getInstance();
		Calendar cFechaNacimiento = Calendar.getInstance();
		cFechaNacimiento.setTime(fechaNacimiento);
		int anioFNAC = cFechaNacimiento.get(Calendar.YEAR);
		int anioHOY = cfechaHoy.get(Calendar.YEAR);

		int mesFNC = cFechaNacimiento.get(Calendar.MONTH) +1;
		int mesHOY = cfechaHoy.get(Calendar.MONTH)+1;

		int diaFNAC = cFechaNacimiento.get(Calendar.DATE);
		int diaHOY = cfechaHoy.get(Calendar.DATE);

		int restaAnios = anioHOY - anioFNAC;

		if(restaAnios>0){
			if(mesHOY>mesFNC)
				anios = restaAnios;
			else if (mesHOY==mesFNC) {
				if(diaHOY>=diaFNAC)
					anios = restaAnios;
				else
					anios = restaAnios-1;
			}else
				anios = restaAnios - 1;
		}else{
			anios = 0;
		}
		return anios;
	}
	
	public static Date sumarAños(Date fecha, int años) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.YEAR, años);
		return calendar.getTime();
	}

}
