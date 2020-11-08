package appEstacionamiento.modoDeActivacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import appEstacionamiento.GUI;
import respuestas.Respuesta;
import serverEstacionamiento.IServerEstacionamientoApp;

class ModoAutomaticoTest {

private ModoAutomatico modoAutomatico;
	
	@Test 
	void cuandoLeLlegaElMensajeComenzoACaminarElServerIniciaElEstacionamientoElServerIniciaElEstacionamientoYSeEnviaLasRespuestasAlGui() {
		IServerEstacionamientoApp server = mock(IServerEstacionamientoApp.class);
		String nroCelular = "03-03-456";
		GUI gui = mock(GUI.class);
		Respuesta respuesta = mock(Respuesta.class);
		String patente = "AA-22";
		when(respuesta.respuestaComoString()).thenReturn("Fue exitosa");
		when(server.iniciarEstacionamiento(patente, nroCelular)).thenReturn(respuesta);
		modoAutomatico = new ModoAutomatico();
		
		modoAutomatico.comenzoACaminar(server, patente, nroCelular, gui);
		
		verify(server).iniciarEstacionamiento(patente, nroCelular);
		verify(gui).print("Fue exitosa");
		verify(gui).print("Esta operación fue realizada de manera automática");
		 
		
		
	}
	
	@Test
	void cuandoLeLlegaElMensajeComenzoAManejarElServerFinalizaElEstacionamientoYSeEnviaLasRespuestasAlGui() {
		IServerEstacionamientoApp server = mock(IServerEstacionamientoApp.class);
		String nroCelular = "03-03-456";
		GUI gui = mock(GUI.class);
		Respuesta respuesta = mock(Respuesta.class);
		String patente = "AA-22";
		when(respuesta.respuestaComoString()).thenReturn("Fue exitosa");
		when(server.finalizarEstacionamiento(nroCelular)).thenReturn(respuesta);
		modoAutomatico = new ModoAutomatico();
		
		modoAutomatico.comenzoAManejar(server, nroCelular, gui);
		
		verify(server).finalizarEstacionamiento(nroCelular);
		verify(gui).print(respuesta.respuestaComoString());
		verify(gui).print("Esta operación fue realizada de manera automática");
		
	}

}
