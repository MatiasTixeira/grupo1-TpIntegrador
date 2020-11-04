package sectorDeZonas;

import java.util.ArrayList;

import espacioGeografico.Ubicacion;

public interface ISectorZonas extends IControlZonas {
	public ArrayList<ZonaDeEstacionamiento>getZonas();
	public Boolean perteneceAUnaZonaDeEstacionamiento(Ubicacion u);
	public void registrar(ZonaDeEstacionamiento zona);
}
