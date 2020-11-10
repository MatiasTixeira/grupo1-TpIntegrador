package compras;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import puntoDeVenta.PuntoDeVenta;

class CompraPuntualTest {
	private CompraPuntual compraPuntual;
	private PuntoDeVenta puntoDeVenta;
	private Integer cantidadDeHoras;
	private LocalDate fecha;
	private LocalTime hora;

	@BeforeEach
	public void setUp() {
		this.puntoDeVenta = mock(PuntoDeVenta.class);
		this.cantidadDeHoras = 4;
		this.fecha = LocalDate.now();
		this.hora = LocalTime.now();

		this.compraPuntual = new CompraPuntual(
				this.puntoDeVenta, this.cantidadDeHoras, this.fecha, this.hora);
	}

	@Test
	void testSetNumeroDeControl() {

		this.compraPuntual.setNumeroDeControl(1000);
		assertEquals(1000, compraPuntual.getNumeroDeControl());
	}

	@Test
	void testSePuedeAccederALosCamposSeteadosEnElConstructorCompraPuntual() {

		assertEquals(this.puntoDeVenta, this.compraPuntual.getPuntoDeVenta());
		assertEquals(this.cantidadDeHoras, this.compraPuntual.getCantidadDeHoras());
		assertEquals(this.fecha, this.compraPuntual.getFecha());
		assertEquals(this.hora, this.compraPuntual.getHora());
	}
}
