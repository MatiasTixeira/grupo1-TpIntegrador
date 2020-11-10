package appEstacionamiento.modoDeActivacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import appEstacionamiento.AppEstacionamiento;
import appEstacionamiento.GUI;
import respuestas.Respuesta;
import sectorDeZonas.SectorDeZonas;
import sectorDeZonas.ZonaDeEstacionamiento;
import serverEstacionamiento.IServerEstacionamientoApp;

class ModoAutomaticoTest {

private ModoAutomatico modoAutomatico;
	IServerEstacionamientoApp server;
	AppEstacionamiento app;
	Respuesta respuesta;
	String nroCelular;
	String patente;
	GUI gui;

	@BeforeEach
	public void setUp() {
		server = mock(IServerEstacionamientoApp.class);
		app = mock(AppEstacionamiento.class);
		respuesta = mock(Respuesta.class);
		modoAutomatico = new ModoAutomatico();
		nroCelular = "03-03-456";
		patente = "AA-22";
		gui = mock(GUI.class);
		when(app.respuestaInicio()).thenReturn(respuesta);
		when(app.respuestaFin()).thenReturn(respuesta);
	}


	@Test 
	void cuandoLeLlegaElMensajeComenzoACaminarElServerIniciaElEstacionamientoYSeEnviaLasRespuestasAlGui() {
		when(respuesta.respuestaComoString()).thenReturn("Fue exitosa");
		when(respuesta.operacionExitosa()).thenReturn(true);
		modoAutomatico.comenzoACaminar(app,gui);
		
		verify(app).respuestaInicio();
		verify(gui).print("Fue exitosa");
		verify(gui).print("Esta operacion fue realizada de manera automatica");

		
	}
	
	@Test 
	void cuandoLeLlegaElMensajeComenzoACaminarYElServerNoIniciaElEstacionamientoYSeEnviaLasRespuestasAlGui() {
		when(respuesta.operacionExitosa()).thenReturn(false);
		modoAutomatico.comenzoACaminar(app,gui);
	
		verify(app).respuestaInicio();
		verify(gui, never()).print(any(String.class));
	}
	
	@Test
	void cuandoLeLlegaElMensajeComenzoAManejarElServerFinalizaElEstacionamientoYSeEnviaLasRespuestasAlGui() {
		when(respuesta.respuestaComoString()).thenReturn("Fue exitosa");
		when(respuesta.operacionExitosa()).thenReturn(true);
		modoAutomatico.comenzoAManejar(app, gui);
		
		verify(app).respuestaFin();
		verify(gui).print("Fue exitosa");
		verify(gui).print("Esta operacion fue realizada de manera automatica");
		
	}
	
	@Test
	void cuandoLeLlegaElMensajeComenzoAManejarYElServerNoFinalizaElEstacionamientoYSeEnviaLasRespuestasAlGui() {
		when(respuesta.operacionExitosa()).thenReturn(false);
		modoAutomatico.comenzoAManejar(app, gui);
		
		verify(app).respuestaFin();
		verify(gui, never()).print(any(String.class));
	}

}
