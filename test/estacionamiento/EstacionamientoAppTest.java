package estacionamiento;

import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalTime;

import org.junit.jupiter.api.Test;






class EstacionamientoAppTest {
	EstacionamientoApp estacionamiento;
	@Test
	void testConstructor() {
		
		estacionamiento = 
				new EstacionamientoApp("AAA-111",LocalTime.of(10, 0),LocalTime.of(14, 0),"03-03-456");
		
	assertTrue(estacionamiento.esSuPatente("AAA-111"));
	assertTrue(estacionamiento.estaVigente()); 
	assertEquals(LocalTime.of(10,0), estacionamiento.getHoraInicio());
	assertEquals(LocalTime.of(14, 0), estacionamiento.getHoraFin());
	assertTrue(estacionamiento.esSuCelular("03-03-456"));
	
		
	}
	
	@Test
	void cuandoFinaliza() {
		
		estacionamiento = 
				new EstacionamientoApp("AAA-111",LocalTime.of(10, 0),LocalTime.of(14, 0),"03-03-456");
		assertTrue(estacionamiento.estaVigente());
		
		estacionamiento.finalizar();
		
		assertFalse(estacionamiento.estaVigente());
		
		
	}
}
