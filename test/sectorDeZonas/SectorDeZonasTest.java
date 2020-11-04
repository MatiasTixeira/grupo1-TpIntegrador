package sectorDeZonas;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import appInspector.Infraccion;
import espacioGeografico.Ubicacion;
import espacioGeografico.UbicacionGeografica;
import estacionamiento.Estacionamiento;
import puntosDeVenta.PuntoDeVenta;
import sectorDeSaldos.sectorDeSaldo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class SectorDeZonasTest {
	ZonaDeEstacionamiento zona;
	SectorDeZonas sectorZona;
	UbicacionGeografica ubi;
	
	@BeforeEach
	public void setUp() {
	zona = mock(ZonaDeEstacionamiento.class);
	}
	
	@Test
	void cuandoTenemosUnaZonaEstaSePuedeRegistrarEnElSectorDeZonas(){
		sectorZona = new SectorDeZonas();
		sectorZona.registrar(zona);
		assertEquals(1,sectorZona.getZonas().size());
	}
	
	@Test
	void cuandoUnaUbicacionPerteneceAUnaZonaEstaRetornaTrue() {
		sectorZona = new SectorDeZonas();
		sectorZona.registrar(zona);
		when(zona.contiene(any(Ubicacion.class))).thenReturn(true);
		assertEquals(true,sectorZona.perteneceAUnaZonaDeEstacionamiento(mock(Ubicacion.class)));
	}

	@Test
	void cuandoUnaUbicacionNoPerteneceAUnaZonaEstaRetornaTrue() {
		sectorZona = new SectorDeZonas();
		sectorZona.registrar(zona);
		when(zona.contiene(any(Ubicacion.class))).thenReturn(false);
		assertEquals(false,sectorZona.perteneceAUnaZonaDeEstacionamiento(mock(Ubicacion.class)));
	}
}
