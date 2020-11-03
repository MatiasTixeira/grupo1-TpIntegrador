package sectorDeCompras;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import compras.Compra;
import compras.CompraPuntual;
import compras.CompraSaldo;

class SectorDeComprasTest{

	@Test
	void testConstructor() {
		SectorDeCompras sectorDeCompras= new SectorDeCompras();
		assertEquals(0,sectorDeCompras.getCompras().size());
	}
	@Test
	void alRegistrarUnaCompraLaGuardaEnSuListaDeCompras() {
		Compra compra1 = mock(CompraPuntual.class);
		Compra compra2 = mock(CompraSaldo.class);
		SectorDeCompras sectorDeCompras= new SectorDeCompras();
		
		sectorDeCompras.registrar(compra1);
		assertEquals(1,sectorDeCompras.getCompras().size());
		
		sectorDeCompras.registrar(compra2);
		assertEquals(2,sectorDeCompras.getCompras().size());
		
		
	}

}
