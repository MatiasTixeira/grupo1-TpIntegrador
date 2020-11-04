package appEstacionamiento.estadoDeMoviemiento;

import appEstacionamiento.AppEstacionamiento;

public class Caminando extends EstadoDeMovimiento {

	protected Caminando(AppEstacionamiento app) {
		super(app);
	}

	@Override
	public void isDriving() {
		this.getApp().setEstadoDeMovimiento(new Manejando(this.getApp()));
		this.getApp().comenzoACaminar();
	}

	@Override
	public void isWalking() {}
}
