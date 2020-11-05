package compras;

import puntoDeVenta.PuntoDeVenta;

public class CompraPuntual extends Compra {
	private Double cantidadDeHoras;

	public CompraPuntual(PuntoDeVenta puntoDeVenta, Double cantidadDeHoras) {
		super(puntoDeVenta);
		this.setCantidadDeHoras(cantidadDeHoras);
	}

	public Double getCantidadDeHoras() {
		return cantidadDeHoras;
	}

	private void setCantidadDeHoras(Double cantidadDeHoras) {
		this.cantidadDeHoras = cantidadDeHoras;
	}
}
