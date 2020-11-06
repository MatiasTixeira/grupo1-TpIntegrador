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
		
		LocalTime horaMaxima = this.getControlEst().getHoraFin();
		LocalTime horaDebitable = horaMaxima.plusHours((long)cantHoras);
		LocalTime horaFin = horaDebitable.isAfter(horaMaxima) 
						? horaMaxima
						: horaDebitable;
		LocalTime horaActual = LocalTime.now();
		Integer horasCompradas = Math.max(1,horaFin.getHour() - horaActual.getHour());
		CompraPuntual compra = new CompraPuntual(this,horasCompradas);
		this.getControlCom().registrar(compra);
		
		EstacionamientoPuntual est = new EstacionamientoPuntual(
				patente, horaActual, horaFin, compra);
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