package appEstacionamiento.modoDeActivacion;

import appEstacionamiento.AppEstacionamiento;
import appEstacionamiento.GUI;
import respuestas.Respuesta;

public class ModoAutomatico implements ModoDeActivacion {

	@Override
	public void comenzoAManejar(
			AppEstacionamiento app,
			GUI gui) {

		Respuesta res = app.finEstacionamiento();

		if (res.operacionExitosa()) {
			gui.print(res.respuestaComoString());
			gui.print("Esta operación fue realizada de manera automática");
		}
	}

	@Override
	public void comenzoACaminar(
			AppEstacionamiento app,
			GUI gui) {

		Respuesta res = app.inicioEstacionamiento();

		if (res.operacionExitosa()) {
			gui.print(res.respuestaComoString());
			gui.print("Esta operación fue realizada de manera automática");
		}
	}
}
