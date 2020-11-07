package sectorDeInfracciones;

import java.util.ArrayList;

import appInspector.Infraccion;

public class SectorDeInfracciones {
	
	//atributos 
	private ArrayList<Infraccion> listaInfracciones;	
	
	//Constructor 
	public SectorDeInfracciones() {
		this.setListaInfracciones(new ArrayList<Infraccion>());
	}
	
	//Metodos
	public ArrayList<Infraccion> getInfracciones(){
		return this.listaInfracciones;
	}

	public void registrar(Infraccion infraccion) {
		this.getInfracciones().add(infraccion);
	}
	
	private void setListaInfracciones(ArrayList<Infraccion> listaInfracciones) {
		this.listaInfracciones = listaInfracciones;
	}
}
