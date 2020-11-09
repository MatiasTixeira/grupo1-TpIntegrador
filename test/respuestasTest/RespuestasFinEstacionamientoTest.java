package respuestasTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import respuestas.RespuestaFinEstacionamiento;

class RespuestasFinEstacionamientoTest {
	RespuestaFinEstacionamiento resFin;
	LocalTime ini;
	LocalTime fin;
	Integer cantHoras;
	Double costo;

	@BeforeEach
	void setUp() throws Exception {
		ini = LocalTime.of(10, 00);
		fin = LocalTime.of(20, 00);
		cantHoras = 5;
		costo =  200d;
		resFin = new RespuestaFinEstacionamiento(ini,fin, cantHoras, costo);
	}

	@Test
	void sePuedeAccederALosCamposSeteadosEnElConstructor() {
		assertEquals(fin ,resFin.getHoraFin());
		assertEquals(ini ,resFin.getHoraInicio());
		assertEquals(200d,resFin.getCosto());
		assertEquals(5,resFin.getCantHoras());
		
	}

	@Test
	void unaRespuestaPuedeDarlaMismaComoUnString() {
		String textoDeseado = 
				"El estacionamiento comenza a las " +
				ini +
				" y termina a las " +
				fin +
				". Dura " +
				cantHoras +
				" horas, y costa $" +
				costo;
		assertEquals(textoDeseado ,resFin.respuestaComoString());
	}
	
	@Test
	void cuandoLaOperacionEsExitosaRetornaTrue() {
		assertEquals(true ,resFin.operacionExitosa());
	}
}
