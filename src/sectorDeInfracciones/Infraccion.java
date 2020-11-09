package sectorDeInfracciones;

import java.time.LocalDate;
import java.time.LocalTime;

import sectorDeZonas.ZonaDeEstacionamiento;

public class Infraccion {

	//atributos
	private String inspector;
	private String patente;
	private ZonaDeEstacionamiento zona;
	private LocalDate fecha;
	private LocalTime hora;

	public Infraccion(
			String patente,
			ZonaDeEstacionamiento zona,
			String inspector,
			LocalDate fecha,
			LocalTime hora) {

		this.setInspector(inspector);
		this.setPatente(patente);
		this.setZona(zona);
		this.setFecha(fecha);
		this.setHora(hora);
	}

	public String getInspector() {
		return inspector;
	}

	private void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public String getPatente() {
		return patente;
	}

	private void setPatente(String patente) {
		this.patente = patente;
	}

	public ZonaDeEstacionamiento getZona() {
		return zona;
	}

	private void setZona(ZonaDeEstacionamiento zona) {
		this.zona = zona;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	private void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	private void setHora(LocalTime hora) {
		this.hora = hora;
	}
}
