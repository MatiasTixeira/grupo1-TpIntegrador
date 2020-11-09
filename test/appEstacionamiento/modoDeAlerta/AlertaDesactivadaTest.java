package appEstacionamiento.modoDeAlerta;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import appEstacionamiento.AppEstacionamiento;
import appEstacionamiento.GUI;

class AlertaDesactivadaTest {
	AlertaDesactivada alertaD;
	GUI gui;
	AppEstacionamiento app;
	
	@BeforeEach
	public void setUp() {
		gui = mock(GUI.class);
		alertaD = new AlertaDesactivada();
		app = mock(AppEstacionamiento.class);
}
	@Test
	void cuandoComienzaACaminarNoHaceNada() {
		alertaD.comenzoACaminar(app,gui);
		verify(gui, never()).alert(any(String.class));
	}

	@Test
	void cuandoComienzaAManejarNoHaceNada() {
		alertaD.comenzoAManejar(app,gui);	
		verify(gui, never()).alert(any(String.class));
	}

}
