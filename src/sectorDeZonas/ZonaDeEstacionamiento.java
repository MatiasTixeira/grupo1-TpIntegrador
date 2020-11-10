package sectorDeZonas;

import java.util.ArrayList;

import espacioGeografico.Ubicacion;
import espacioGeografico.UbicacionGeografica;
import puntoDeVenta.PuntoDeVenta;

public class ZonaDeEstacionamiento{
	
	private UbicacionGeografica ubicacion;
	
	public ZonaDeEstacionamiento(String i,UbicacionGeografica u, ArrayList<PuntoDeVenta> puntosDeVenta) {
		this.setInspector(i);
		this.setUbicacion(u);
		this.setPuntosDeVenta(puntosDeVenta);
	}

	public void setPuntosDeVenta(ArrayList<PuntoDeVenta> puntosDeVenta) {
	}

	private void setInspector(String inspector) {
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
