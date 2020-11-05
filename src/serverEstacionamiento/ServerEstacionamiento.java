package serverEstacionamiento;

import java.time.LocalTime;

import estacionamiento.EstacionamientoApp;
import respuestas.Respuesta;
import respuestas.RespuestaInicioEstacionamiento;
import respuestas.RespuestaSinSaldo;
import sectorDeEstacionamiento.IControlDeEstacionamiento;
import sectorDeSaldos.IControlSaldo;
 
public class ServerEstacionamiento implements IServerEstacionamientoApp {
	private IControlDeEstacionamiento controlEstacionamiento;
	private IControlSaldo controlSaldo;

	public IControlDeEstacionamiento getControlEstacionamiento() {
		return this.controlEstacionamiento;
	}

	public void setControlEstacionamiento(IControlDeEstacionamiento controlEstacionamiento) {
		this.controlEstacionamiento = controlEstacionamiento;
	}

	public IControlSaldo getControlSaldo() {
		return this.controlSaldo;
	}

	public void setControlSaldo(IControlSaldo controlSaldo) {
		this.controlSaldo = controlSaldo;
	}

	@Override
	public Respuesta iniciarEstacionamiento(String nroCelular, String patente) {
		if (this.getControlSaldo().saldo(nroCelular) <= 0) {
			return new RespuestaSinSaldo();
		} else {
			LocalTime horaInicio = LocalTime.now();
			LocalTime horaMaxima = this.getControlEstacionamiento().getHoraFin();
			Double precioPorHora = this.getControlEstacionamiento().getPrecioPorHora();
			Double precioPorSegundo = precioPorHora / 3600;
			Integer segundosDebitables =
					(int) (this.getControlSaldo().saldo(nroCelular) / precioPorSegundo);
			LocalTime horaDebitable = horaInicio.plusSeconds(segundosDebitables);
			LocalTime horaFin = horaDebitable.isBefore(horaMaxima)
					? horaDebitable
					: horaMaxima;

			this.getControlEstacionamiento().registrarEstacionamiento(
					new EstacionamientoApp(patente, horaInicio, horaFin, nroCelular)
			);

			return new RespuestaInicioEstacionamiento(horaInicio, horaFin);
		}
	}
}
