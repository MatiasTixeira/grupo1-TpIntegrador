package compras;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import puntoDeVenta.PuntoDeVenta;

class CompraSaldoTestCase {
	private CompraSaldo compraSaldo;

	@BeforeEach
	public void setUp() {
		PuntoDeVenta puntoDeVenta = mock(PuntoDeVenta.class);

		compraSaldo = new CompraSaldo(puntoDeVenta, 350d, "03-03-456");
	}

	@Test
	void test() {
		assertEquals("03-03-456", compraSaldo.getNumeroDeCelular());
	}
}
