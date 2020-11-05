package sectorDeCompras;

import java.util.ArrayList;

import compras.Compra;

public class SectorDeCompras implements ISectorDeCompras {
	private ArrayList<Compra> compras;
	private Integer proximoNumControl;
	
	public SectorDeCompras() {
		this.setCompras(new ArrayList<Compra>());
		this.proximoNumControl = 0;
	}
	
	public void setCompras(ArrayList<Compra> compras) {
		this.compras = compras;
	}
	
	@Override
	public Compra registrar(Compra compra) {
		compra.setNumeroDeControl(this.proximoNumControl);
		this.proximoNumControl += 1;
		this.getCompras().add(compra);
		return compra;
	}

	@Override
	public ArrayList<Compra> getCompras() {
		return this.compras;
	}

}
