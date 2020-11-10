package compras;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import puntoDeVenta.PuntoDeVenta;

class CompraSaldoTest {
	private CompraSaldo compraSaldo;
	private PuntoDeVenta puntoDeVenta;
	private Double monto;
	private String nroCelular;
	private LocalDate fecha;
	private LocalTime hora;

	@BeforeEach
	public void setUp() {
		this.puntoDeVenta = mock(PuntoDeVenta.class);
		this.monto = 350d;
		this.nroCelular = "03-03-456";
		this.fecha = LocalDate.now();
		this.hora = LocalTime.now();

	}

	@Test
	void testSePuedeAccederALosCamposSeteadosEnElConstructorCompraSaldo() {
		this.compraSaldo = new CompraSaldo(
				this.puntoDeVenta, this.monto, this.nroCelular, this.fecha, this.hora);

		assertEquals(this.puntoDeVenta, this.compraSaldo.getPuntoDeVenta());
		assertEquals(this.monto, this.compraSaldo.getMonto());
		assertEquals(this.nroCelular, this.compraSaldo.getNumeroDeCelular());
		assertEquals(this.fecha, this.compraSaldo.getFecha());
		assertEquals(this.hora, this.compraSaldo.getHora());
	}
}
