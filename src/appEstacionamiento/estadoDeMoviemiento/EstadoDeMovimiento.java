package appEstacionamiento.estadoDeMoviemiento;

import appEstacionamiento.AppEstacionamiento; 

public abstract class EstadoDeMovimiento {
	private AppEstacionamiento app;

	public EstadoDeMovimiento(AppEstacionamiento app) {
		this.setApp(app);
	}

	public AppEstacionamiento getApp() {
		return this.app;
	}

	public void setApp(AppEstacionamiento app) {
		this.app = app;
	}

	public abstract void isDriving();

	public abstract void isWalking();
}

