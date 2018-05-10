package packTest;

import static org.junit.Assert.*;

import org.junit.Test;

import packJuego.Pelota;
import packJuego.Raqueta;

public class PelotaTest {
	Pelota p1 = new Pelota(5, 5, 1);
	Pelota p2 = new Pelota(10, 10, 2);
	Raqueta r1 = new Raqueta(20);
	Raqueta r2 = new Raqueta(54);

	@Test
	public void test() {
		
		assertEquals(p2.getPosx(), 10);
		
		p1.mover();
		
		assertEquals(p1.getPosx(), 12);
		
		p1.aumentarVelocidad();
		p1.mover();
		
		assertEquals(p1.getPosx(), 20);
		
		p1.comprobarRaqueta(r2);
		
		assertEquals(p1.getPosx(), 20);
		
		r1.mover(-1000);
		r1.update();
		r1.mover(0);
		p1.comprobarRaqueta(r1);
		
		assertEquals(p1.getVecX(), -1.0, 0.1);
		
	}

}
