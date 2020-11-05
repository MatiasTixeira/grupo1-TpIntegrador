package puntoDeVenta;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import compras.CompraPuntual;
import compras.CompraSaldo;
import estacionamiento.EstacionamientoPuntual;
import sectorDeCompras.IRegistroCompras;
import sectorDeEstacionamiento.IControlDeEstacionamiento;
import sectorDeSaldos.IControlSaldo;

class PuntoDeVentaTest {
	PuntoDeVenta punto;
	IControlDeEstacionamiento ctrlEst;
	IRegistroCompras regis;
	IControlSaldo ctrlSal;

	@BeforeEach
	public void setUp() {
		this.ctrlEst = mock(IControlDeEstacionamiento.class);
		this.regis = mock(IRegistroCompras.class);
		this.ctrlSal = mock(IControlSaldo.class);
		this.punto = new PuntoDeVenta(ctrlSal,ctrlEst,regis);
	}

	@Test
	void cuandoQuierenComprarCreditoEnUnPuntoDeVentaSeCargaElSaldoYSeRegistraLaCompra() {
		this.punto.comprarCredito("112233", 15d);
		verify(regis).registrar( any(CompraSaldo.class));
		verify(ctrlSal).cargarSaldo("112233", 15d);
	}

	@Test
	void cuandoQuierenComprarUnEstacionamientoEnUnPuntoDeVentaSeRegistraLaCompraYElEstacionamiento() {
		this.punto.comprarEstacionamiento("112233", 15);
		verify(this.regis).registrar( any(CompraPuntual.class) );
		verify(this.ctrlEst).registrarEstacionamiento(any(EstacionamientoPuntual.class));
	}
}
