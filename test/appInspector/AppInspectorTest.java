package appInspector;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sectorDeEstacionamiento.IControlDeEstacionamiento;
import sectorDeInfracciones.IRegistroDeInfraccion;
import sectorDeInfracciones.Infraccion;
import sectorDeZonas.ZonaDeEstacionamiento;

class AppInspectorTest {
	IControlDeEstacionamiento controlEstacionamiento;
	IRegistroDeInfraccion controlInfraccion;
	String patente;
	String nombre;
	AppInspector aplicacion;
	ZonaDeEstacionamiento zona;

	@BeforeEach
	public void setUp() {
		this.controlEstacionamiento = mock(IControlDeEstacionamiento.class);
		this.controlInfraccion = mock(IRegistroDeInfraccion.class);
		this.nombre = "juan";
		this.patente = "ABC123";
		this.zona = mock(ZonaDeEstacionamiento.class);
		this.aplicacion = new AppInspector(
				this.controlEstacionamiento, this.nombre ,this.controlInfraccion);
	}

	@Test
	void tieneEstacionamientoVigenteSeComunicaConControlEstacionamiento() {
		//testea si un estacionamiento esta vigente

		this.aplicacion.tieneEstacionamientoVigente(this.patente);

		verify(this.controlEstacionamiento).tieneEstacionamientoVigenteConPatente(patente);
	}

	@Test
	void registrarInfraccionEnviaUnaInfraccionAlControlDeInfracciones() {
		//Testea si le llega la infraccion a la interfaz

		this.aplicacion.registrarInfraccion(this.patente, this.zona);
		verify(this.controlInfraccion).registrar(any(Infraccion.class));

	}
}





