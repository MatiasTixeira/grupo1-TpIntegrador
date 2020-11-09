package appInspector;

import java.time.LocalDate;
import java.time.LocalTime;

import sectorDeEstacionamiento.IControlDeEstacionamiento;
import sectorDeInfracciones.IRegistroDeInfraccion;
import sectorDeInfracciones.Infraccion;
import sectorDeZonas.ZonaDeEstacionamiento;

public class AppInspector {

	//Atributos
	private String inspector;
	private IControlDeEstacionamiento controlE;
	private IRegistroDeInfraccion controlI;

	//Constructor
	public AppInspector(
			IControlDeEstacionamiento controlInfraccion,
			String nombre,
			IRegistroDeInfraccion controlEstacionamiento){
		this.setControlE(controlInfraccion);
		this.setControlI(controlEstacionamiento);
		this.setInspector(nombre);
	}

	//Metodos
	private void setInspector(String inspector) {
		this.inspector = inspector;
	}

	private void setControlE(IControlDeEstacionamiento controlE) {
		this.controlE = controlE;
	}

	private void setControlI(IRegistroDeInfraccion controlI) {
		this.controlI = controlI;
	}

	public Boolean tieneEstacionamientoVigente(String patente) {
		return this.getControlE().tieneEstacionamientoVigenteConPatente(patente);
	}

	public void registrarInfraccion(String patente, ZonaDeEstacionamiento zona) {
		Infraccion infraccion = new Infraccion(patente, zona, this.getInspector(), LocalDate.now(), LocalTime.now());

		this.getControlI().registrar(infraccion);
	}

	private String getInspector() {
		return inspector;
	}

	private IControlDeEstacionamiento getControlE() {
		return controlE;
	}

	private IRegistroDeInfraccion getControlI() {
		return controlI;
	}

}

