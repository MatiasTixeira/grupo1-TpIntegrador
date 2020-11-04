package appEstacionamiento.estadoDeMoviemiento;

import appEstacionamiento.AppEstacionamiento;

public abstract class EstadoDeMovimiento {
	private AppEstacionamiento app;

	protected EstadoDeMovimiento(AppEstacionamiento app) {
		this.setApp(app);
	}

	protected AppEstacionamiento getApp() {
		return app;
	}

	protected void setApp(AppEstacionamiento app) {
		this.app = app;
	}

	public abstract void isDriving();

	public abstract void isWalking();
}
