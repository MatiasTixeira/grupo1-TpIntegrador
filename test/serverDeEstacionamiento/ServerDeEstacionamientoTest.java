package serverDeEstacionamiento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import espacioGeografico.Ubicacion;
import estacionamiento.Estacionamiento;
import estacionamiento.EstacionamientoApp;
import respuestas.Respuesta;
import respuestas.RespuestaFinEstacionamiento;
import respuestas.RespuestaInicioEstacionamiento;
import respuestas.RespuestaSinSaldo;
import sectorDeEstacionamiento.IControlDeEstacionamiento;
import sectorDeSaldos.IControlSaldo;
import sectorDeZonas.IControlZonas;
import serverEstacionamiento.ServerEstacionamiento;

class ServerDeEstacionamientoTest {
	IControlDeEstacionamiento controlEstacionamiento;
	IControlSaldo controlSaldo;
	IControlZonas controlZonas;
	ServerEstacionamiento server;
	String nroCelular;
	String patente;

	@BeforeEach
	public void setUp() {
		this.controlEstacionamiento = mock(IControlDeEstacionamiento.class);
		this.controlSaldo = mock(IControlSaldo.class);
		this.controlZonas = mock(IControlZonas.class);
		this.nroCelular = "1124600909";
		this.patente = "ABC123";
	    server = new ServerEstacionamiento(controlEstacionamiento,controlSaldo,controlZonas);
	    when(controlEstacionamiento.getPrecioPorHora()).thenReturn(40d);
	    when(controlEstacionamiento.getHoraFin()).thenReturn(LocalTime.of(20, 00));
	}

	@Test
	void iniciarEstacionamientoRetornaRespuestaSinSaldoCuandoElNumeroNoTienSaldo() {
		when(this.controlSaldo.saldo(this.nroCelular)).thenReturn(0d);

		Respuesta res = this.server.iniciarEstacionamiento(nroCelular, patente);

		assertTrue(res instanceof RespuestaSinSaldo);
	}

	@Test
	void iniciarEstacionamientoCon1000000DeSaldoALas9PermiteEstacionarHastaLas20() {
		when(this.controlSaldo.saldo(this.nroCelular)).thenReturn(1_000_000d);
		LocalTime horaActual = LocalTime.of(9, 0);

		try(MockedStatic<LocalTime> localTimeMock = Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)) {

			localTimeMock.when(LocalTime::now).thenReturn(horaActual);

			RespuestaInicioEstacionamiento res =
					(RespuestaInicioEstacionamiento) this.server.iniciarEstacionamiento(this.nroCelular, this.patente);

			verify(this.controlEstacionamiento).registrarEstacionamiento(any(Estacionamiento.class));
			assertEquals(LocalTime.of(9, 0), res.getHoraInicio());
			assertEquals(LocalTime.of(20, 0), res.getHoraFin());
		}
	}

	@Test
	void iniciarEstacionamientoCon100DeSaldoALas18PermiteEstacionarHastaLas20() {
		when(this.controlSaldo.saldo(this.nroCelular)).thenReturn(100d);
		LocalTime horaActual = LocalTime.of(18, 0);

		try(MockedStatic<LocalTime> localTimeMock = Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)) {

			localTimeMock.when(LocalTime::now).thenReturn(horaActual);

			RespuestaInicioEstacionamiento res =
					(RespuestaInicioEstacionamiento) this.server.iniciarEstacionamiento(this.nroCelular, this.patente);

			verify(this.controlEstacionamiento).registrarEstacionamiento(any(Estacionamiento.class));
			assertEquals(LocalTime.of(18, 0), res.getHoraInicio());
			assertEquals(LocalTime.of(20, 0), res.getHoraFin());
		}
	}
 
	@Test
	void iniciarEstacionamientoCon60DeSaldoALas14PermiteEstacionarHastaLas15Y30() {
		when(this.controlSaldo.saldo(this.nroCelular)).thenReturn(60d);
		LocalTime horaActual = LocalTime.of(14, 0);

		try(MockedStatic<LocalTime> localTimeMock = Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)) {

			localTimeMock.when(LocalTime::now).thenReturn(horaActual);

			RespuestaInicioEstacionamiento res =
					(RespuestaInicioEstacionamiento) this.server.iniciarEstacionamiento(this.nroCelular, this.patente);

			verify(this.controlEstacionamiento).registrarEstacionamiento(any(Estacionamiento.class));
			assertEquals(LocalTime.of(14, 0), res.getHoraInicio());
			assertEquals(LocalTime.of(15, 30), res.getHoraFin());
		}
	}

	@Test
	void finalizarEstacionamientoEncuentraUnEstacionamientoLoFinalizaYEnviaUnaRespuesta() {
		EstacionamientoApp estacionamiento = mock(EstacionamientoApp.class);
		when(this.controlEstacionamiento.estacionamientoVigente(this.nroCelular))
			.thenReturn(estacionamiento);

		Respuesta res = this.server.finalizarEstacionamiento(this.nroCelular);

		verify(estacionamiento).finalizar(this.controlSaldo, this.controlEstacionamiento.getPrecioPorHora());
		assertTrue(res instanceof RespuestaFinEstacionamiento);
	}

	@Test
	void estaEnZonaDeEstacionamientoSeComunicaConControlZonas() {
		Ubicacion ubicacion = mock(Ubicacion.class);

		server.estaEnZonaDeEstacionamiento(ubicacion);

		verify(this.controlZonas).perteneceAUnaZonaDeEstacionamiento(ubicacion);
	}

	@Test
	void tieneEstacionamientoVigenteSeComunicaConControlEstacionamientos() {
		server.tieneEstacionamientoVigente(this.patente);

		verify(this.controlEstacionamiento).tieneEstacionamientoVigenteConPatente(this.patente);
	}

	@Test
	void getHoraFinSeComunicaConControlEstacionamientos() {
		server.getHoraFin();

		verify(this.controlEstacionamiento).getHoraFin();
	}

	@Test
	void getHoraInicioSeComunicaConControlEstacionamientos() {
		server.getHoraInicio();

		verify(this.controlEstacionamiento).getHoraInicio();
	}

}
