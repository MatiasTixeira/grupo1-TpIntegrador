package respuestas;

public class RespuestaNoTieneEstacionamientoVigente implements Respuesta {

	@Override
	public String respuestaComoString() {
		return "El número indicado no tiene ningún estacionamiento vigente. Estacionamiento no permitido.";
	}

	@Override
	public Boolean operacionExitosa() {
		return false;
	}
}
