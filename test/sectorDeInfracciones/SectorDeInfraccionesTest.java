package sectorDeInfracciones;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	void registrarInfraccionLaGuardaEnLaLista() {
		Infraccion infraccion = mock(Infraccion.class);
		sectorDeInfracciones.registrar(infraccion);
		assertTrue(sectorDeInfracciones.getInfracciones().contains(infraccion));
	}
}


