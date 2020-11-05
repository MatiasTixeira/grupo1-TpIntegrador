package suscripcion;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class GestorDeSuscripcionTest {
	GestorSuscripcion gestorSub;
	Suscriptor sub;
	Evento eve;
	
	@BeforeEach
	public void setUp() {
	gestorSub = new GestorSuscripcion();
	sub = mock(Suscriptor.class);
	eve = mock(Evento.class);
	}
	
	@Test
	void cuandoUnSuscripotorSeSuscribeEsteSeRegistraEnLaLista() {
		gestorSub.registrarSuscripcion(sub);
		assertEquals(1,gestorSub.getSuscriptores().size());
	}

	@Test
	void cuandoOcurreUnEventoTodosLosSuscriptoresDeLaListaSonAlertados() {
		gestorSub.registrarSuscripcion(sub);
		gestorSub.alertar(eve);
		verify(this.sub).update(any(Evento.class));
	}
}

