package serverEstacionamiento;

import java.time.LocalTime;

import espacioGeografico.Ubicacion;
import respuestas.Respuesta;

public interface IServerEstacionamientoApp {

	public Respuesta iniciarEstacionamiento(String nroCelular, String patente);

	public Respuesta finalizarEstacionamiento(String nroCelular);

	public Boolean estaEnZonaDeEstacionamiento(Ubicacion ubicacion);

	public Boolean tieneEstacionamientoVigente(String patente);

	public LocalTime getHoraFin();

	public LocalTime getHoraInicio();

	public Boolean estaEnHorario();



}
