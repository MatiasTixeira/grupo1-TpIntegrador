package appInspector;

import sectorDeEstacionamiento.IControlDeEstacionamiento;

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
	
	public void registrarInfraccion(String patente, String zona) {
		Infraccion infraccion = new Infraccion(patente, zona, this.getInspector());
		this.getControlI().registrar(infraccion);
	}

	public String getInspector() {
		return inspector;
	}

	public IControlDeEstacionamiento getControlE() {
		return controlE;
	}

	public IRegistroDeInfraccion getControlI() {
		return controlI;
	}
	
}

