package estacionamiento;

import java.time.LocalTime;

import sectorDeSaldos.IControlSaldo;

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
	public void finalizar(IControlSaldo controlSaldo, Double precioPorHora) {
		if(this.getEstaActivo()) {
			this.setHoraFin(this.calcularHorarioMaximo());
			Double costo = this.costo(precioPorHora);
			controlSaldo.descontar(this.getNumeroCelular(), costo);
			this.setEstaActivo(false);
		}
		
	}

	private LocalTime calcularHorarioMaximo() {
		return LocalTime.now().isBefore(this.getHoraFin())
				? LocalTime.now()
				: this.getHoraFin();
	}
	public Double costo(Double precioPorHora) {
		LocalTime horaInicio = this.getHoraInicio();
		LocalTime horaFin = this.getHoraFin();

		Integer segundosCobrados = horaFin.toSecondOfDay() - horaInicio.toSecondOfDay();
		Double precioPorSegundo = precioPorHora / 3600;
		Double costo = segundosCobrados * precioPorSegundo;
		return costo;
	}
	public Integer cantidadDeHoras() {
		return Math.min(1, (this.getHoraFin().getHour() - this.getHoraInicio().getHour()));
	}
	
}
