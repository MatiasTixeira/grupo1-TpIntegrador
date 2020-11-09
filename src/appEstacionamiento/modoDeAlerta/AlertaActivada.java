package appEstacionamiento.modoDeAlerta;

import appEstacionamiento.AppEstacionamiento;
import appEstacionamiento.GUI;

public class AlertaActivada implements ModoDeAlerta {

	@Override
	public void comenzoAManejar(AppEstacionamiento app, GUI gui) {

		if (app.puedeFinalizarEstacionamiento())
			gui.alert("Deberías comenzar un estacionamiento.");
	}

	@Override
	public void comenzoACaminar(AppEstacionamiento app, GUI gui) {

		if (app.puedeIniciarEstacionamiento())
			gui.alert("Deberías comenzar un estacionamiento.");
	}
}
