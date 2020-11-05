package estacionamiento;

import java.time.LocalTime;

public abstract class Estacionamiento {
	protected String patente;
	protected LocalTime horaInicio;
	protected LocalTime horaFin;

	public String getPatente() {
		return patente;
	}

	protected void setPatente(String patente) {
		this.patente = patente;
	}

	protected LocalTime getHoraInicio() {
		return horaInicio;
	}

	protected void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	protected LocalTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}

	public Estacionamiento(
			String patente,
			LocalTime horaInicio,
			LocalTime horaFin) {

		this.setHoraFin(horaFin);
		this.setHoraInicio(horaInicio);
		this.setPatente(patente);
	}

	public Boolean estaVigente() {
		return LocalTime.now().isBefore(this.getHoraFin());
	}

	public abstract String getNumeroCelular();
}
