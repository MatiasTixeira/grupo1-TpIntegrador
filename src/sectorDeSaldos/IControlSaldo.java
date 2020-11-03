package sectorDeSaldos;

public interface IControlSaldo {

	public Integer saldo(String numCelular);
	public void descontar(String numCelular, Integer cantADescontar);
	public void cargarSaldo(String numCelular, Integer saldoACargar);
	
}
