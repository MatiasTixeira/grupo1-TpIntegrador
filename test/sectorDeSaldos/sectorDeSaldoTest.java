package sectorDeSaldos;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class sectorDeSaldoTest {
	sectorDeSaldo sectorDeSaldo;
	Map<String, Double> creditoCelulares;
	
	@BeforeEach
	public void setUp() {
		creditoCelulares = new HashMap<String,Double>();
		creditoCelulares.put("112233", 55d);
		creditoCelulares.put("223344", 555d);
		creditoCelulares.put("334455", 650d);
		creditoCelulares.put("556677", 5d);
	    sectorDeSaldo = new sectorDeSaldo(creditoCelulares);
	}
	
	@Test
	void elCelularNoPuedePagarElEstacionamiento() {
		assertEquals(false, sectorDeSaldo.creditoSuficiente(10d, "556677") );
	}
	
	@Test
	void elCelularSiPuedePagarElEstacionamiento() {
		assertEquals(true, sectorDeSaldo.creditoSuficiente(10d, "334455") );
	}
	
	@Test
	void aUnCelularSeLeDescuenta100DeCredito() {
		sectorDeSaldo.descontar("334455",500d);
		assertEquals(150, creditoCelulares.get("334455") );
	}
	
	@Test
	void aUnCelularSeLeCarga500DeCredito() {
		sectorDeSaldo.cargarSaldo("556677",500d);
		assertEquals(505, creditoCelulares.get("556677") );
	}

	@Test
	void elSaldoDelCelular334455EsDe650() {
		assertEquals(650, sectorDeSaldo.saldo("334455"));
	}
}
