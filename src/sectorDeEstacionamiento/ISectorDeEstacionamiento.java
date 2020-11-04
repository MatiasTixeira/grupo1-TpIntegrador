package sectorDeEstacionamiento;
import java.time.LocalTime;
import java.util.ArrayList;
import estacionamiento.Estacionamiento;

public interface ISectorDeEstacionamiento extends IControlDeEstacionamiento{
	public ArrayList<Estacionamiento> getEstacionamientos();
	public void setHoraInicio(LocalTime horaInicio);
	public void setHoraFin(LocalTime horaFin);
	public void setPrecioPorHora(Double precioPorHora);
	
}
