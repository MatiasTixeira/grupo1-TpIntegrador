package compras;

import puntosDeVenta.PuntoDeVenta;

public class CompraSaldo extends Compra {
	private Double monto;
	private String numeroDeCelular;

	public CompraSaldo(PuntoDeVenta puntoDeVenta, Double monto, String numeroDeCelular) {
		super(puntoDeVenta);
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
}
