package sectorDeSaldos;

import java.util.Map;

public class SectorDeSaldo implements ISectorSaldo {

	//atributos 
	private Map<String, Double> creditoCelulares;
	
	
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
		Double valorARetornar = 
		this.getCreditoCelulares().containsKey(numCelular)
			? this.getCreditoCelulares().get(numCelular)
			: 0;
		return valorARetornar;
	}
	
	public void descontar(String numCelular, Double cantADescontar) {
		Double valorCambiado = (this.getCreditoCelulares().get(numCelular)) - cantADescontar;
		cambiarSaldoA(numCelular, valorCambiado);
	}
	
	public void cargarSaldo(String numCelular, Double saldoACargar) {
		Double valorCambiado = (this.getCreditoCelulares().get(numCelular)) + saldoACargar;
		cambiarSaldoA(numCelular, valorCambiado);
	}

	private void cambiarSaldoA(String numCelular, Double valorCambiado) {
		this.getCreditoCelulares().remove(numCelular);
		this.getCreditoCelulares().put(numCelular, valorCambiado);
	}
	
	public Boolean creditoSuficiente(Double cantDeHoras, String numCelular) {
		Double costoTotal = cantDeHoras * 40;
		Double valor = this.getCreditoCelulares().get(numCelular);
		return costoTotal < valor;
	}
}
