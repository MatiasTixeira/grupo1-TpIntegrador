package estacionamiento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import sectorDeSaldos.IControlSaldo;






class EstacionamientoAppTest {
	EstacionamientoApp estacionamiento;
	@Test
	void testConstructor() {
		
		estacionamiento = 
				new EstacionamientoApp("AAA-111",LocalTime.of(10, 0),LocalTime.of(20, 0),"03-03-456");
		
	assertTrue(estacionamiento.esSuPatente("AAA-111"));
	assertTrue(estacionamiento.estaVigente()); 
	assertEquals(LocalTime.of(10,0).getHour(), estacionamiento.getHoraInicio().getHour());
	assertEquals(LocalTime.of(20, 0).getHour(), estacionamiento.getHoraFin().getHour());
	assertTrue(estacionamiento.esSuCelular("03-03-456"));
	
		
	}
	
	@Test
	void cuandoFinalizaDejaDeEstarVigenteYSeteaLaHoraFinYLeAvisaAlControlDeSaldoCuantoDebeDescontar() {
		IControlSaldo controlSaldo = mock(IControlSaldo.class);
		estacionamiento = 
				new EstacionamientoApp("AAA-111",LocalTime.of(10, 0),LocalTime.of(20, 0),"03-03-456");
		assertTrue(estacionamiento.estaVigente());
		
		estacionamiento.finalizar(controlSaldo,40d);
		
		assertFalse(estacionamiento.estaVigente());
		assertEquals(LocalTime.now().getHour(), estacionamiento.getHoraFin().getHour());
		
		
	}
}
