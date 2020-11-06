package appEstacionamiento.modoDeActivacion;

import appEstacionamiento.GUI;
import serverEstacionamiento.IServerEstacionamientoApp;

public class ModoManual implements ModoDeActivacion {

	@Override
	public void comenzoACaminar(
			IServerEstacionamientoApp server,
			String patente,
			String nroCelular,
			GUI gui) {}

	@Override
	public void comenzoAManejar(
			IServerEstacionamientoApp server,
			String nroCelular,
			GUI gui) {}

}
