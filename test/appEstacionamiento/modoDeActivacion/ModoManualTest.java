package appEstacionamiento.modoDeActivacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import appEstacionamiento.GUI;
import respuestas.Respuesta;
import serverEstacionamiento.IServerEstacionamientoApp;

class ModoManualTest {
	private ModoManual modoManual;
	
	@Test
	void cuandoLeLlegaElMensajeComenzoACaminarNoHaceNada() {
		IServerEstacionamientoApp server = mock(IServerEstacionamientoApp.class);
		String nroCelular = "03-03-456";
		GUI gui = mock(GUI.class);
		String patente = "AA-22";
		modoManual = new ModoManual();
		
		modoManual.comenzoACaminar(server, patente, nroCelular, gui);
		
		verify(server,never()).iniciarEstacionamiento(any(String.class), any(String.class));
		verify(gui,never()).print(any(String.class));
		
		
	}
	
	@Test
	void cuandoLeLlegaElMensajeComenzoAManejarNoHaceNada() {
		IServerEstacionamientoApp server = mock(IServerEstacionamientoApp.class);
		String nroCelular = "03-03-456";
		GUI gui = mock(GUI.class);
		modoManual = new ModoManual();
		
		modoManual.comenzoAManejar(server, nroCelular, gui);
		
		verify(server,never()).finalizarEstacionamiento(any(String.class));
		verify(gui,never()).print(any(String.class));
		
	}

}
