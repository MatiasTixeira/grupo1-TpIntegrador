package appEstacionamiento.modoDeAlerta;

import appEstacionamiento.AppEstacionamiento;
import appEstacionamiento.GUI;

public class AlertaDesactivada implements ModoDeAlerta {

	@Override
	public void comenzoACaminar(AppEstacionamiento app, GUI gui) {}

	@Override
	public void comenzoAManejar(AppEstacionamiento app, GUI gui) {}

}
