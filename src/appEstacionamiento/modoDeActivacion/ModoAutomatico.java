package appEstacionamiento.modoDeActivacion;

import appEstacionamiento.AppEstacionamiento;
import appEstacionamiento.GUI;
import respuestas.Respuesta;

public class ModoAutomatico implements ModoDeActivacion {

	@Override
	public void comenzoAManejar(
			AppEstacionamiento app,
			GUI gui) {

		Respuesta res = app.respuestaFin();

		if (res.operacionExitosa()) {
			gui.print(res.respuestaComoString());
			gui.print("Esta operacion fue realizada de manera automatica");
		}
	}

	@Override
	public void comenzoACaminar(
			AppEstacionamiento app,
			GUI gui) {

		Respuesta res = app.respuestaInicio();

		if (res.operacionExitosa()) {
			gui.print(res.respuestaComoString());
			gui.print("Esta operacion fue realizada de manera automatica");
		}
	}
}
