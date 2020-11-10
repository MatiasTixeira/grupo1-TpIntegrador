package sistemaDeEstacionamientoMedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sectorDeCompras.ISectorDeCompras;
import sectorDeEstacionamiento.ISectorDeEstacionamiento;
import sectorDeInfracciones.ISectorDeInfracciones;
import sectorDeSaldos.ISectorSaldo;
import sectorDeZonas.ISectorZonas;
import suscripcion.IGestorDeSuscripcion;

class SistemaDeEstacionamientoMedidoTest {
	private SistemaDeEstacionamientoMedido sem;
	private ISectorDeEstacionamiento sectorEstacionamiento; 
	private ISectorSaldo sectorSaldo;
	private ISectorZonas sectorZona; 
	private ISectorDeCompras sectorCompra;
	private ISectorDeInfracciones sectorInfraccion;
	private IGestorDeSuscripcion gestorSuscripcion;
	
	
	@BeforeEach
	public void setUp() {
		
		LocalTime horaInicio = LocalTime.of(7, 0);
		LocalTime horaFin = LocalTime.of(20, 0);
		Double precioPorHora = 40d;
		sectorEstacionamiento = mock(ISectorDeEstacionamiento.class);
		sectorSaldo = mock(ISectorSaldo.class);
		sectorZona = mock(ISectorZonas.class);
		sectorCompra = mock(ISectorDeCompras.class);
		sectorInfraccion = mock(ISectorDeInfracciones.class);
		gestorSuscripcion = mock(IGestorDeSuscripcion.class);
		sem = new SistemaDeEstacionamientoMedido
				(horaInicio,horaFin,precioPorHora,sectorEstacionamiento,
				sectorSaldo,sectorZona,sectorCompra,sectorInfraccion,
				gestorSuscripcion);
	}
	

	@Test
	void testConstructor() {
		
		LocalTime horaInicio = LocalTime.of(7, 0);
		LocalTime horaFin = LocalTime.of(20, 0);
		Double precioPorHora = 40d;
		
		assertEquals(horaInicio, sem.getHoraInicio());
		assertEquals(horaFin,sem.getHoraFin());
		assertEquals(precioPorHora,sem.getPrecioPorHora());
		assertEquals(sectorEstacionamiento,sem.getSectorEstacionamiento());
		assertEquals(sectorSaldo,sem.getSectorSaldo());
		assertEquals(sectorZona,sem.getSectorZona());
		assertEquals(sectorCompra,sem.getSectorCompra());
		assertEquals(sectorInfraccion,sem.getSectorInfraccion());
		assertEquals(gestorSuscripcion,sem.getSectorSuscripcion());
	}

	@Test
	void cuandoElSemCambiaSuHoraDeFinLeAvisaAlSectorDeCompra() {
		
		sem.setHoraFin(LocalTime.of(6,30));
		
		verify(this.sectorEstacionamiento).setHoraFin(LocalTime.of(6,30));
	}
	@Test
	void cuandoElSemCambiaSuHoraDeInicioLeAvisaAlSectorDeCompra() {
		
		sem.setHoraInicio(LocalTime.of(6,30));
		
		verify(this.sectorEstacionamiento).setHoraInicio(LocalTime.of(6,30));
	}
	@Test
	void cuandoElSemCambiaSuPrecioPorHoraLeAvisaAlSectorDeCompra() {
		
		sem.setPrecioPorHora(100d); 
		
		verify(this.sectorEstacionamiento).setPrecioPorHora(100d);
	}
}
