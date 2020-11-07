package appEstacionamiento.estadoDeMovimiento;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import appEstacionamiento.AppEstacionamiento;
import appEstacionamiento.estadoDeMoviemiento.Caminando;
import appEstacionamiento.estadoDeMoviemiento.Manejando;

class ManejandoTest {
	private Manejando manejando;
	private AppEstacionamiento appE;
	
	
	@Test
	void testConstructor() {
		appE = mock(AppEstacionamiento.class);
		manejando = new Manejando(appE);
		
		assertEquals(appE, manejando.getApp());
		

	}
	
	@Test
	void isDrivinNoHaceNada() {
		
		appE = mock(AppEstacionamiento.class);
		manejando = new Manejando(appE);
		
		
		manejando.isDriving();
		
		verify(appE,never()).setEstadoDeMovimiento(any(Caminando.class));
		verify(appE,never()).comenzoACaminar();
	}
	
	@Test
	void isWalkingSeteaElEstadoDeMovimientoEstaCaminandoEnLaAppYLeDiceQueComenzoACaminar() {
		
		appE = mock(AppEstacionamiento.class);
		manejando = new Manejando(appE);
		
		manejando.isWalking();
		
		verify(appE).setEstadoDeMovimiento(any(Caminando.class));
		verify(appE).comenzoACaminar();
		
		
	}

}
