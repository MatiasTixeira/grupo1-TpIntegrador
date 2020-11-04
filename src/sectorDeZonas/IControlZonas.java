package sectorDeZonas;

import espacioGeografico.Ubicacion;

public interface IControlZonas {
	public Boolean perteneceAUnaZonaDeEstacionamiento(Ubicacion u);
	public void registrar(ZonaDeEstacionamiento zona);
}
