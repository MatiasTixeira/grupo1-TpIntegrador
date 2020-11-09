package appEstacionamiento.modoDeAlerta;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import appEstacionamiento.AppEstacionamiento;
import appEstacionamiento.GUI;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
class AlertaActivadaTest {
	AlertaActivada alertaA;
	GUI gui;
	AppEstacionamiento app;
	

	@BeforeEach
	public void setUp() {
		gui = mock(GUI.class);
		alertaA = new AlertaActivada();
		app = mock(AppEstacionamiento.class);
	}
	
	@Test
	void cuandoPuedeIniciarUnEstacionamientoLoAlerta() {
		when(app.puedeIniciarEstacionamiento()).thenReturn(true);
		alertaA.comenzoACaminar(app,gui);
		verify(gui).alert("Deberias comenzar un estacionamiento.");
	}
	
	@Test
	void cuandoNoPuedeIniciarUnEstacionamientoNoLoAlerta() {
		when(app.puedeIniciarEstacionamiento()).thenReturn(false);
		alertaA.comenzoACaminar(app,gui);
		verify(gui, never()).print(any(String.class));
	}

	@Test
	void cuandoPuedeFinalizarUnEstacionamientoLoAlerta() {
		when(app.puedeFinalizarEstacionamiento()).thenReturn(true);
		alertaA.comenzoAManejar(app,gui);
		verify(gui).alert("Deberias finalizar tu estacionamiento.");
	}
	
	@Test
	void cuandoNoPuedeFinalizarUnEstacionamientoNoLoAlerta() {
		when(app.puedeFinalizarEstacionamiento()).thenReturn(false);
		alertaA.comenzoAManejar(app,gui);
		verify(gui, never()).print(any(String.class));
	}
}
