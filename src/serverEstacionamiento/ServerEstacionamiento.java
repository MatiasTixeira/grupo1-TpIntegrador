package serverEstacionamiento;

import java.time.LocalTime;

import espacioGeografico.Ubicacion;
import estacionamiento.EstacionamientoApp;
import respuestas.Respuesta;
import respuestas.RespuestaFinEstacionamiento;
import respuestas.RespuestaInicioEstacionamiento;
import respuestas.RespuestaSinSaldo;
import sectorDeEstacionamiento.IControlDeEstacionamiento;
import sectorDeSaldos.IControlSaldo;
import sectorDeZonas.IControlZonas;

public class ServerEstacionamiento implements IServerEstacionamientoApp {
	private IControlDeEstacionamiento controlEstacionamiento;
	private IControlSaldo controlSaldo;
	private IControlZonas controlZonas;

	public ServerEstacionamiento(
			IControlDeEstacionamiento cE,
			IControlSaldo cS,
			IControlZonas cZ) {
		this.setControlEstacionamiento(cE);
		this.setControlSaldo(cS);
		this.setZonas(cZ);
	}

	private void setZonas(IControlZonas cZ) {
		this.controlZonas = cZ;

	}

	private IControlDeEstacionamiento getControlEstacionamiento() {
		return this.controlEstacionamiento;
	}

	private void setControlEstacionamiento(IControlDeEstacionamiento controlEstacionamiento) {
		this.controlEstacionamiento = controlEstacionamiento;
	}

	private IControlSaldo getControlSaldo() {
		return this.controlSaldo;
	}

	private void setControlSaldo(IControlSaldo controlSaldo) {
		this.controlSaldo = controlSaldo;
	}

	private IControlZonas getControlZonas() {
		return this.controlZonas;
	}

	private LocalTime horaFinMaxima(String nroCelular, LocalTime horaInicio) {
		LocalTime horaMaxima = this.getControlEstacionamiento().getHoraFin();
		Double precioPorSegundo = this.getControlEstacionamiento().getPrecioPorHora() / 3600;
		Integer segundosDebitables =
			Math.min(
				LocalTime.of(23, 59, 59).toSecondOfDay() - horaInicio.toSecondOfDay(),
				(int) (this.getControlSaldo().saldo(nroCelular) / precioPorSegundo));
		LocalTime horaDebitable = horaInicio.plusSeconds(segundosDebitables);
		LocalTime horaFin = horaDebitable.isBefore(horaMaxima)
				? horaDebitable
				: horaMaxima;
		return horaFin;
	}

	@Override
	public Respuesta iniciarEstacionamiento(String nroCelular, String patente) {
		Respuesta res;
		if (this.noContieneSaldoSuficiente(nroCelular)) {
			res = new RespuestaSinSaldo();
		} else {
			LocalTime horaInicio = LocalTime.now();
			LocalTime horaFin = horaFinMaxima(nroCelular, horaInicio);
			this.getControlEstacionamiento().registrarEstacionamiento(
					new EstacionamientoApp(patente, horaInicio, horaFin, nroCelular)
			);
			res = new RespuestaInicioEstacionamiento(horaInicio, horaFin);
		}
		return res;
	}

	private boolean noContieneSaldoSuficiente(String nroCelular) {
		return this.getControlSaldo().saldo(nroCelular) <= 0;
	}

	@Override
	public Respuesta finalizarEstacionamiento(String nroCelular) {

		EstacionamientoApp estacionamiento = (EstacionamientoApp) this.getControlEstacionamiento().estacionamientoVigente(nroCelular);
		estacionamiento.finalizar(this.getControlSaldo(), this.getControlEstacionamiento().getPrecioPorHora());

		return new RespuestaFinEstacionamiento(
				estacionamiento.getHoraInicio(),
				estacionamiento.getHoraFin(),
				estacionamiento.cantidadDeHoras(),
				estacionamiento.costo(this.getControlEstacionamiento().getPrecioPorHora()));
	}

	@Override
	public Boolean estaEnZonaDeEstacionamiento(Ubicacion ubicacion) {
		return this.getControlZonas().perteneceAUnaZonaDeEstacionamiento(ubicacion);
	}

	@Override
	public Boolean tieneEstacionamientoVigente(String patente) {
		return this.getControlEstacionamiento().tieneEstacionamientoVigenteConPatente(patente);
	}

	@Override
	public LocalTime horaFin() {
		return this.getControlEstacionamiento().getHoraFin();
	}

	@Override
	public LocalTime horaInicio() {
		return this.getControlEstacionamiento().getHoraInicio();
	}

	@Override 
	public Boolean estaEnHorario() {
		return LocalTime.now().isBefore(this.horaFin()) && 
			   LocalTime.now().isAfter(this.horaInicio());
	}
}

