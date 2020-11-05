 package sectorDeEstacionamiento;
import estacionamiento.Estacionamiento;

public interface IControlDeEstacionamiento {

	public void registrarEstacionamiento(Estacionamiento estacionamiento);
	public Boolean tieneEstacionamientoVigente(String patente);
	public Estacionamiento estacionamientoVigente(String num);
	public  void finalizarTodosLosEstacionamientos();
	public Boolean esHorarioDeEstacionamiento();
	
}

