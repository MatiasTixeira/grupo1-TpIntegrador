package respuestasTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import respuestas.RespuestaSinSaldo;

class RespuestaSinSaldoTest {
	RespuestaSinSaldo resSal;

	@BeforeEach
	void setUp() throws Exception {
		resSal = new RespuestaSinSaldo();
	}

	@Test
	void unaRespuestaPuedeDarlaMismaComoUnString() {
		String textoDeseado = "Saldo insuficiente. Estacionamiento no permitido.";
		assertEquals(textoDeseado ,resSal.respuestaComoString());
	}

	@Test
	void cuandoLaOperacionEsExitosaRetornaFalse() {
		assertEquals(false ,resSal.operacionExitosa());
	}
}
