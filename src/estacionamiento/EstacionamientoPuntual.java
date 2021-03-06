package estacionamiento;

import java.time.LocalTime;

import compras.CompraPuntual;
import sectorDeSaldos.IControlSaldo;

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
		return this.compra;
	}

	public void setCompra(CompraPuntual compra) {
		this.compra = compra; 
	}
	public boolean esSuCelular(String celular) {
		return false;
	}
	@Override
	public void finalizar(IControlSaldo controlSaldo, Double precioPorHora) {
		this.setEstaActivo(false);
		
	}


}
