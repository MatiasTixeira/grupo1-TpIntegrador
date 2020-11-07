package appEstacionamiento.estadoDeMovimiento;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import appEstacionamiento.AppEstacionamiento;
import appEstacionamiento.estadoDeMoviemiento.Caminando;
import appEstacionamiento.estadoDeMoviemiento.Manejando;

class CaminandoTest {
	
	private AppEstacionamiento appE;
	private Caminando caminando;
		
	@Test
	void testConstructor() {
		appE = mock(AppEstacionamiento.class);
		caminando = new Caminando(appE);
			
		assertEquals(appE, caminando.getApp());
			

	}
		
	@Test
	void isWalkingNoHaceNada() {
			
		appE = mock(AppEstacionamiento.class);
		caminando = new Caminando(appE);			
			
		caminando.isWalking();
			
		verify(appE,never()).setEstadoDeMovimiento(any(Caminando.class));
		verify(appE,never()).comenzoAManejar();			
	}
		
	@Test
	void isDrivingSeteaElEstadoDeMovimientoDrivingEnLaAppYLeDiceQueComenzoAManejar() {
			
		appE = mock(AppEstacionamiento.class);
		caminando = new Caminando(appE);
			
		caminando.isDriving();
			
		verify(appE).setEstadoDeMovimiento(any(Manejando.class));
		verify(appE).comenzoAManejar();
			
			
	}

}

