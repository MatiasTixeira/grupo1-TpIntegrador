package respuestas;

public class RespuestaNoTieneEstacionamientoVigente implements Respuesta {

	@Override
	public String respuestaComoString() {
		return "El número indicado no tiene ningun estacionamiento vigente. Estacionamiento no permitido.";
	}

	@Override
	public Boolean operacionExitosa() {
		return false;
	}
}
