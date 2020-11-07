package estacionamiento;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import compras.CompraPuntual;

class EstacionamientoPuntualTest {
	private EstacionamientoPuntual estacionamiento;
	private CompraPuntual compra;
	@BeforeEach
	public void setUp() {
		compra = mock(CompraPuntual.class);
		estacionamiento = 
				new EstacionamientoPuntual("AAA-111",LocalTime.of(10, 0),LocalTime.of(20, 0),compra);
	}
	@Test
	void testConstructor() {
		compra = mock(CompraPuntual.class);
		estacionamiento = 
				new EstacionamientoPuntual("AAA-111",LocalTime.of(10, 0),LocalTime.of(20, 0),compra);
		
	assertTrue(estacionamiento.esSuPatente("AAA-111"));
	assertTrue(estacionamiento.estaVigente()); 
	assertEquals(LocalTime.of(10,0), estacionamiento.getHoraInicio());
	assertEquals(LocalTime.of(20, 0), estacionamiento.getHoraFin());
	assertFalse(estacionamiento.esSuCelular("03-03-456"));
	assertEquals(compra, estacionamiento.getCompra());
		
	}
	@Test
	void cuandoFinaliza() {
		compra = mock(CompraPuntual.class);
		estacionamiento = 
				new EstacionamientoPuntual("AAA-111",LocalTime.of(10, 0),LocalTime.of(20, 0),compra);
		assertTrue(estacionamiento.estaVigente());
		estacionamiento.finalizar();
		assertFalse(estacionamiento.estaVigente());
		
		
	}

}
