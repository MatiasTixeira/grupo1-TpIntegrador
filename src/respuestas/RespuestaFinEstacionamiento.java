package respuestas;

import java.time.LocalTime;

public class RespuestaFinEstacionamiento implements Respuesta {
	private LocalTime horaInicio;
	private LocalTime horaFin;
	private Integer cantHoras;
	private Double costo;

	public RespuestaFinEstacionamiento(
			LocalTime horaInicio,
			LocalTime horaFin,
			Integer cantHoras,
			Double costo) {
		this.setHoraInicio(horaInicio);
		this.setHoraFin(horaFin);
		this.setCantHoras(cantHoras);
		this.setCosto(costo);
	}

	private LocalTime getHoraInicio() {
		return horaInicio;
	}

	private void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	private LocalTime getHoraFin() {
		return horaFin;
	}

	private void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}

	private Integer getCantHoras() {
		return cantHoras;
	}

	private void setCantHoras(Integer cantHoras) {
		this.cantHoras = cantHoras;
	}

	private Double getCosto() {
		return costo;
	}

	private void setCosto(Double costo) {
		this.costo = costo;
	}

	@Override
	public String respuestaComoString() {
		return  "El estacionamiento comenzó a las " +
			this.getHoraInicio() +
			" y terminó a las " +
			this.getHoraFin() +
			". Duró " +
			this.getCantHoras() +
			" horas, y costó $" +
			this.getCosto();
	}

	@Override
	public Boolean operacionExitosa() {
		return true;
	}


}
