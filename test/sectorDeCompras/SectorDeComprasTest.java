package sectorDeCompras;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import compras.Compra;
import compras.CompraPuntual;
import compras.CompraSaldo;

class SectorDeComprasTest{
	SectorDeCompras sectorDeCompras;
	@BeforeEach
	public void setUp() {
		sectorDeCompras= new SectorDeCompras();
	}
	
	@Test
	void testConstructor() {
		assertEquals(0,sectorDeCompras.getCompras().size());
	}
	
	@Test
	void alRegistrarUnaCompraLaGuardaEnSuListaDeCompras() {
		Compra compra1 = mock(CompraPuntual.class);
		Compra compra2 = mock(CompraSaldo.class);
		
		sectorDeCompras.registrar(compra1);
		assertEquals(1,sectorDeCompras.getCompras().size());
		
		sectorDeCompras.registrar(compra2);
		assertEquals(2,sectorDeCompras.getCompras().size());	
	}

}
