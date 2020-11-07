package serverDeEstacionamiento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import espacioGeografico.Ubicacion;
import estacionamiento.Estacionamiento;
import respuestas.RespuestaFinEstacionamiento;
import respuestas.RespuestaInicioEstacionamiento;
import respuestas.RespuestaSinSaldo;
import sectorDeEstacionamiento.IControlDeEstacionamiento;
import sectorDeSaldos.IControlSaldo;
import sectorDeZonas.IControlZonas;
import serverEstacionamiento.IServerEstacionamientoApp;
import serverEstacionamiento.ServerEstacionamiento;

class ServerDeEstacionamientoTest {
	IControlDeEstacionamiento controlEstacionamiento;
	IControlSaldo controlSaldo;
	IControlZonas controlZonas;
	ServerEstacionamiento server;
	
	@BeforeEach
	public void setUp() {
		controlEstacionamiento = mock(IControlDeEstacionamiento.class);
		controlSaldo = mock(IControlSaldo.class);
	    controlZonas = mock(IControlZonas.class);
	    server = new ServerEstacionamiento(controlEstacionamiento,controlSaldo,controlZonas);
	    when(controlEstacionamiento.getPrecioPorHora()).thenReturn(40d);
	    when(controlEstacionamiento.getHoraFin()).thenReturn(LocalTime.of(20, 00));
	}
		
	@Test
	void unServerDeEstacionamientoPuedeFinalizarUnEstacionamientoYEsteDevuelveUnaRespuesta() {
		String celu = "1124600909";
		LocalTime horaInicio = LocalTime.of(10, 0);
		LocalTime horaFin = LocalTime.of(15, 0);
		Estacionamiento est = mock(Estacionamiento.class);
		when(est.getHoraInicio()).thenReturn(horaInicio);
		when(est.getHoraFin()).thenReturn(horaFin);
		when(controlEstacionamiento.estacionamientoVigente(celu)).thenReturn(est);
		RespuestaFinEstacionamiento res = 
				(RespuestaFinEstacionamiento) server.finalizarEstacionamiento(celu);
		assertEquals(res.getCantHoras(),5);
		assertEquals(res.getCosto(),200);
		assertEquals(res.getHoraFin(),horaFin);
		assertEquals(res.getHoraInicio(),horaInicio);
	}
	
	@Test
	void elServerPuedeIniciarUnEstacionamientoYCuandoHaySaldoDaUnaRespuestaInicioDeEstacionamiento() {
		String celu = "1124600909";
		String patente = "ABC123";
		LocalTime horaInicio = LocalTime.now();
		LocalTime horaFin = LocalTime.of(20, 0);
		when(controlSaldo.saldo(celu)).thenReturn(2000d);
		Estacionamiento est = mock(Estacionamiento.class);
		when(est.getHoraInicio()).thenReturn(horaInicio);
		when(est.getHoraFin()).thenReturn(horaFin);
		RespuestaInicioEstacionamiento res = 
				(RespuestaInicioEstacionamiento) server.iniciarEstacionamiento(celu,patente);
		assertEquals(res.getHoraFin(),horaFin);
		assertEquals(res.getHoraInicio().getHour(),horaInicio.getHour());
		//Si no ponemos por horas falla por milisegundos
	}	

	//Integer horaFinal = 
    //		controlEstacionamiento.getHoraFin().getHour() - LocalTime.now().getHour();
	
	
	@Test
	void elServerPuedeIniciarUnEstacionamientoPeroSiNoTieneSaldoEsteDevuelveUnaRespuestaSinSaldo() {
		String celu = "1124600909";
		String patente = "ABC123";
		when(controlSaldo.saldo(celu)).thenReturn(0d);
		RespuestaSinSaldo res = 
				(RespuestaSinSaldo) server.iniciarEstacionamiento(celu,patente);
		assertEquals(res.respuestaComoString(),"Saldo insuficiente. Estacionamiento no permitido.");
	}
	
	
	
	
	@Test
	void cuandoUnEstacionamientoEstaEnZonaDeEstacionamientosEsteRetornaTrue() {
		Ubicacion ubicacion = mock(Ubicacion.class);
		when(controlZonas.perteneceAUnaZonaDeEstacionamiento(ubicacion)).thenReturn(true);
		server.estaEnZonaDeEstacionamiento(ubicacion);
	}

	@Test
	void cuandoUnEstacionamientoVigenteEsteRetornaTrue() {
		String patente = "112233";
		when(controlEstacionamiento.tieneEstacionamientoVigenteConPatente(patente)).thenReturn(true);
		server.tieneEstacionamientoVigente(patente);
	}
	
}
