package respuestas;

public class RespuestaSinSaldo implements Respuesta {

	@Override
	public String respuestaComoString() {
		return "Saldo insuficiente. Estacionamiento no permitido.";
	}

	@Override
	public Boolean operacionExitosa() {
		return false;
	}
}
