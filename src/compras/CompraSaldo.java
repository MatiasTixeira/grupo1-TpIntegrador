package compras;

import java.time.LocalDate;
import java.time.LocalTime;

import puntoDeVenta.PuntoDeVenta;

public class CompraSaldo extends Compra {
	private Double monto;
	private String numeroDeCelular;

	public CompraSaldo(
			PuntoDeVenta puntoDeVenta,
			Double monto,
			String numeroDeCelular,
			LocalDate fecha,
			LocalTime hora) {

		super(puntoDeVenta, fecha, hora);
		this.setMonto(monto);
		this.setNumeroDeCelular(numeroDeCelular);
	}

	private void setMonto(Double monto) {
		this.monto = monto;
	}

	private void setNumeroDeCelular(String numeroDeCelular) {
		this.numeroDeCelular = numeroDeCelular;
	}

	public String getNumeroDeCelular() {
		return this.numeroDeCelular;
	}

	public Double getMonto() {
		return this.monto;
	}
}
