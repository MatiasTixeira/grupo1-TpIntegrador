package sectorDeSaldos;

import java.util.HashMap;
import java.util.Map;

public class SectorDeSaldo implements ISectorSaldo {

	//atributos 
	Map<String, Double> creditoCelulares;
	
	
	//constructor 
	public SectorDeSaldo(Map<String, Double>celularesYCreditos) {
		this.setCreditoCelulares(celularesYCreditos);
	}
	
	//metodos
	public Map<String, Double> getSaldos(){
		return this.getCreditoCelulares();
	}

	public Map<String, Double> getCreditoCelulares() {
		return creditoCelulares;
	}

	public void setCreditoCelulares(Map<String, Double> creditoCelulares) {
		this.creditoCelulares = creditoCelulares;
	}

	public Double saldo(String numCelular) {
		return this.getCreditoCelulares().get(numCelular);
	}
	
	public void descontar(String numCelular, Double cantADescontar) {
		Double valorCambiado = (this.getCreditoCelulares().get(numCelular)) - cantADescontar;
		this.getCreditoCelulares().remove(numCelular);
		this.getCreditoCelulares().put(numCelular, valorCambiado);
	}
	
	public void cargarSaldo(String numCelular, Double saldoACargar) {
		Double valorCambiado = (this.getCreditoCelulares().get(numCelular)) + saldoACargar;
		this.getCreditoCelulares().remove(numCelular);
		this.getCreditoCelulares().put(numCelular, valorCambiado);
	}

	public Boolean creditoSuficiente(Double cantDeHoras, String numCelular) {
		Double costoTotal = cantDeHoras * 40;
		Double valor = this.getCreditoCelulares().get(numCelular);
		return costoTotal < valor;
	}
}
