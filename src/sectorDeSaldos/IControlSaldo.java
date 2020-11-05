package sectorDeSaldos;

public interface IControlSaldo {

	public Double saldo(String numCelular);
	public void descontar(String numCelular, Double cantADescontar);
	public void cargarSaldo(String numCelular, Double saldoACargar);
	
}
