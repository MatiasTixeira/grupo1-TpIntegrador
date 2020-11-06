package appEstacionamiento;

import appEstacionamiento.estadoDeMoviemiento.EstadoDeMovimiento;
import appEstacionamiento.modoDeActivacion.ModoDeActivacion;
import appEstacionamiento.modoDeAlerta.ModoDeAlerta;
import espacioGeografico.GPS;
import espacioGeografico.Ubicacion;
import respuestas.Respuesta;
import serverEstacionamiento.IServerEstacionamientoApp;

public class AppEstacionamiento implements MovementSensor {
	private String nroCelular;
	private String patente;
	private EstadoDeMovimiento estadoDeMovimiento;
	private ModoDeAlerta modoDeAlerta;
	private ModoDeActivacion modoDeActivacion;
	private IServerEstacionamientoApp server;
	private Ubicacion ultimaUbicacionEst;
	private GPS gps;
	private GUI gui;

	public String getNroCelular() {
		return this.nroCelular;
	}

	public void setNroCelular(String nroCelular) {
		this.nroCelular = nroCelular;
	}

	public String getPatente() {
		return this.patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

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

	public IServerEstacionamientoApp getServer() {
		return server;
	}

	public void setServer(IServerEstacionamientoApp server) {
		this.server = server;
	}

	public Ubicacion getUltimaUbicacionEst() {
		return ultimaUbicacionEst;
	}

	public void setUltimaUbicacionEst(Ubicacion ultimaUbicacionEst) {
		this.ultimaUbicacionEst = ultimaUbicacionEst;
	}

	public GUI getGui() {
		return this.gui;
	}

	public void setGui(GUI gui) {
		this.gui = gui;
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
		Respuesta respuesta = this.getServer().iniciarEstacionamiento(
				this.getNroCelular(), this.getPatente());

		this.setUltimaUbicacionEst(this.getGps().ubicacionActual());
		this.getGui().print(respuesta.respuestaComoString());
	}

	public void finalizarEstacionamiento() {
		Respuesta respuesta = this.getServer().finalizarEstacionamiento(
				this.getNroCelular());

		this.getGui().print(respuesta.respuestaComoString());
	}

	public void comenzoACaminar() {

		if (!tieneEstacionamientoVigente() &&
				this.estaEnZonaEstacionamiento()) {

			this.getModoDeAlerta().comenzoACaminar(this.getGui());

			this.getModoDeActivacion().comenzoACaminar(
					this.getServer(),
					this.getPatente(),
					this.getNroCelular(),
					this.getGui());
		}
	}

	public void comenzoAManejar() {

		if (this.tieneEstacionamientoVigente() &&
				this.ubicacionActual().equals(this.getUltimaUbicacionEst())) {

			this.getModoDeAlerta().comenzoAManejar(this.getGui());

			this.getModoDeActivacion().comenzoAManejar(
					this.getServer(),
					this.getNroCelular(),
					this.getGui());
		}
	}

	private Boolean estaEnZonaEstacionamiento() {
		return this.getServer().estaEnZonaDeEstacionamiento(this.ubicacionActual());
	}

	public Ubicacion ubicacionActual() {
		return this.getGps().ubicacionActual();
	}

	private Boolean tieneEstacionamientoVigente() {
		return this.getServer().tieneEstacionamientoVigente(this.getPatente());
	}

	public GPS getGps() {
		return this.gps;
	}
}
