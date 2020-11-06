package appEstacionamiento.estadoDeMoviemiento;

import appEstacionamiento.AppEstacionamiento;

public class Manejando extends EstadoDeMovimiento {

	public Manejando(AppEstacionamiento app) {
		super(app);
	}

	@Override
	public void isDriving() {}

	@Override
	public void isWalking() {
		this.getApp().setEstadoDeMovimiento(new Caminando(this.getApp()));
		this.getApp().comenzoAManejar();
	}
}
