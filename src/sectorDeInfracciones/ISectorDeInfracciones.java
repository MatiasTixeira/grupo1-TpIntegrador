package sectorDeInfracciones;

import java.util.ArrayList;

import appInspector.IRegistroDeInfraccion;
import appInspector.Infraccion;

public interface ISectorDeInfracciones extends IRegistroDeInfraccion{
	public ArrayList<Infraccion> getInfracciones();
}
