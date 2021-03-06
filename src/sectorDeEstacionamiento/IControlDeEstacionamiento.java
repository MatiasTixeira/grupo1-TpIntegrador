 package sectorDeEstacionamiento;
import java.time.LocalTime;

import estacionamiento.Estacionamiento;

public interface IControlDeEstacionamiento {

	public void registrarEstacionamiento(Estacionamiento estacionamiento);
	public Boolean tieneEstacionamientoVigenteConPatente(String patente);
	public Estacionamiento estacionamientoVigente(String num);
	public  void finalizarTodosLosEstacionamientos();
	public Boolean esHorarioDeEstacionamiento();
	public LocalTime getHoraFin();
	public Double getPrecioPorHora();
	public Boolean tieneEstacionamientoVigenteConCelular(String celular);
	public LocalTime getHoraInicio();

}

