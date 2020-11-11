package estacionamiento;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import compras.CompraPuntual;
import sectorDeSaldos.IControlSaldo;

class EstacionamientoPuntualTest {
	private EstacionamientoPuntual estacionamiento;
	private CompraPuntual compra;
	@BeforeEach
	public void setUp() {
		compra = mock(CompraPuntual.class);
		estacionamiento = 
				new EstacionamientoPuntual("AAA-111",LocalTime.of(10, 0),LocalTime.of(20, 0),compra);
	}
	@Test
	void testConstructor() {
		compra = mock(CompraPuntual.class);
		estacionamiento = 
				new EstacionamientoPuntual("AAA-111",LocalTime.of(10, 0),LocalTime.of(20, 0),compra);
	LocalTime las16Horas = LocalTime.of(16, 0);
	try(MockedStatic<LocalTime> localTimeMock = Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)) {
		
		localTimeMock.when(LocalTime::now).thenReturn(las16Horas);
		assertTrue(estacionamiento.estaVigente()); 

	}
	assertEquals(LocalTime.of(10,0), estacionamiento.getHoraInicio());
	assertEquals(LocalTime.of(20, 0), estacionamiento.getHoraFin());
	assertFalse(estacionamiento.esSuCelular("03-03-456"));
	assertEquals(compra, estacionamiento.getCompra());
	assertTrue(estacionamiento.esSuPatente("AAA-111"));
		
	}
	@Test
	void vigenciaDelEstacionamiento() {
		LocalTime las11Horas = LocalTime.of(11, 0);
		LocalTime las21Horas = LocalTime.of(21, 0);
		LocalTime las6Horas = LocalTime.of(6, 0);
		IControlSaldo controlSaldo = mock(IControlSaldo.class) ;
		try(MockedStatic<LocalTime> localTimeMock = Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)) {

			localTimeMock.when(LocalTime::now).thenReturn(
													las11Horas,
													las21Horas,
													las6Horas );
			
						        		
					
		assertTrue(estacionamiento.estaVigente());
		assertFalse(estacionamiento.estaVigente());
		estacionamiento.finalizar(controlSaldo, 40d);
		
		estacionamiento.setEstaActivo(false);
		assertFalse(estacionamiento.estaVigente());
		}
		
	}
	
	@Test
	void cuandoFinaliza() {
		compra = mock(CompraPuntual.class);
		IControlSaldo controlSaldo = mock(IControlSaldo.class) ;
		estacionamiento = 
				new EstacionamientoPuntual("AAA-111",LocalTime.of(10, 0),LocalTime.of(20, 0),compra);
		LocalTime las16Horas = LocalTime.of(16, 0);
		try(MockedStatic<LocalTime> localTimeMock = Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)) {
			
			localTimeMock.when(LocalTime::now).thenReturn(las16Horas);
			assertTrue(estacionamiento.estaVigente());
		}
		estacionamiento.finalizar(controlSaldo, 40d );
		assertFalse(estacionamiento.estaVigente());
		verifyNoMoreInteractions(controlSaldo);
		
		
	}

}
