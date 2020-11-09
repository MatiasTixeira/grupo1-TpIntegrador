package appEstacionamiento.modoDeActivacion;

import appEstacionamiento.AppEstacionamiento;
import appEstacionamiento.GUI;

public interface ModoDeActivacion {

	public void comenzoACaminar(
			AppEstacionamiento app,
			GUI gui);

	public void comenzoAManejar(
			AppEstacionamiento app,
			GUI gui);

}
