package sectorDeEstacionamiento;

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
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import estacionamiento.EstacionamientoApp;
import estacionamiento.EstacionamientoPuntual;
import sectorDeSaldos.IControlSaldo;

class SectorDeEstacionamientoTest {
	private SectorDeEstacionamiento sectorDeEstacionamiento;
	private IControlSaldo controlDesaldo;
	@BeforeEach
	public void setUp() {
		controlDesaldo = mock(IControlSaldo.class);
		sectorDeEstacionamiento =
			new SectorDeEstacionamiento(LocalTime.of(8, 0), LocalTime.of(20, 0), 40d,controlDesaldo);

	}
	@Test
	void testConstructor() {
		assertEquals(0,sectorDeEstacionamiento.getEstacionamientos().size());
		assertEquals(40d, sectorDeEstacionamiento.getPrecioPorHora());
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
	void cuandoSeCompruebaLaVigenciaDeEstacionamientoDeUnaPatente() {
		EstacionamientoPuntual estacionamiento = mock(EstacionamientoPuntual.class);
		when(estacionamiento.estaVigente()).thenReturn(true,false);
		when(estacionamiento.esSuPatente("11AA")).thenReturn(true);
		//No hay estacionamiento registrado con esa patente
		assertFalse(sectorDeEstacionamiento.tieneEstacionamientoVigenteConPatente("11AA"));
		//El estacionamiento esta registrado con esa patente y esta vigente
		sectorDeEstacionamiento.registrarEstacionamiento(estacionamiento);
		assertTrue(sectorDeEstacionamiento.tieneEstacionamientoVigenteConPatente("11AA"));
		//El estacionamiento esta registrado con esa patente pero no esta vigente
		assertFalse(sectorDeEstacionamiento.tieneEstacionamientoVigenteConPatente("11AA"));

	}
	@Test
	void retornoDeEstacionamientoVigente(){
		EstacionamientoApp estacionamiento = mock(EstacionamientoApp.class);
		when(estacionamiento.estaVigente()).thenReturn(true,false);
		when(estacionamiento.esSuCelular("03-03-456")).thenReturn(true,false);

		sectorDeEstacionamiento.registrarEstacionamiento(estacionamiento);
		assertEquals(estacionamiento,sectorDeEstacionamiento.estacionamientoVigente("03-03-456"));
		
	}
	
	@Test
	void tieneEstacionamientoVigenteBuscadoPorElNumeroCelular(){
		EstacionamientoApp estacionamiento = mock(EstacionamientoApp.class);
		EstacionamientoPuntual estacionamientoPuntual = mock(EstacionamientoPuntual.class);
		EstacionamientoApp estacionamientoApp2 = mock(EstacionamientoApp.class);
		when(estacionamientoApp2.estaVigente()).thenReturn(false);
		when(estacionamientoApp2.esSuCelular("12-112-134")).thenReturn(true);
		when(estacionamiento.estaVigente()).thenReturn(true);
		when(estacionamiento.esSuCelular("03-03-456")).thenReturn(true);
		when(estacionamientoPuntual.estaVigente()).thenReturn(true);
		when(estacionamientoPuntual.esSuCelular("04-03-03")).thenReturn(false);
		

		sectorDeEstacionamiento.registrarEstacionamiento(estacionamiento);
		sectorDeEstacionamiento.registrarEstacionamiento(estacionamientoApp2);
		sectorDeEstacionamiento.registrarEstacionamiento(estacionamientoPuntual);
		
		assertTrue(sectorDeEstacionamiento.tieneEstacionamientoVigenteConCelular("03-03-456"));
		assertFalse(sectorDeEstacionamiento.tieneEstacionamientoVigenteConCelular("04-03-03"));
		assertFalse(sectorDeEstacionamiento.tieneEstacionamientoVigenteConCelular("12-112-134"));
	}
	@Test
	void cuandoSefinalizanTodosLosEstacionamientosNoEstanMasVigentes() {
		EstacionamientoApp estacionamiento = mock(EstacionamientoApp.class);
		when(estacionamiento.estaVigente()).thenReturn(true);
		when(estacionamiento.esSuPatente("11AA")).thenReturn(true);
		EstacionamientoPuntual estacionamiento2 = mock(EstacionamientoPuntual.class);
		when(estacionamiento2.estaVigente()).thenReturn(true);
		when(estacionamiento2.esSuPatente("22BB")).thenReturn(true);
		//agrego los estacionamientos vigentes
		sectorDeEstacionamiento.registrarEstacionamiento(estacionamiento);
		sectorDeEstacionamiento.registrarEstacionamiento(estacionamiento2);
		assertTrue(sectorDeEstacionamiento.tieneEstacionamientoVigenteConPatente("11AA"));
		assertTrue(sectorDeEstacionamiento.tieneEstacionamientoVigenteConPatente("22BB"));
		//finalizo los estacionamientos
		sectorDeEstacionamiento.finalizarTodosLosEstacionamientos();
		//verifico que hayan finalizado
		verify(estacionamiento).finalizar(sectorDeEstacionamiento.getControlSaldo()
										,sectorDeEstacionamiento.getPrecioPorHora());
		verify(estacionamiento2).finalizar(sectorDeEstacionamiento.getControlSaldo()
										,sectorDeEstacionamiento.getPrecioPorHora());
	}

	@Test
	void horarioDeEstacionamiento() {
		LocalTime las11Horas = LocalTime.of(11, 0);
		LocalTime las21Horas = LocalTime.of(21, 0);
		LocalTime las6Horas = LocalTime.of(6, 0);
		try(MockedStatic<LocalTime> localTimeMock = Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)) {
			
			localTimeMock.when(LocalTime::now).thenReturn(
					las11Horas,las11Horas,
					las21Horas,las21Horas,
					las6Horas,las6Horas);
		
		assertTrue(sectorDeEstacionamiento.esHorarioDeEstacionamiento());
		assertFalse(sectorDeEstacionamiento.esHorarioDeEstacionamiento());
		assertFalse(sectorDeEstacionamiento.esHorarioDeEstacionamiento());
		}
	}
}
