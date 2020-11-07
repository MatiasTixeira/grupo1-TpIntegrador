package sectorDeZonas;

import java.util.ArrayList;
import espacioGeografico.Ubicacion;

public class SectorDeZonas implements ISectorZonas{

	//atributos 
	private ArrayList<ZonaDeEstacionamiento> zonas;
	
	public SectorDeZonas() {
		this.setZonas(new ArrayList<ZonaDeEstacionamiento>());
	}
	
	//testeado
	@Override
	public ArrayList<ZonaDeEstacionamiento> getZonas() {
		return zonas;
	}

	@Override
	public Boolean perteneceAUnaZonaDeEstacionamiento(Ubicacion u) {
		return this.getZonas()
			.stream()
			.anyMatch(zona -> zona.contiene(u));
	}

	@Override
	public void registrar(ZonaDeEstacionamiento zona) {
		this.getZonas().add(zona);
	}

	private void setZonas(ArrayList<ZonaDeEstacionamiento> zonas) {
		this.zonas = zonas;
	}

}

