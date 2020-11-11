package appEstacionamiento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import appEstacionamiento.estadoDeMoviemiento.EstadoDeMovimiento;
import appEstacionamiento.modoDeActivacion.ModoDeActivacion;
import appEstacionamiento.modoDeAlerta.ModoDeAlerta;
import espacioGeografico.GPS;
import espacioGeografico.Ubicacion;
import respuestas.Respuesta;
import respuestas.RespuestaOperacionFallida;
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
	
	@Test
	void iniciarEstacionamientoPideUnaRespuestaYLaImprimeEnElGUI() {
		when(server.tieneEstacionamientoVigente(patente)).thenReturn(false);
		when(server.estaEnZonaDeEstacionamiento(ubicacionInicial)).thenReturn(true);
		when(server.estaEnHorario()).thenReturn(true);
		when(server.iniciarEstacionamiento(nroCelular, patente)).thenReturn(res);
		when(res.respuestaComoString()).thenReturn("Fue exitosa");
		
		appE.iniciarEstacionamiento();
		
		verify(this.gui).print("Fue exitosa");
	}
	
	@Test
	void finalizarEstacionamientoPideUnaRespuestaYLaImprimeEnElGUI() {
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
			
			appE.finalizarEstacionamiento();
			
			verify(this.gui).print("Fue exitosa");
	}
	
	
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
	
	@Test 
	void comenzoACaminarLeAvisaAlModoDeAlertaYAlModoDeActivacionQueComenzoACaminar() {
		
		appE.comenzoACaminar();
		
		verify(this.modoDeAlerta).comenzoACaminar(this.appE, this.gui);
		verify(this.modoDeActivacion).comenzoACaminar(this.appE, this.gui);
	}
	
	@Test
	void comenzoAManejarLeAvisaAlModoDeAlertaYAlModoDeActivacionQueComenzoAManejar() {

		
		appE.comenzoAManejar();
		
		verify(this.modoDeAlerta).comenzoAManejar(this.appE, this.gui);
		verify(this.modoDeActivacion).comenzoAManejar(this.appE, this.gui);
	}
	
	
	@Test
	void respuestaFinCuandoNoPuedeFinzalizarUnEstacionamientoPorLaZonaLeEnviaAlGuiElStringDeLaRespuestaFallida() {
		
		when(server.tieneEstacionamientoVigente(patente)).thenReturn(false);
		when(server.estaEnHorario()).thenReturn(false);
		when(server.estaEnZonaDeEstacionamiento(any(Ubicacion.class))).thenReturn(false);
		
		Respuesta respuestaEsperada =  appE.respuestaFin();
		String respuestaFallidaString = new RespuestaOperacionFallida().respuestaComoString();
		
		assertEquals(respuestaFallidaString, respuestaEsperada.respuestaComoString());

	}
	@Test
	void noPuedeIniciarEstacionamientoSiNoEstaEnHorario() {
		
		when(server.estaEnHorario()).thenReturn(false);
		when(server.tieneEstacionamientoVigente(patente)).thenReturn(false);
		when(server.estaEnZonaDeEstacionamiento(any(Ubicacion.class))).thenReturn(true);
		
		assertFalse(appE.puedeIniciarEstacionamiento());

	}
	@Test
	void noPuedeIniciarEstacionamientoSiDisponeYaDeUnEstacionamiento() {
		
		when(server.estaEnHorario()).thenReturn(true);
		when(server.tieneEstacionamientoVigente(patente)).thenReturn(true);
		when(server.estaEnZonaDeEstacionamiento(any(Ubicacion.class))).thenReturn(true);
		
		assertFalse(appE.puedeIniciarEstacionamiento());

	}
	@Test
	void noPuedeIniciarEstacionamientoSiNoEstaEnZonaDeEstacionamiento() {
		
		when(server.estaEnHorario()).thenReturn(true);
		when(server.tieneEstacionamientoVigente(patente)).thenReturn(false);
		when(server.estaEnZonaDeEstacionamiento(any(Ubicacion.class))).thenReturn(false);
		
		assertFalse(appE.puedeIniciarEstacionamiento());

	}
	@Test
	void cuandoPuedeIniciarEstacionamiento() {
		
		when(server.estaEnHorario()).thenReturn(true);
		when(server.tieneEstacionamientoVigente(patente)).thenReturn(false);
		when(server.estaEnZonaDeEstacionamiento(any(Ubicacion.class))).thenReturn(true);
		
		assertTrue(appE.puedeIniciarEstacionamiento());

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
	@Test
	void noPuedeFinalizarEstacionamientoSiNoTieneUnEstacionamientoVigente() {
		
		when(this.gps.ubicacionActual()).thenReturn(this.ubicacionInicial);
		when(server.estaEnHorario()).thenReturn(true);
		when(server.tieneEstacionamientoVigente(patente)).thenReturn(false);
		when(server.estaEnZonaDeEstacionamiento(any(Ubicacion.class))).thenReturn(true);
		appE.setUltimaUbicacionEst(ubicacionInicial);
		
		assertFalse(appE.puedeFinalizarEstacionamiento());

	}
	
	@Test
	void noPuedeFinalizarEstacionamientoSiNoEstaEnHorario() {
		
		when(this.gps.ubicacionActual()).thenReturn(this.ubicacionInicial);
		when(server.estaEnHorario()).thenReturn(false);
		when(server.tieneEstacionamientoVigente(patente)).thenReturn(true);
		when(server.estaEnZonaDeEstacionamiento(any(Ubicacion.class))).thenReturn(true);
		appE.setUltimaUbicacionEst(ubicacionInicial);
		
		assertFalse(appE.puedeFinalizarEstacionamiento());

	}
	
}
