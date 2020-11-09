package appEstacionamiento.modoDeActivacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import appEstacionamiento.AppEstacionamiento;
import appEstacionamiento.GUI;

class ModoManualTest {
	private ModoManual modoManual;
	AppEstacionamiento app;
	String nroCelular;
	String patente;
	GUI gui;
	
	@BeforeEach
	public void setUp() {
		app = mock(AppEstacionamiento.class);
		modoManual = new ModoManual();
		nroCelular = "03-03-456";
		patente = "AA-22";
		gui = mock(GUI.class);
	}
	
	@Test
	void cuandoLeLlegaElMensajeComenzoACaminarNoHaceNada() {
		modoManual.comenzoACaminar(app, gui);
		verify(app,never()).iniciarEstacionamiento();
		verify(gui,never()).print(any(String.class));
	}
	
	@Test
	void cuandoLeLlegaElMensajeComenzoAManejarNoHaceNada() {
		modoManual.comenzoAManejar(app, gui);
		verify(app,never()).finalizarEstacionamiento();
		verify(gui,never()).print(any(String.class));
		
	}

}
