package respuestasTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import respuestas.RespuestaInicioEstacionamiento;

class RespuestaInicioEstacionamientoTest {
	RespuestaInicioEstacionamiento resIni;
	LocalTime ini;
	LocalTime fin;

	@BeforeEach
	void setUp() throws Exception {
		ini = LocalTime.of(10, 00);
		fin = LocalTime.of(20, 00);
		resIni = new RespuestaInicioEstacionamiento(ini,fin);
	}

	@Test
	void sePuedeAccederALosCamposSeteadosEnElConstructor() {
		assertEquals(fin ,resIni.getHoraFin());
		assertEquals(ini ,resIni.getHoraInicio());
	}

	@Test
	void unaRespuestaPuedeDarlaMismaComoUnString() {
		String textoDeseado = 
				"El estacionamiento comenzó a las " +
				ini +
				" y su duración máxima es hasta las " +
				fin + ".";
		assertEquals(textoDeseado ,resIni.respuestaComoString());
	}
	
	@Test
	void cuandoLaOperacionEsExitosaRetornaTrue() {
		assertEquals(true ,resIni.operacionExitosa());
	}
	
}
