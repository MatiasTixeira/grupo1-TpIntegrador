package sectorDeZonas;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import espacioGeografico.Ubicacion;
import espacioGeografico.UbicacionGeografica;
import puntosDeVenta.PuntoDeVenta;

class ZonaDeEstacionamientoTest {
	ZonaDeEstacionamiento zonaE;
	UbicacionGeografica ubi;
	
	@BeforeEach
	public void setUp() {
	ubi = mock(UbicacionGeografica.class);
	PuntoDeVenta punto = mock(PuntoDeVenta.class);
	ArrayList<PuntoDeVenta> puntos = new ArrayList<PuntoDeVenta>();
	puntos.add(punto);
	zonaE = new ZonaDeEstacionamiento("Juan",ubi,puntos);
	}
	
	@Test
	void cuandoUnaUbicacionEstaDentroDeLaZonaRetornaTrue() {
		when(ubi.estaDentroDeLaZona(any(Ubicacion.class))).thenReturn(true);
		assertEquals(true,zonaE.contiene(mock(Ubicacion.class)));
	}
	
	@Test
	void cuandoUnaUbicacionEstaDentroDeLaZonaRetornaFalse() {
		when(ubi.estaDentroDeLaZona(any(Ubicacion.class))).thenReturn(false);
		assertEquals(false,zonaE.contiene(mock(Ubicacion.class)));
	}

}
