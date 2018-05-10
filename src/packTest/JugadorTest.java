package packTest;

import static org.junit.Assert.*;

import org.junit.Test;

import packJuego.DatosJuego;
import packJuego.jugadores.Jugador;

public class JugadorTest {
	Jugador j1 = new Jugador(0, "Patxi");
	@Test
	public void test() throws InterruptedException {
		
		j1.mover(4);
		j1.update();
	
		assertEquals(j1.getRaqueta().getPosy(),DatosJuego.ALTURA/2-DatosJuego.ALTURA_RAQUETA/2 + 4);
		assertEquals(j1.getVelRaqueta(),4);
		
		j1.ralentizar();
		j1.mover(4);
		j1.update();
		
		assertEquals(j1.getRaqueta().getPosy(),DatosJuego.ALTURA/2-DatosJuego.ALTURA_RAQUETA/2 + 6);
		j1.update();
		assertEquals(j1.getRaqueta().getPosy(),DatosJuego.ALTURA/2-DatosJuego.ALTURA_RAQUETA/2 + 8);
		
		j1.mover(4);
		j1.update();
		assertEquals(j1.getRaqueta().getPosy(),DatosJuego.ALTURA/2-DatosJuego.ALTURA_RAQUETA/2 + 10);
		
	}

}
