package compras;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import puntoDeVenta.PuntoDeVenta;

class CompraPuntualTestCase {
	CompraPuntual compraPuntual;

	@BeforeEach
	public void setUp() {
		PuntoDeVenta puntoDeVenta = mock(PuntoDeVenta.class);

		this.compraPuntual = new CompraPuntual(puntoDeVenta, 4);
	}

	@Test
	void testSetNumeroDeControl() {
		compraPuntual.setNumeroDeControl(1000);
		assertEquals(1000, compraPuntual.getNumeroDeControl());
	}

	@Test
	void cuandoComproUnaCiertaCantidadDeHorasQuedaRegistro() {
		compraPuntual.setNumeroDeControl(1000);
		assertEquals(4, compraPuntual.getCantidadDeHoras());
	}
}
