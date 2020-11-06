package appEstacionamiento.modoDeActivacion;

import appEstacionamiento.GUI;
import serverEstacionamiento.IServerEstacionamientoApp;

public interface ModoDeActivacion {

	public void comenzoACaminar(
			IServerEstacionamientoApp server,
			String patente,
			String nroCelular,
			GUI gui);

	public void comenzoAManejar(
			IServerEstacionamientoApp server,
			String nroCelular,
			GUI gui);

}
