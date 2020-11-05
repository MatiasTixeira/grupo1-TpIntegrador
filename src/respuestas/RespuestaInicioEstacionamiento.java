package respuestas;

import java.time.LocalTime;

public class RespuestaInicioEstacionamiento implements Respuesta {
	private LocalTime horaInicio;
	private LocalTime horaFin;

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}

	public RespuestaInicioEstacionamiento(LocalTime horaInicio, LocalTime horaFin) {
		this.setHoraInicio(horaInicio);
		this.setHoraFin(horaFin);
	}

	@Override
	public String respuestaComoString() {
		return
				"El estacionamiento comenzó a las " +
				this.getHoraInicio() +
				" y su duración máxima es hasta las " +
				this.getHoraFin() +
				".";
	}
}
