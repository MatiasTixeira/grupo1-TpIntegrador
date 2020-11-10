package compras;

import java.time.LocalDate;
import java.time.LocalTime;

import puntoDeVenta.PuntoDeVenta;

public class CompraPuntual extends Compra {
	private Integer cantidadDeHoras;

	public CompraPuntual(PuntoDeVenta puntoDeVenta, Integer cantidadDeHoras, LocalDate fecha, LocalTime hora) {
		super(puntoDeVenta, fecha, hora);
		this.setCantidadDeHoras(cantidadDeHoras);
	}

	public Integer getCantidadDeHoras() {
		return this.cantidadDeHoras;
	}

	private void setCantidadDeHoras(Integer cantidadDeHoras) {
		this.cantidadDeHoras = cantidadDeHoras;
	}
}
