package appEstacionamiento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import appEstacionamiento.estadoDeMoviemiento.EstadoDeMovimiento;
import appEstacionamiento.modoDeActivacion.ModoDeActivacion;
import appEstacionamiento.modoDeAlerta.ModoDeAlerta;
import espacioGeografico.GPS;
import espacioGeografico.Ubicacion;
import respuestas.Respuesta;
import sectorDeEstacionamiento.IControlDeEstacionamiento;
import sectorDeSaldos.IControlSaldo;
import sectorDeZonas.IControlZonas;
import serverEstacionamiento.IServerEstacionamientoApp;
import serverEstacionamiento.ServerEstacionamiento;

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
	private Ubicacion ubicacionInicial;
	private Respuesta res;
	
	@BeforeEach
	public void setUp() {
		estadoDeMovimiento = mock(EstadoDeMovimiento.class);
		modoDeAlerta = mock(ModoDeAlerta.class);
		modoDeActivacion = mock(ModoDeActivacion.class);
		server = mock(IServerEstacionamientoApp.class);
		nroCelular = "123-123-456";
		patente = "AA-11";
		gps = mock(GPS.class);
		gui = mock(GUI.class);
		res = mock(Respuesta.class);
		appE = new AppEstacionamiento(nroCelular,patente,estadoDeMovimiento
				,modoDeAlerta,modoDeActivacion,server,gps,gui);
		ubicacionInicial = mock(Ubicacion.class);
		when(gps.ubicacionActual()).thenReturn(this.ubicacionInicial);
	}
	
	@Test
	void testConstructor() {
		
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
	void respuestaInicioCuandoPuedeIniciarUnEstacionamientoLeEnviaAlServerElInicio_AlGuiLaRespuesta_YSeGuardaLaUbicacionCuandoLaRespuestaEsExitosa() {
		when(server.tieneEstacionamientoVigente(patente)).thenReturn(false);
		when(server.estaEnZonaDeEstacionamiento(ubicacionInicial)).thenReturn(true);
		when(server.estaEnHorario()).thenReturn(true);
		when(server.iniciarEstacionamiento(nroCelular, patente)).thenReturn(res);
		when(res.operacionExitosa()).thenReturn(true);
		appE.respuestaInicio();
		verify(appE.getServer()).iniciarEstacionamiento(appE.getNroCelular(), appE.getPatente());
		assertEquals(ubicacionInicial, appE.getUltimaUbicacionEst());
	}
	
	@Test
	void respuestaInicioCambiaLaUltimaUbicacionCuandoLaRespuestaEsExitosa() {
		when(server.tieneEstacionamientoVigente(patente)).thenReturn(true,false,true);
		when(server.estaEnZonaDeEstacionamiento(ubicacionInicial)).thenReturn(true);
		when(server.estaEnHorario()).thenReturn(true);
		when(server.iniciarEstacionamiento(nroCelular, patente)).thenReturn(res);
		when(res.operacionExitosa()).thenReturn(true);
		appE.respuestaInicio();
		appE.respuestaFin();
		Ubicacion ubi2 = mock(Ubicacion.class);
		when(this.gps.ubicacionActual()).thenReturn(ubi2);
		when(server.estaEnZonaDeEstacionamiento(ubi2)).thenReturn(true);
		appE.respuestaInicio();
		verify(appE.getServer()).iniciarEstacionamiento(appE.getNroCelular(), appE.getPatente());
		assertNotEquals(ubicacionInicial, appE.getUltimaUbicacionEst());
	}
	
	//------------------
	
	
	
	
	@Test
	void respuestaFinCuandoPuedeFinzalizarUnEstacionamientoLeAvisaAlServerQueFinaliza() {
		Respuesta respuesta = mock(Respuesta.class);
		
		when(respuesta.respuestaComoString()).thenReturn("Fue exitosa");
		when(server.finalizarEstacionamiento(nroCelular))
			.thenReturn(respuesta);
		when(server.tieneEstacionamientoVigente(patente)).thenReturn(false,true);
		when(server.estaEnZonaDeEstacionamiento(ubicacionInicial)).thenReturn(true);
		when(server.estaEnHorario()).thenReturn(true);
		when(server.iniciarEstacionamiento(nroCelular, patente)).thenReturn(res);
		when(res.operacionExitosa()).thenReturn(true);
		appE.respuestaInicio();
		appE.respuestaFin();
		
		verify(appE.getServer()).finalizarEstacionamiento(appE.getNroCelular());
		}
	
	// falta hacerlo
	@Test
	void respuestaFinCuandoNoPuedeFinzalizarUnEstacionamientoLeAvisaAlServerQueFinaliza() {
		Respuesta respuesta = mock(Respuesta.class);
		
		when(respuesta.respuestaComoString()).thenReturn("Fue exitosa");
		when(server.finalizarEstacionamiento(nroCelular))
			.thenReturn(respuesta);
		when(server.tieneEstacionamientoVigente(patente)).thenReturn(false,true);
		when(server.estaEnZonaDeEstacionamiento(ubicacionInicial)).thenReturn(true);
		when(server.estaEnHorario()).thenReturn(true);
		when(server.iniciarEstacionamiento(nroCelular, patente)).thenReturn(res);
		when(res.operacionExitosa()).thenReturn(true);
		appE.respuestaInicio();
		appE.respuestaFin();
		
		verify(appE.getServer()).finalizarEstacionamiento(appE.getNroCelular());
		}
	
	@Test
	void cuandoLeLlegaElMensajeDrivingSeLoEnviaASuEstadoDeMovimientoActual() {	
		appE.driving();
		verify(estadoDeMovimiento).isDriving();
	}
		
	@Test
	void cuandoLeLlegaElMensajeWalkinSeLoEnviaASuEstadoDeMovimientoActual() {	
		appE.walking();	
		verify(estadoDeMovimiento).isWalking();	
	}
	
	
}
