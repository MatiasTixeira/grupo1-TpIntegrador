package appInspector;
import estacionamiento.Estacionamiento;

public interface IControlDeEstacionamiento {

	public void registrarEstacionamiento(Estacionamiento e);
	public Boolean estaVigente(String p);
	public Estacionamiento estacionamiento(Integer num);
	public  void finalizarTodosLosEstacionamientos();
	public Boolean esHorarioDeEstacionamiento();
	
}
