package appEstacionamiento.modoDeAlerta;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;

import org.junit.jupiter.api.Test;

import appEstacionamiento.GUI;

class AlertaDesactivadaTest {

private AlertaDesactivada alertaD;
	
	@Test
	void cuandoComienzaACaminarNoHaceNada() {
		GUI gui = mock(GUI.class);
		alertaD = new AlertaDesactivada();
		
		alertaD.comenzoACaminar(gui);
		
		verify(gui, never()).alert("Deberías comenzar un estacionamiento.");
	}

	@Test
	void cuandoComienzaAManejarNoHaceNada() {
		GUI gui = mock(GUI.class);
		alertaD = new AlertaDesactivada();
		
		alertaD.comenzoAManejar(gui);
		
		verify(gui, never()).alert("Deberías finalizar tu estacionamiento.");
	}

}
