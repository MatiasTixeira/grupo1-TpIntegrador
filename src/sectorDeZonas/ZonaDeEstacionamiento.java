package sectorDeZonas;

import java.util.ArrayList;

import espacioGeografico.Ubicacion;
import espacioGeografico.UbicacionGeografica;
import puntoDeVenta.PuntoDeVenta;

public class ZonaDeEstacionamiento{
	
	//atributos 
	private String inspector;
	private UbicacionGeografica ubicacion;
	private ArrayList<PuntoDeVenta> puntosDeVenta;
	
	
	public ZonaDeEstacionamiento(String i,UbicacionGeografica u, ArrayList<PuntoDeVenta> puntosDeVenta) {
		this.setInspector(i);
		this.setUbicacion(u);
		this.setPuntosDeVenta(puntosDeVenta);
	}

	public void setPuntosDeVenta(ArrayList<PuntoDeVenta> puntosDeVenta) {
		this.puntosDeVenta = puntosDeVenta;
	}

	private void setInspector(String inspector) {
		this.inspector = inspector;
	}

	private void setUbicacion(UbicacionGeografica ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	public Boolean contiene(Ubicacion u) {
		return this.getUbicacion().estaDentroDeLaZona(u);
	}

	public UbicacionGeografica getUbicacion() {
		return ubicacion;
	}

}
