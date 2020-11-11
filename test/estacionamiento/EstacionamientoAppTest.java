package estacionamiento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import sectorDeSaldos.IControlSaldo;






class EstacionamientoAppTest {
	EstacionamientoApp estacionamiento;
	@Test
	void testConstructor() {
		
		estacionamiento = 
				new EstacionamientoApp("AAA-111",LocalTime.of(10, 0),LocalTime.of(20, 0),"03-03-456");
	LocalTime las16Horas = LocalTime.of(16, 0);
	try(MockedStatic<LocalTime> localTimeMock = Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)) {
		
		localTimeMock.when(LocalTime::now).thenReturn(las16Horas);
		assertTrue(estacionamiento.estaVigente());
	}	
	assertTrue(estacionamiento.esSuPatente("AAA-111")); 
	assertEquals(LocalTime.of(10,0).getHour(), estacionamiento.getHoraInicio().getHour());
	assertEquals(LocalTime.of(20, 0).getHour(), estacionamiento.getHoraFin().getHour());
	assertTrue(estacionamiento.esSuCelular("03-03-456"));
	
		
	}
	
	@Test
	void cuandoFinalizaDejaDeEstarVigenteYSeteaLaHoraFinYLeAvisaAlControlDeSaldoCuantoDebeDescontar() {
		IControlSaldo controlSaldo = mock(IControlSaldo.class);
		estacionamiento = 
				new EstacionamientoApp("AAA-111",LocalTime.of(10, 0),LocalTime.of(20, 0),"03-03-456");
		LocalTime las16Horas = LocalTime.of(16, 0);
		
		try(MockedStatic<LocalTime> localTimeMock = Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)) {
					
					localTimeMock.when(LocalTime::now).thenReturn(las16Horas);
					estacionamiento.finalizar(controlSaldo,40d);
					verify(controlSaldo).descontar(estacionamiento.getNumeroCelular(), estacionamiento.costo(40d));
					assertFalse(estacionamiento.estaVigente());
					assertEquals(LocalTime.now().getHour(), estacionamiento.getHoraFin().getHour());
					
		}	
		
	}
	@Test
	void cuandoFinalizaYNoEstaVigentePorElHorario() {
		IControlSaldo controlSaldo = mock(IControlSaldo.class);
		LocalTime las20Horas = LocalTime.of(20, 0);
		estacionamiento = 
				new EstacionamientoApp("AAA-111",LocalTime.of(10, 0),LocalTime.of(16, 0),"03-03-456");
		
		try(MockedStatic<LocalTime> localTimeMock = Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)) {
					
					localTimeMock.when(LocalTime::now).thenReturn(las20Horas);
				assertFalse(estacionamiento.estaVigente());
				estacionamiento.finalizar(controlSaldo,40d);
				assertFalse(estacionamiento.getEstaActivo());
				verify(controlSaldo).descontar(estacionamiento.getNumeroCelular(), estacionamiento.costo(40d));
				assertEquals(LocalTime.of(16, 0), estacionamiento.getHoraFin());
		}	
	}
	@Test
	void cuandoFinalizaUnEstacionamientoYNoEstaActivoNoHaceNada() {
		IControlSaldo controlSaldo = mock(IControlSaldo.class);
		estacionamiento = 
				new EstacionamientoApp("AAA-111",LocalTime.of(10, 0),LocalTime.of(16, 0),"03-03-456");
		estacionamiento.setEstaActivo(false);
		
		estacionamiento.finalizar(controlSaldo, 40d);
		assertFalse(estacionamiento.estaVigente());
		assertFalse(estacionamiento.getEstaActivo());
		assertEquals(LocalTime.of(16, 0), estacionamiento.getHoraFin());
		verifyNoMoreInteractions(controlSaldo);
	}
	@Test
	void cantidadDeHorasDeUnEstacionamiento() {
		estacionamiento = 
				new EstacionamientoApp("AAA-111",LocalTime.of(10, 0),LocalTime.of(16, 0),"03-03-456");
		EstacionamientoApp estacionamiento2 = 
				new EstacionamientoApp("AAA-111",LocalTime.of(10, 0),LocalTime.of(20, 0),"03-03-456");
		EstacionamientoApp estacionamiento3 = 
				new EstacionamientoApp("AAA-111",LocalTime.of(10, 0),LocalTime.of(10, 40),"03-03-456");
		
		assertEquals(6,estacionamiento.cantidadDeHoras());
		assertEquals(10,estacionamiento2.cantidadDeHoras());
		assertEquals(1,estacionamiento3.cantidadDeHoras());
	}
	
}
