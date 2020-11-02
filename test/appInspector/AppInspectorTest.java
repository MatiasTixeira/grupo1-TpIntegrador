package appInspector;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import appInspector.IControlDeEstacionamiento;
import appInspector.IRegistroDeInfraccion;
import appInspector.Infraccion;

class AppInspectorTest {
	IControlDeEstacionamiento controlEstacionamiento;
	IRegistroDeInfraccion controlInfraccion;
	AppInspector aplicacion;
	String patente;
	Infraccion i;
	String zona;
	
	@BeforeEach
	public void setUp() {
		this.controlEstacionamiento = mock(IControlDeEstacionamiento.class);
		this.controlInfraccion = mock(IRegistroDeInfraccion.class);
		this.i = mock(Infraccion.class);
		this.patente = "ABC123";
		this.zona = "Bernal";
		String nombre = "juan";
		this.aplicacion = new AppInspector(this.controlEstacionamiento,nombre,this.controlInfraccion);
		when(controlEstacionamiento.estaVigente(this.patente)).thenReturn(true);
	}
	
	@Test
	void estaVigenteUnEstacionamientoPorEstaPatenteTest() {
		//testea si un estacionamiento esta vigente
		assertEquals(true, this.aplicacion.tieneEstacionamientoVigente(patente)) ;
	}
	
	@Test
	void registraUnaInfraccion() {
		//Testea si le llega la infraccion a la interfaz
		this.aplicacion.registrarInfraccion(this.patente, this.zona);
		verify(this.controlInfraccion).registrar(any(Infraccion.class));
		
	}	
}





