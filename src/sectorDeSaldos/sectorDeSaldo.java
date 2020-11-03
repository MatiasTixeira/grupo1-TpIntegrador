package sectorDeSaldos;

import java.util.HashMap;
import java.util.Map;

public class sectorDeSaldo implements ISectorSaldo {

	//atributos 
	Map<String, Integer> creditoCelulares;
	
	
	//constructor 
	public sectorDeSaldo(Map<String, Integer>celularesYCreditos) {
		this.setCreditoCelulares(celularesYCreditos);
	}
	
	//metodos
	public Map<String, Integer> getSaldos(){
		return this.getCreditoCelulares();
	}

	public Map<String, Integer> getCreditoCelulares() {
		return creditoCelulares;
	}

	public void setCreditoCelulares(Map<String, Integer> creditoCelulares) {
		this.creditoCelulares = creditoCelulares;
	}

	public Integer saldo(String numCelular) {
		return this.getCreditoCelulares().get(numCelular);
	}
	
	public void descontar(String numCelular, Integer cantADescontar) {
		Integer valorCambiado = (this.getCreditoCelulares().get(numCelular)) - cantADescontar;
		this.getCreditoCelulares().remove(numCelular);
		this.getCreditoCelulares().put(numCelular, valorCambiado);
	}
	
	public void cargarSaldo(String numCelular, Integer saldoACargar) {
		Integer valorCambiado = (this.getCreditoCelulares().get(numCelular)) + saldoACargar;
		this.getCreditoCelulares().remove(numCelular);
		this.getCreditoCelulares().put(numCelular, valorCambiado);
	}

	public Boolean creditoSuficiente(Integer cantDeHoras, String numCelular) {
		Integer costoTotal = cantDeHoras * 40;
		Integer valor = this.getCreditoCelulares().get(numCelular);
		return costoTotal < valor;
	}
}
