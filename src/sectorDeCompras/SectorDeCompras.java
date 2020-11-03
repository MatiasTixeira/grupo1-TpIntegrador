package sectorDeCompras;

import java.util.ArrayList;

import compras.Compra;

public class SectorDeCompras implements ISectorDeCompras {
	private ArrayList<Compra> compras;
	
	public SectorDeCompras() {
		compras = new ArrayList<Compra>();
	}
	@Override
	public void registrar(Compra compra) {
		this.compras.add(compra);
		
	}

	@Override
	public ArrayList<Compra> getCompras() {
		return this.compras;
	}

}
