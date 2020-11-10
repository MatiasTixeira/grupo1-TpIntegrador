package appEstacionamiento;

import java.time.LocalTime;

import appEstacionamiento.estadoDeMoviemiento.EstadoDeMovimiento;
import appEstacionamiento.modoDeActivacion.ModoDeActivacion;
import appEstacionamiento.modoDeAlerta.ModoDeAlerta;
import espacioGeografico.GPS;
import espacioGeografico.Ubicacion;
import respuestas.Respuesta;
import respuestas.RespuestaOperacionFallida;
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

	public AppEstacionamiento(
			String nroCelular,
			String patente,
			EstadoDeMovimiento estadoMov,
			ModoDeAlerta modoDeAlerta,
			ModoDeActivacion modoAct,
			IServerEstacionamientoApp server,
			GPS gps,
			GUI gui){

		this.setModoDeAlerta(modoDeAlerta);
		this.setModoDeActivacion(modoAct);
		this.setEstadoDeMovimiento(estadoMov);
		this.setNroCelular(nroCelular);
		this.setPatente(patente);
		this.setServer(server);
		this.setGps(gps);
		this.setGui(gui);
	}

	public String getNroCelular() {
		return this.nroCelular;
	}

	private void setNroCelular(String nroCelular) {
		this.nroCelular = nroCelular;
	}

	public String getPatente() {
		return this.patente;
	}

	private void setPatente(String patente) {
		this.patente = patente;
	}

	public void setEstadoDeMovimiento(EstadoDeMovimiento estadoDeMovimiento) {
		this.estadoDeMovimiento = estadoDeMovimiento;
	}

	public EstadoDeMovimiento getEstadoDeMovimiento() {
		return this.estadoDeMovimiento;
	}

	public ModoDeAlerta getModoDeAlerta() {
		return this.modoDeAlerta;
	}

	public void setModoDeAlerta(ModoDeAlerta modoDeAlerta) {
		this.modoDeAlerta = modoDeAlerta;
	}

	public ModoDeActivacion getModoDeActivacion() {
		return this.modoDeActivacion;
	}

	public void setModoDeActivacion(ModoDeActivacion modoDeActivacion) {
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

	public GPS getGps() {
		return this.gps;
	}

	public void setGps(GPS gps) {
		this.gps = gps;
	}

	@Override
	public void driving() {
		this.getEstadoDeMovimiento().isDriving();
	}

	@Override
	public void walking() {
		this.getEstadoDeMovimiento().isWalking();
	}

	//------------------
	
	
	public void iniciarEstacionamiento() {
		this.getGui().print(this.respuestaInicio().respuestaComoString());
	}

	public Respuesta respuestaInicio() {
		Respuesta respuesta = this.puedeIniciarEstacionamiento()
			? this.getServer().iniciarEstacionamiento(this.getNroCelular(), this.getPatente())
			: new RespuestaOperacionFallida();
		if (respuesta.operacionExitosa()) this.setUltimaUbicacionEst(this.getGps().ubicacionActual());
		return respuesta;
	}

	public void finalizarEstacionamiento() {
		this.getGui().print(this.respuestaFin().respuestaComoString());
	}

	public Respuesta respuestaFin() {
		return this.puedeFinalizarEstacionamiento()
			? this.getServer().finalizarEstacionamiento(this.getNroCelular())
			: new RespuestaOperacionFallida();
	}

	
	public void comenzoACaminar() {

		this.getModoDeAlerta().comenzoACaminar(this, this.getGui());

		this.getModoDeActivacion().comenzoACaminar(this, this.getGui());
	}

	public void comenzoAManejar() {

		this.getModoDeAlerta().comenzoAManejar(this, this.getGui());

		this.getModoDeActivacion().comenzoAManejar(this, this.getGui());
	}

	private Boolean estaEnZonaEstacionamiento() {
		return this.getServer().estaEnZonaDeEstacionamiento(this.ubicacionActual());
	}

	private Boolean tieneEstacionamientoVigente() {
		return this.getServer().tieneEstacionamientoVigente(this.getPatente());
	}

	private Boolean estaEnHorario() {
		//Creo que esta en sector de estacionamiento.
		return this.getServer().estaEnHorario();
	}

	private Ubicacion ubicacionActual() {
		return this.getGps().ubicacionActual();
	}

	public Boolean estaEnUltimaUbicacion() {
		return this.ubicacionActual().equals(this.getUltimaUbicacionEst());
	}

	public Boolean puedeIniciarEstacionamiento() {
		return this.estaEnZonaEstacionamiento()
				&& !this.tieneEstacionamientoVigente()
				&& this.estaEnHorario();
	}

	public Boolean puedeFinalizarEstacionamiento() {
		return this.estaEnUltimaUbicacion()
				&& this.tieneEstacionamientoVigente()
				&& this.estaEnHorario();
	}

}
