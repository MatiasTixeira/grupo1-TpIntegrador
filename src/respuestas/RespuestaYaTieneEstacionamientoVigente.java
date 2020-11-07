package respuestas;

public class RespuestaYaTieneEstacionamientoVigente implements Respuesta {

	@Override
	public String respuestaComoString() {
		return "El numero indicado ya tiene un estacionamiento vigente. Estacionamiento no permitido.";
	}

	@Override
	public Boolean operacionExitosa() {
		return false;
	}
}
