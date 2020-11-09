package respuestasTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import respuestas.RespuestaOperacionFallida;
import respuestas.RespuestaSinSaldo;

class RespuestaOperacionFallidaTest {
	RespuestaOperacionFallida resFal;

	@BeforeEach
	void setUp() throws Exception {
		resFal = new RespuestaOperacionFallida();
	}

	@Test
	void unaRespuestaPuedeDarlaMismaComoUnString() {
		String textoDeseado = "No se puede realizar la operacion.";
		assertEquals(textoDeseado ,resFal.respuestaComoString());
	}

	@Test
	void cuandoLaOperacionEsExitosaRetornaFalse() {
		assertEquals(false ,resFal.operacionExitosa());
	}

}
