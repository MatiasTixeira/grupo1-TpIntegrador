package sectorDeInfracciones;

import java.util.ArrayList;

public class SectorDeInfracciones implements ISectorDeInfracciones {

	//atributos
	private ArrayList<Infraccion> listaInfracciones;

	//Constructor
	public SectorDeInfracciones() {
		this.setListaInfracciones(new ArrayList<Infraccion>());
	}

	//Metodos
	@Override
	public ArrayList<Infraccion> getInfracciones(){
		return this.listaInfracciones;
	}

	@Override
	public void registrar(Infraccion infraccion) {
		this.getInfracciones().add(infraccion);
	}

	private void setListaInfracciones(ArrayList<Infraccion> listaInfracciones) {
		this.listaInfracciones = listaInfracciones;
	}
}
