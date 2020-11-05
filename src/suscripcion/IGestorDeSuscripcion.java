package suscripcion;

import java.util.ArrayList;

public interface IGestorDeSuscripcion {

	public void registrarSuscripcion(Suscriptor s);
	public void alertar(Evento e);
	public ArrayList<Suscriptor> getSuscriptores();
	
}
