package puntoDeVenta;

import java.time.LocalDate;
import java.time.LocalTime;

import compras.CompraPuntual;
import compras.CompraSaldo;
import estacionamiento.EstacionamientoPuntual;
import sectorDeCompras.IRegistroCompras;
import sectorDeEstacionamiento.IControlDeEstacionamiento;
import sectorDeSaldos.IControlSaldo;

public class PuntoDeVenta {

	//atributos
	private IControlSaldo controlSal;
	private IControlDeEstacionamiento controlEst;
	private IRegistroCompras controlCom;

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
		
		LocalDate fechaActual = LocalDate.now();
		LocalTime horaActual = LocalTime.now(); 
		
		CompraSaldo compra = new CompraSaldo(this,monto,num, fechaActual, horaActual);
		this.getControlCom().registrar(compra);
		this.getControlSal().cargarSaldo(num, monto);
	}

	public EstacionamientoPuntual comprarEstacionamiento(String patente, Integer cantHoras) {

		LocalTime horaActual = LocalTime.now();
		LocalTime horaMaxima = this.getControlEst().getHoraFin();
		Integer horasCompradas = Math.min(cantHoras, horaMaxima.getHour() - horaActual.getHour());
		LocalTime horaDebitable = horaActual.plusHours(horasCompradas);
		LocalTime horaFin = horaDebitable.isBefore(horaMaxima)
				? horaDebitable
				: horaMaxima;

		CompraPuntual compra = new CompraPuntual(this, horasCompradas, LocalDate.now(), LocalTime.now());
		this.getControlCom().registrar(compra);

		EstacionamientoPuntual est = new EstacionamientoPuntual(
				patente, horaActual, horaFin, compra);
		this.getControlEst().registrarEstacionamiento(est);
		return est;
	}

	private IControlSaldo getControlSal() {
		return controlSal;
	}

	private void setControlSal(IControlSaldo controlSal) {
		this.controlSal = controlSal;
	}

	private IControlDeEstacionamiento getControlEst() {
		return controlEst;
	}

	private void setControlEst(IControlDeEstacionamiento controlEst) {
		this.controlEst = controlEst;
	}

	private IRegistroCompras getControlCom() {
		return controlCom;
	}

	private void setControlCom(IRegistroCompras controlCom) {
		this.controlCom = controlCom;
	}
}
