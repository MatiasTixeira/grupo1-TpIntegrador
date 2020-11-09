package appEstacionamiento.modoDeAlerta;

import appEstacionamiento.AppEstacionamiento;
import appEstacionamiento.GUI;

public class AlertaActivada implements ModoDeAlerta {

	@Override
	public void comenzoAManejar(AppEstacionamiento app, GUI gui) {

		if (app.puedeFinalizarEstacionamiento())
			gui.alert("Deberias finalizar tu estacionamiento.");
	}

	@Override
	public void comenzoACaminar(AppEstacionamiento app, GUI gui) {

		if (app.puedeIniciarEstacionamiento())
			gui.alert("Deberias comenzar un estacionamiento.");
	}
}
