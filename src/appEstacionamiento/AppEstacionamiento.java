package appEstacionamiento;

import appEstacionamiento.estadoDeMoviemiento.EstadoDeMovimiento;
import appEstacionamiento.modoDeActivacion.ModoDeActivacion;
import appEstacionamiento.modoDeAlerta.ModoDeAlerta;
import serverEstacionamiento.IServerEstacionamientoApp;

public class AppEstacionamiento implements MovementSensor {
	private EstadoDeMovimiento estadoDeMovimiento;
	private ModoDeAlerta modoDeAlerta;
	private ModoDeActivacion modoDeActivacion;
	private IServerEstacionamientoApp server;

	public void setEstadoDeMovimiento(EstadoDeMovimiento estadoDeMovimiento) {
		this.estadoDeMovimiento = estadoDeMovimiento;
	}

	private EstadoDeMovimiento getEstadoDeMovimiento() {
		return this.estadoDeMovimiento;
	}

	public ModoDeAlerta getModoDeAlerta() {
		return this.modoDeAlerta;
	}

	private void setModoDeAlerta(ModoDeAlerta modoDeAlerta) {
		this.modoDeAlerta = modoDeAlerta;
	}

	public ModoDeActivacion getModoDeActivacion() {
		return this.modoDeActivacion;
	}

	private void setModoDeActivacion(ModoDeActivacion modoDeActivacion) {
		this.modoDeActivacion = modoDeActivacion;
	}

	@Override
	public void driving() {
		this.getEstadoDeMovimiento().isDriving();
	}

	@Override
	public void walking() {
		this.getEstadoDeMovimiento().isWalking();
	}

	public void iniciarEstacionamiento() {
		//server.iniciarEstacionamiento()
	}

	public void comenzoACaminar() {
		this.getModoDeAlerta().comenzoACaminar();
		this.getModoDeActivacion().comenzoACaminar();
	}

	public void comenzoAManejar() {
		this.getModoDeAlerta().comenzoAManejar();
		this.getModoDeActivacion().comenzoAManejar();
	}

}
