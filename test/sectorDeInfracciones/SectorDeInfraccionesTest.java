package sectorDeInfracciones;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import appInspector.Infraccion;
import puntoDeVenta.PuntoDeVenta;
import sectorDeCompras.IRegistroCompras;
import sectorDeCompras.SectorDeCompras;
import sectorDeEstacionamiento.IControlDeEstacionamiento;
import sectorDeSaldos.IControlSaldo;

class SectorDeInfraccionesTest {
	SectorDeInfracciones sectorDeInfracciones;
	
	@BeforeEach
	public void setUp() {
		sectorDeInfracciones= new SectorDeInfracciones();
	}
	
	@Test
	void cuandoSeInstanciaUnSectorDeInfraccionesComienzaConLaListaVacia() {
		assertEquals(0,sectorDeInfracciones.getInfracciones().size());
	}
	
	@Test
	void cuandoSeGeneraUnaInfraccionElSectorDeInfraccionesLoRegistra() {
		Infraccion infraccion = mock(Infraccion.class);
		sectorDeInfracciones.registrar(infraccion);
		assertEquals(1,sectorDeInfracciones.getInfracciones().size());
	}
}


