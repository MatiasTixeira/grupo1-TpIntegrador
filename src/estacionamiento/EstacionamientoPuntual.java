package estacionamiento;

import java.time.LocalTime;

import compras.CompraPuntual;

public class EstacionamientoPuntual extends Estacionamiento {

	private CompraPuntual compra;

	public EstacionamientoPuntual(
			String patente,
			LocalTime horaInicio,
			LocalTime horaFin,
			CompraPuntual compra) {

		super(patente, horaInicio, horaFin);
		this.setCompra(compra);
	}

	public CompraPuntual getCompra() {
		return compra;
	}

	public void setCompra(CompraPuntual compra) {
		this.compra = compra;
	}

	@Override
	public String getNumeroCelular() {
		return "";
	}

}
