package compras;

import puntosDeVenta.PuntoDeVenta;

public class CompraPuntual extends Compra {
	private Integer cantidadDeHoras;

	public CompraPuntual(PuntoDeVenta puntoDeVenta, Integer cantidadDeHoras) {
		super(puntoDeVenta);
		this.setCantidadDeHoras(cantidadDeHoras);
	}

	private void setCantidadDeHoras(Integer cantidadDeHoras) {
		this.cantidadDeHoras = cantidadDeHoras;
	}
}