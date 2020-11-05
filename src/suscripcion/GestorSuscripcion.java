package suscripcion;

import java.util.ArrayList;
public class GestorSuscripcion implements IGestorDeSuscripcion{

	//atributos 
	ArrayList<Suscriptor> suscriptores;
	
	//constructores
	public GestorSuscripcion() {
		this.setSuscriptores(new ArrayList<Suscriptor>());
	}
	
	//metodos
	public void registrarSuscripcion(Suscriptor s) {
		this.getSuscriptores().add(s);
	}
	
	public void setSuscriptores(ArrayList<Suscriptor> suscriptores) {
		this.suscriptores = suscriptores;
	}
	
	public void alertar(Evento e) {
		for (Suscriptor sub : this.getSuscriptores()) {
			sub.update(e);
		}
	}
	
	public ArrayList<Suscriptor> getSuscriptores(){
		return this.suscriptores;
	}
}
