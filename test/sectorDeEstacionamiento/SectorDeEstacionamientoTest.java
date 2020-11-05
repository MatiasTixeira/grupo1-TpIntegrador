package sectorDeEstacionamiento;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import estacionamiento.EstacionamientoPuntual;

class SectorDeEstacionamientoTest {
	private SectorDeEstacionamiento sectorDeEstacionamiento;
	@BeforeEach

	public void setUp() {
		sectorDeEstacionamiento =
			new SectorDeEstacionamiento(LocalTime.of(8, 0), LocalTime.of(20, 0), 40d);

	}
	@Test
	void testConstructor() {
		assertEquals(0,sectorDeEstacionamiento.getEstacionamientos().size());
		assertEquals(40, sectorDeEstacionamiento.getPrecioPorHora());
	}
	@Test
	void registrarEstacionamientoTest() {
		EstacionamientoPuntual estacionamiento = mock(EstacionamientoPuntual.class);

		sectorDeEstacionamiento.registrarEstacionamiento(estacionamiento);
		assertEquals(1,sectorDeEstacionamiento.getEstacionamientos().size());

		sectorDeEstacionamiento.registrarEstacionamiento(estacionamiento);
		assertEquals(2,sectorDeEstacionamiento.getEstacionamientos().size());
	}
	@Test
	void cuandoSeCompruebaLaVigenciaDeDeEstacionamientoDeUnaPatente() {
		EstacionamientoPuntual estacionamiento = mock(EstacionamientoPuntual.class);
		when(estacionamiento.estaVigente()).thenReturn(true,false);
		when(estacionamiento.getPatente()).thenReturn("11AA");
		//No hay estacionamiento registrado con esa patente
		assertFalse(sectorDeEstacionamiento.tieneEstacionamientoVigente("11AA"));
		//El estacionamiento esta registrado con esa patente y esta vigente
		sectorDeEstacionamiento.registrarEstacionamiento(estacionamiento);
		assertTrue(sectorDeEstacionamiento.tieneEstacionamientoVigente("11AA"));
		//El estacionamiento esta registrado con esa patente pero no esta vigente
		assertFalse(sectorDeEstacionamiento.tieneEstacionamientoVigente("11AA"));

	}
	@Test
	void retornoDeEstacionamientoVigenteBuscadoPorElNumeroCelular(){
		EstacionamientoPuntual estacionamiento = mock(EstacionamientoPuntual.class);
		when(estacionamiento.estaVigente()).thenReturn(true);
		when(estacionamiento.getNumeroCelular()).thenReturn("03-03-456");

		sectorDeEstacionamiento.registrarEstacionamiento(estacionamiento);
		assertEquals(estacionamiento,sectorDeEstacionamiento.estacionamientoVigente("03-03-456"));

	}
	@Test
	void cuandoSefinalizanTodosLosEstacionamientosNoEstanMasVigentes() {
		EstacionamientoPuntual estacionamiento = mock(EstacionamientoPuntual.class);
		when(estacionamiento.estaVigente()).thenReturn(true);
		when(estacionamiento.getPatente()).thenReturn("11AA");
		EstacionamientoPuntual estacionamiento2 = mock(EstacionamientoPuntual.class);
		when(estacionamiento2.estaVigente()).thenReturn(true);
		when(estacionamiento2.getPatente()).thenReturn("22BB");
		//agrego los estacionamientos vigentes
		sectorDeEstacionamiento.registrarEstacionamiento(estacionamiento);
		sectorDeEstacionamiento.registrarEstacionamiento(estacionamiento2);
		assertTrue(sectorDeEstacionamiento.tieneEstacionamientoVigente("11AA"));
		assertTrue(sectorDeEstacionamiento.tieneEstacionamientoVigente("22BB"));
		//finalizo los estacionamientos
		sectorDeEstacionamiento.finalizarTodosLosEstacionamientos();
		//verifico que hayan finalizado
		verify(estacionamiento).finalizar();
		verify(estacionamiento2).finalizar();
	}

	@Test
	void horarioDeEstacionamiento() {
		SectorDeEstacionamiento spySectorDeEstacionamiento = spy(sectorDeEstacionamiento);
		when(spySectorDeEstacionamiento.horaActual())
			.thenReturn(LocalTime.of(10, 0),LocalTime.of(10, 0)
						,LocalTime.of(22, 0),LocalTime.of(22, 0));
		assertTrue(spySectorDeEstacionamiento.esHorarioDeEstacionamiento());
		assertFalse(spySectorDeEstacionamiento.esHorarioDeEstacionamiento());
	}
}
