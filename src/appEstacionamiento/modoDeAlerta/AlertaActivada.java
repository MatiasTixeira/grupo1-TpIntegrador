package appEstacionamiento.modoDeAlerta;

import appEstacionamiento.GUI;

public class AlertaActivada implements ModoDeAlerta {

	@Override
	public void comenzoAManejar(GUI gui) {

		gui.alert("Deberías finalizar tu estacionamiento.");
	}

	@Override
	public void comenzoACaminar(GUI gui) {

		gui.alert("Deberías comenzar un estacionamiento.");
	}
}
