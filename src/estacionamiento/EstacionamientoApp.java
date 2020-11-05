package estacionamiento;

import java.time.LocalTime;

public class EstacionamientoApp extends Estacionamiento {
	private String patente;
	private String numeroCelular;

	public EstacionamientoApp (
			String patente,
			LocalTime horaInicio,
			LocalTime horaFin,
			String nroCelular) {

		super(patente, horaInicio, horaFin);
		this.setNumeroCelular(nroCelular);
	}

	private void setNumeroCelular(String nroCelular) {
		this.numeroCelular = nroCelular;
	}
	@Override 
	public void finalizar() {
		this.setEstaActivo(false);
		
	}
	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	@Override
	public String getNumeroCelular() {
		
		return numeroCelular;
	}

}
