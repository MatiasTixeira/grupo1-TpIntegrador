package appEstacionamiento.modoDeAlerta;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import appEstacionamiento.GUI;

import static org.mockito.Mockito.*;
class AlertaActivadaTest {
	
	private AlertaActivada alertaA;
	
	@Test
	void cuandoComienzaACaminarAlertaQueDeberiaEmpezarElEstacionamiento() {
		GUI gui = mock(GUI.class);
		alertaA = new AlertaActivada();
		
		alertaA.comenzoACaminar(gui);
		
		verify(gui).alert("Deberías comenzar un estacionamiento.");
	}

	@Test
	void cuandoComienzaAManejarAlertaQueDeberiaFinalizarElEstacionamiento() {
		GUI gui = mock(GUI.class);
		alertaA = new AlertaActivada();
		
		alertaA.comenzoAManejar(gui);
		
		verify(gui).alert("Deberías finalizar tu estacionamiento.");
	}
}
