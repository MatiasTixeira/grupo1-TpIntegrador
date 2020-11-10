package compras;

import java.time.LocalDate;
import java.time.LocalTime;

import puntoDeVenta.PuntoDeVenta;

public abstract class Compra {
	private LocalDate fecha;
	private LocalTime hora;
	private PuntoDeVenta puntoDeVenta;
	private Integer numeroDeControl;

	public Compra(PuntoDeVenta puntoDeVenta, LocalDate fecha, LocalTime hora) {
		this.setFecha(fecha);
		this.setHora(hora);
		this.setPuntoDeVenta(puntoDeVenta);
	}

	private void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public PuntoDeVenta getPuntoDeVenta() {
		return puntoDeVenta;
	}

	private void setHora(LocalTime hora) {
		this.hora = hora;
	}

	private void setPuntoDeVenta(PuntoDeVenta puntoDeVenta) {
		this.puntoDeVenta = puntoDeVenta;
	}

	public Integer getNumeroDeControl() {
		return this.numeroDeControl;
	}

	public void setNumeroDeControl(Integer numeroDeControl) {
		this.numeroDeControl = numeroDeControl;
	}
}
