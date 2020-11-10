package estacionamiento;

import java.time.LocalTime;

import sectorDeSaldos.IControlSaldo;

public abstract class Estacionamiento {
	private  String patente;
	private  LocalTime horaInicio;
	private  LocalTime horaFin;
	private Boolean estaActivo;
	
	
	public Estacionamiento(
			String patente,
			LocalTime horaInicio,
			LocalTime horaFin) {

		this.setHoraFin(horaFin);
		this.setHoraInicio(horaInicio);
		this.setPatente(patente);
		this.setEstaActivo(true);
	}
	
	public String getPatente() {
		return patente;
	}
	
	public boolean esSuPatente(String patente) {
		return this.getPatente().equals(patente);
	}
	
	public  abstract boolean esSuCelular(String celular);

	protected void setPatente(String patente) {
		this.patente = patente;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	protected void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return this.horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}

	public Boolean estaVigente() {
		Boolean estaVigente = 
				this.estaEnHorario()&& 
				this.getEstaActivo();
		return estaVigente ;
	}

	private boolean estaEnHorario() {
		return LocalTime.now().isBefore(this.getHoraFin());
	}
	
	public Boolean getEstaActivo() {
		return estaActivo;
	}

	public abstract void finalizar(IControlSaldo iControlSaldo,Double precioPorHora);
		
	public void setEstaActivo(Boolean estaActivo) {
		this.estaActivo = estaActivo;
	}
}
