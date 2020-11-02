package appInspector;

import java.time.LocalDate;
import java.time.LocalTime;

public class Infraccion {

	//atributos 
	private String inspector;
	private String patente;
	private String zona;
	private LocalDate fecha;
	private LocalTime hora;
		
	public Infraccion(String patente, String zona, String inspector) {
		this.setInspector(inspector);
		this.setPatente(patente);
		this.setZona(zona);
		this.setFecha(LocalDate.now());
		this.setHora(LocalTime.now());
		
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}
}
