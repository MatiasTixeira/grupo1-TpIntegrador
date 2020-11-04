package sectorDeCompras;

import java.util.ArrayList;

import compras.Compra;

public class SectorDeCompras implements ISectorDeCompras {
	private ArrayList<Compra> compras;
	
	public SectorDeCompras() {
		this.setCompras(new ArrayList<Compra>());
	}
	
	public void setCompras(ArrayList<Compra> compras) {
		this.compras = compras;
	}
	
	@Override
	public void registrar(Compra compra) {
		this.getCompras().add(compra);
		
	}

	@Override
	public ArrayList<Compra> getCompras() {
		return this.compras;
	}

}
