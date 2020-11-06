package puntoDeVenta;

import java.time.LocalTime;

import compras.CompraPuntual;
import compras.CompraSaldo;
import estacionamiento.EstacionamientoPuntual;
import sectorDeCompras.IRegistroCompras;
import sectorDeEstacionamiento.IControlDeEstacionamiento;
import sectorDeSaldos.IControlSaldo;

public class PuntoDeVenta {

	//atributos
	IControlSaldo controlSal;
	IControlDeEstacionamiento controlEst;
	IRegistroCompras controlCom;

	//constructor
	public PuntoDeVenta(
			IControlSaldo controlSal,
			IControlDeEstacionamiento controlEst,
			IRegistroCompras controlCom) {
		this.setControlCom(controlCom);
		this.setControlEst(controlEst);
		this.setControlSal(controlSal);
	}

	//metodos
	public void comprarCredito(String num, Double monto) {
		CompraSaldo compra = new CompraSaldo(this,monto,num);
		this.getControlCom().registrar(compra);
		this.getControlSal().cargarSaldo(num, monto);
	}

	public Integer comprarEstacionamiento(String patente, Integer cantHoras) {

		LocalTime horaActual = LocalTime.now();
		LocalTime horaMaxima = this.getControlEst().getHoraFin();
		Integer horasCompradas = Math.min(cantHoras, horaMaxima.getHour() - horaActual.getHour());
		LocalTime horaDebitable = horaActual.plusHours(horasCompradas);
		LocalTime horaFin = horaDebitable.isBefore(horaMaxima)
				? horaDebitable
				: horaMaxima;

		CompraPuntual compra = new CompraPuntual(this,horasCompradas);
		this.getControlCom().registrar(compra);

		EstacionamientoPuntual est = new EstacionamientoPuntual(
				patente, horaActual, horaFin, compra);
		this.getControlEst().registrarEstacionamiento(est);
		return horasCompradas;
	}

	public IControlSaldo getControlSal() {
		return controlSal;
	}

	public void setControlSal(IControlSaldo controlSal) {
		this.controlSal = controlSal;
	}

	public IControlDeEstacionamiento getControlEst() {
		return controlEst;
	}

	public void setControlEst(IControlDeEstacionamiento controlEst) {
		this.controlEst = controlEst;
	}

	public IRegistroCompras getControlCom() {
		return controlCom;
	}

	public void setControlCom(IRegistroCompras controlCom) {
		this.controlCom = controlCom;
	}
}
