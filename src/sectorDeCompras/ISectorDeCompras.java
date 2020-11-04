package sectorDeCompras;
import java.util.ArrayList;
import compras.Compra;

public interface ISectorDeCompras extends IRegistroCompras{
	public ArrayList<Compra> getCompras();
}
