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
	public PuntoDeVenta(IControlSaldo controlSal,IControlDeEstacionamiento controlEst,IRegistroCompras controlCom) {
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

	public void comprarEstacionamiento(String patente, Integer cantHoras) {
		CompraPuntual compra = new CompraPuntual(this,cantHoras);

		LocalTime horaMaxima = this.getControlEst().getHorarioDeFinalizacion();
		LocalTime horaDebitable = horaMaxima.plusHours(cantHoras);
		LocalTime horaFin = horaDebitable.isAfter(horaMaxima)
						? horaMaxima
						: horaDebitable;

		EstacionamientoPuntual est = new EstacionamientoPuntual(
				patente, LocalTime.now(), horaFin, compra);
		this.getControlEst().registrarEstacionamiento(est);
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
