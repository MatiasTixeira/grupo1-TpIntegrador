package sectorDeEstacionamiento;

import java.time.LocalTime;
import java.util.ArrayList;

import estacionamiento.Estacionamiento;
import sectorDeSaldos.IControlSaldo;

public class SectorDeEstacionamiento implements ISectorDeEstacionamiento {

	private ArrayList<Estacionamiento> estacionamientos;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	private Double precioPorHora;
	private IControlSaldo controlSaldo;

	public SectorDeEstacionamiento(LocalTime horaInicio, LocalTime horaFin, Double precioPorHora,IControlSaldo controlSaldo) {
		this.setHoraFin(horaFin);
		this.setHoraInicio(horaInicio);
		this.setPrecioPorHora(precioPorHora);
		this.setEstacionamientos(new ArrayList<Estacionamiento>());
		this.setControlSaldo(controlSaldo);
	}

	private void setEstacionamientos(ArrayList<Estacionamiento> estacionamientos) {
		this.estacionamientos = estacionamientos;

	}

	@Override
	public void registrarEstacionamiento(Estacionamiento estacionamiento) {
		this.getEstacionamientos().add(estacionamiento);
	}

	@Override
	public Boolean tieneEstacionamientoVigenteConPatente(String patente) {
		Boolean estaVigente = this.getEstacionamientos()
				.stream()
				.anyMatch(estacionamiento ->
						estacionamiento.esSuPatente(patente) &&
						estacionamiento.estaVigente());
		return estaVigente;
	}

	@Override
	public Estacionamiento estacionamientoVigente(String numCelular) {
		//Precondicion: Debe de haber un estacionamiento con ese numero celular y el mismo
		//debe de estar vigente.
		Estacionamiento estacionamiento = this.getEstacionamientos()
				.stream()
				.filter(e -> e.esSuCelular(numCelular) &&
							 e.estaVigente())
				.findAny()
				.get();
		return estacionamiento;
	}

	@Override
	public void finalizarTodosLosEstacionamientos() {
		for (Estacionamiento estacionamiento : this.getEstacionamientos()) {
			estacionamiento.finalizar(this.getControlSaldo(), this.getPrecioPorHora());
		}

	}  

	public IControlSaldo getControlSaldo() {
		return controlSaldo;
	}

	public void setControlSaldo(IControlSaldo controlSaldo) {
		this.controlSaldo = controlSaldo;
	}

	@Override
	public Boolean esHorarioDeEstacionamiento() {

		Boolean esHorarioDeEstacionamiento =
				LocalTime.now().isAfter(this.getHoraInicio()) &&
				LocalTime.now().isBefore(this.getHoraFin());

		return esHorarioDeEstacionamiento;
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
	public Double getPrecioPorHora() {
		
		return this.precioPorHora;
	}

	@Override
	public Boolean tieneEstacionamientoVigenteConCelular(String celular) {
		Boolean estaVigente = this.getEstacionamientos()
				.stream()
				.anyMatch(estacionamiento ->
						estacionamiento.esSuCelular(celular) &&
						estacionamiento.estaVigente());
		return estaVigente;
	}

}
