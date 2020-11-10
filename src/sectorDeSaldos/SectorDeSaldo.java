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
		Double saldo = 
				this.contieneAlCelular(numCelular)
			? this.getCreditoCelulares().get(numCelular)
			: 0;
		return saldo;
	}

	private boolean contieneAlCelular(String numCelular) {
		return this.getCreditoCelulares().containsKey(numCelular);
	}
	
	public void descontar(String numCelular, Double cantADescontar) {
		Double valorCambiado = (this.saldo(numCelular)) - cantADescontar;
		cambiarSaldoA(numCelular, valorCambiado);
	}
	
	public void cargarSaldo(String numCelular, Double saldoACargar) {
		Double valorCambiado = (this.saldo(numCelular)) + saldoACargar;
		cambiarSaldoA(numCelular, valorCambiado);
	}

	private void cambiarSaldoA(String numCelular, Double valorCambiado) {
		this.getCreditoCelulares().remove(numCelular);
		this.getCreditoCelulares().put(numCelular, valorCambiado);
	}
	
	public Boolean creditoSuficiente(Double cantDeHoras, String numCelular) {
		Double costoTotal = cantDeHoras * 40;
		Double valor = this.saldo(numCelular);
		return costoTotal < valor;
	}
}
