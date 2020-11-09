package appEstacionamiento.modoDeAlerta;

import appEstacionamiento.AppEstacionamiento;
import appEstacionamiento.GUI;

public interface ModoDeAlerta {

	public void comenzoACaminar(AppEstacionamiento app, GUI gui);

	public void comenzoAManejar(AppEstacionamiento app, GUI gui);
}
