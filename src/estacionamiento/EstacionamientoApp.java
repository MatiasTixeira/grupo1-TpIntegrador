package estacionamiento;

import java.time.LocalTime;

public class EstacionamientoApp extends Estacionamiento {
	private String numeroCelular;
	
	public EstacionamientoApp (
			String patente,
			LocalTime horaInicio,
			LocalTime horaFin,
			String nroCelular) {

		super(patente, horaInicio, horaFin);
		this.setNumeroCelular(nroCelular);
	}
	
	public boolean esSuCelular(String celular) {
		return this.getNumeroCelular().equals(celular);
	}
	
	public void setNumeroCelular(String nroCelular) {
		this.numeroCelular = nroCelular;
	}
	
	public String getNumeroCelular() {
		return this.numeroCelular;
	}
	
	@Override 
	public void finalizar() {
		this.setEstaActivo(false);
		
	}
	
}
