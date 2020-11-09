package respuestas;

public class RespuestaOperacionFallida implements Respuesta {

	@Override
	public String respuestaComoString() {
		return "No se puede realizar la operación.";
	}

	@Override
	public boolean operacionExitosa() {
		return false;
	}

}
