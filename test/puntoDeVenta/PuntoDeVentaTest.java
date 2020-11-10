package puntoDeVenta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

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
	String patente;

	@BeforeEach
	public void setUp() {
		this.ctrlEst = mock(IControlDeEstacionamiento.class);
		this.regis = mock(IRegistroCompras.class);
		this.ctrlSal = mock(IControlSaldo.class);
		this.punto = new PuntoDeVenta(ctrlSal,ctrlEst,regis);
		this.patente = "112233";

		when(ctrlEst.getHoraFin()).thenReturn(LocalTime.of(20, 0));
	}

	@Test
	void comprarCreditoEnviaLaCompraAlRegistroCompraYElSaldoAControlSaldo() {
		this.punto.comprarCredito(this.patente, 15d);

		verify(this.regis).registrar(any(CompraSaldo.class));
		verify(this.ctrlSal).cargarSaldo(this.patente, 15d);
	}

	@Test
	void comprarEstacionamientoDe3HorasALas14Y30PermiteEstacionarHastaLas17Y30() {
		LocalTime horaActual = LocalTime.of(14, 30);
		EstacionamientoPuntual estacionamiento;

		try(MockedStatic<LocalTime> localTimeMock = Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)) {

			localTimeMock.when(LocalTime::now).thenReturn(horaActual);

			estacionamiento = this.punto.comprarEstacionamiento(this.patente, 3);
		}

		verify(this.regis).registrar(any(CompraPuntual.class));
		verify(this.ctrlEst).registrarEstacionamiento(any(EstacionamientoPuntual.class));

		assertEquals(estacionamiento.getHoraInicio(), LocalTime.of(14, 30));
		assertEquals(estacionamiento.getHoraFin(), LocalTime.of(17, 30));
	}

	@Test
	void comprarEstacionamientoDe10HorasALas19Y30PermiteEstacionarHastaLas20() {
		LocalTime horaActual = LocalTime.of(19, 30);
		EstacionamientoPuntual estacionamiento;

		try(MockedStatic<LocalTime> localTimeMock = Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)) {

			localTimeMock.when(LocalTime::now).thenReturn(horaActual);

			estacionamiento = this.punto.comprarEstacionamiento(this.patente, 10);
		}

		verify(this.regis).registrar(any(CompraPuntual.class));
		verify(this.ctrlEst).registrarEstacionamiento(any(EstacionamientoPuntual.class));

		assertEquals(estacionamiento.getHoraInicio(), LocalTime.of(19, 30));
		assertEquals(estacionamiento.getHoraFin(), LocalTime.of(20, 0));
	}

	@Test
	void comprarEstacionamientoDe6HorasALas15PermiteEstacionarHastaLas20() {
		LocalTime horaActual = LocalTime.of(15, 0);
		EstacionamientoPuntual estacionamiento;

		try(MockedStatic<LocalTime> localTimeMock = Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)) {

			localTimeMock.when(LocalTime::now).thenReturn(horaActual);

			estacionamiento = this.punto.comprarEstacionamiento(this.patente, 6);
		}

		verify(this.regis).registrar(any(CompraPuntual.class));
		verify(this.ctrlEst).registrarEstacionamiento(any(EstacionamientoPuntual.class));

		assertEquals(estacionamiento.getHoraInicio(), LocalTime.of(15, 0));
		assertEquals(estacionamiento.getHoraFin(), LocalTime.of(20, 0));
	}

}

