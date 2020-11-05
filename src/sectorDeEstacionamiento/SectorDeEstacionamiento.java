package sectorDeEstacionamiento;

import java.time.LocalTime;
import java.util.ArrayList;

import estacionamiento.Estacionamiento;

public class SectorDeEstacionamiento implements ISectorDeEstacionamiento {

	private ArrayList<Estacionamiento> estacionamientos;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	private Double precioPorHora;

	public SectorDeEstacionamiento(LocalTime horaInicio, LocalTime horaFin, Double precioPorHora) {
		this.setHoraFin(horaFin);
		this.setHoraInicio(horaInicio);
		this.setPrecioPorHora(precioPorHora);
		this.setEstacionamientos(new ArrayList<Estacionamiento>());
	}

	private void setEstacionamientos(ArrayList<Estacionamiento> estacionamientos) {
		this.estacionamientos = estacionamientos;

	}

	@Override
	public void registrarEstacionamiento(Estacionamiento estacionamiento) {
		this.getEstacionamientos().add(estacionamiento);
	}

	@Override
	public Boolean tieneEstacionamientoVigente(String patente) {
		Boolean estaVigente = this.getEstacionamientos()
				.stream()
				.anyMatch(estacionamiento ->
						estacionamiento.getPatente().equals(patente) &&
						estacionamiento.estaVigente());
		return estaVigente;
	}

	@Override
	public Estacionamiento estacionamientoVigente(String numCelular) {
		//Precondicion: Debe de haber un estacionamiento con ese numero celular y el mismo
		//debe de estar vigente.
		Estacionamiento estacionamiento = this.getEstacionamientos()
				.stream()
				.filter(e -> e.getNumeroCelular().equals(numCelular) &&
							 e.estaVigente())
				.findAny()
				.get();
		return estacionamiento;
	}

	@Override
	public void finalizarTodosLosEstacionamientos() {
		for (Estacionamiento estacionamiento : this.getEstacionamientos()) {
			estacionamiento.finalizar();
		}

	}

	@Override
	public Boolean esHorarioDeEstacionamiento() {

		Boolean esHorarioDeEstacionamiento =
				horaActual().isAfter(this.getHoraInicio()) &&
				horaActual().isBefore(this.getHoraFin());

		return esHorarioDeEstacionamiento;
	}
	public LocalTime horaActual() {
		return LocalTime.now();
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	@Override
	public ArrayList<Estacionamiento> getEstacionamientos() {

		return this.estacionamientos;
	}

	@Override
	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	@Override
	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;

	}

	@Override
	public void setPrecioPorHora(Double precioPorHora) {
		this.precioPorHora = precioPorHora;

	}

	@Override
	public LocalTime getHorarioDeFinalizacion() {
		// TODO Auto-generated method stub
		return this.horaFin;
	}

	@Override
	public Double getPrecioPorHora() {
		
		return this.precioPorHora;
	}

}
