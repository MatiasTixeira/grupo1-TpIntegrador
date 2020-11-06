package appEstacionamiento.modoDeActivacion;

import appEstacionamiento.GUI;
import respuestas.Respuesta;
import serverEstacionamiento.IServerEstacionamientoApp;

public class ModoAutomatico implements ModoDeActivacion {

	@Override
	public void comenzoAManejar(
			IServerEstacionamientoApp server,
			String nroCelular,
			GUI gui) {

		Respuesta res = server.finalizarEstacionamiento(nroCelular);
		gui.print(res.respuestaComoString());
		gui.print("Esta operación fue realizada de manera automática");
	}

	@Override
	public void comenzoACaminar(
			IServerEstacionamientoApp server,
			String patente,
			String nroCelular,
			GUI gui) {

		Respuesta res = server.iniciarEstacionamiento(patente, nroCelular);
		gui.print(res.respuestaComoString());
		gui.print("Esta operación fue realizada de manera automática");
	}
}
