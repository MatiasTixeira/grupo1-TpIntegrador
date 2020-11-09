package sectorDeInfracciones;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sectorDeZonas.ZonaDeEstacionamiento;

class InfraccionTest {
	private Infraccion infraccion;
	private String patente;
	private ZonaDeEstacionamiento zona;
	private String inspector;
	private LocalDate fecha;
	private LocalTime hora;

	@BeforeEach
	public void setUp() {
		this.patente = "ABC123";
		this.zona = mock(ZonaDeEstacionamiento.class);
		this.inspector = "Rogelio";
		this.fecha = LocalDate.now();
		this.hora = LocalTime.now();
	}

	@Test
	void testSePuedeAccederALosCamposSeteadosEnElConstructor() {
		this.infraccion = new Infraccion(this.patente, this.zona, this.inspector, this.fecha, this.hora);

		assertEquals(this.patente, this.infraccion.getPatente());
		assertEquals(this.zona, this.infraccion.getZona());
		assertEquals(this.inspector, this.infraccion.getInspector());
		assertEquals(this.fecha, this.infraccion.getFecha());
		assertEquals(this.hora, this.infraccion.getHora());
	}

}
