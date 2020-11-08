package appEstacionamiento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import appEstacionamiento.estadoDeMoviemiento.EstadoDeMovimiento;
import appEstacionamiento.modoDeActivacion.ModoDeActivacion;
import appEstacionamiento.modoDeAlerta.ModoDeAlerta;
import espacioGeografico.GPS;
import espacioGeografico.Ubicacion;
import respuestas.Respuesta;
import serverEstacionamiento.IServerEstacionamientoApp;

class AppEstacionamientoTest {
	private AppEstacionamiento appE;
	private String nroCelular;
	private String patente;
	private EstadoDeMovimiento estadoDeMovimiento;
	private ModoDeAlerta modoDeAlerta;
	private ModoDeActivacion modoDeActivacion;
	private IServerEstacionamientoApp server;
	private GPS gps;
	private GUI gui;
	
	@Test
	void testConstructor() {
		nroCelular = "123-123-456";
		patente = "AA-11";
		estadoDeMovimiento = mock(EstadoDeMovimiento.class);
		modoDeAlerta = mock(ModoDeAlerta.class);
		modoDeActivacion = mock(ModoDeActivacion.class);
		server = mock(IServerEstacionamientoApp.class);
		gps = mock(GPS.class);
		gui = mock(GUI.class);
		appE = new AppEstacionamiento(nroCelular,patente,estadoDeMovimiento
									,modoDeAlerta,modoDeActivacion,server,gps,gui);
		
		assertEquals(nroCelular, appE.getNroCelular());
		assertEquals(patente, appE.getPatente());
		assertEquals(estadoDeMovimiento, appE.getEstadoDeMovimiento());
		assertEquals(modoDeAlerta, appE.getModoDeAlerta());
		assertEquals(modoDeActivacion, appE.getModoDeActivacion());
		assertEquals(server, appE.getServer());
		assertEquals(gps, appE.getGps());
		assertEquals(gui, appE.getGui());
	}
	
	@Test
	void cuandoIniciaEstacionamientoLeEnviaAlServerElInicio_AlGuiLaRespuesta_YSeGuardaLaUbicacion() {
		nroCelular = "123-123-456";
		patente = "AA-11";
		estadoDeMovimiento = mock(EstadoDeMovimiento.class);
		modoDeAlerta = mock(ModoDeAlerta.class);
		modoDeActivacion = mock(ModoDeActivacion.class);
		server = mock(IServerEstacionamientoApp.class);
		gps = mock(GPS.class);
		gui = mock(GUI.class);
		Respuesta respuesta = mock(Respuesta.class);
		Ubicacion ub= mock(Ubicacion.class);
		when(respuesta.respuestaComoString()).thenReturn("Fue exitosa");
		when(server.iniciarEstacionamiento(nroCelular, patente))
			.thenReturn(respuesta);
		when(gps.ubicacionActual()).thenReturn(ub);
		
		appE = new AppEstacionamiento(nroCelular,patente,estadoDeMovimiento
									,modoDeAlerta,modoDeActivacion,server,gps,gui);
		
		appE.iniciarEstacionamiento();
		
		verify(appE.getServer()).iniciarEstacionamiento(appE.getNroCelular(), appE.getPatente());
		verify(appE.getGui()).print("Fue exitosa");
		assertEquals(ub, appE.getUltimaUbicacionEst());
		
	}
	
	@Test
	void cuandoFinalizaElEstacionamientoLeAvisaAlServerQueFinalizaYEnviaLaRespuestaAlGui() {
		nroCelular = "123-123-456";
		patente = "AA-11";
		estadoDeMovimiento = mock(EstadoDeMovimiento.class);
		modoDeAlerta = mock(ModoDeAlerta.class);
		modoDeActivacion = mock(ModoDeActivacion.class);
		server = mock(IServerEstacionamientoApp.class);
		gps = mock(GPS.class);
		gui = mock(GUI.class);
		Respuesta respuesta = mock(Respuesta.class);
		when(respuesta.respuestaComoString()).thenReturn("Fue exitosa");
		when(server.finalizarEstacionamiento(nroCelular))
			.thenReturn(respuesta);
		
		appE = new AppEstacionamiento(nroCelular,patente,estadoDeMovimiento
									,modoDeAlerta,modoDeActivacion,server,gps,gui);
		
		appE.finalizarEstacionamiento();
		
		verify(appE.getServer()).finalizarEstacionamiento(appE.getNroCelular());
		verify(appE.getGui()).print("Fue exitosa");
		}
	
		@Test
		void cuandoLeLlegaElMensajeDrivingSeLoEnviaASuEstadoDeMovimientoActual() {
		nroCelular = "123-123-456";
		patente = "AA-11";
		estadoDeMovimiento = mock(EstadoDeMovimiento.class);
		modoDeAlerta = mock(ModoDeAlerta.class);
		modoDeActivacion = mock(ModoDeActivacion.class);
		server = mock(IServerEstacionamientoApp.class);
		gps = mock(GPS.class);
		gui = mock(GUI.class);
		appE = new AppEstacionamiento(nroCelular,patente,estadoDeMovimiento
									,modoDeAlerta,modoDeActivacion,server,gps,gui);
		
		appE.driving();
		
		verify(estadoDeMovimiento).isDriving();
		}
		
		@Test
		void cuandoLeLlegaElMensajeWalkinSeLoEnviaASuEstadoDeMovimientoActual() {
			nroCelular = "123-123-456";
			patente = "AA-11";
			estadoDeMovimiento = mock(EstadoDeMovimiento.class);
			modoDeAlerta = mock(ModoDeAlerta.class);
			modoDeActivacion = mock(ModoDeActivacion.class);
			server = mock(IServerEstacionamientoApp.class);
			gps = mock(GPS.class);
			gui = mock(GUI.class);
			appE = new AppEstacionamiento(nroCelular,patente,estadoDeMovimiento
										,modoDeAlerta,modoDeActivacion,server,gps,gui);
			
			appE.walking();
			
			verify(estadoDeMovimiento).isWalking();
			
		}
		
	

}
