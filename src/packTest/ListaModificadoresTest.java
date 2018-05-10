package packTest;

import static org.junit.Assert.*;

import org.junit.Test;

import packJuego.Pelota;
import packJuego.modificadores.*;

public class ListaModificadoresTest {
	ListaModificadores lm = new ListaModificadores();
	Modificador m1 = new Acelerador(5, 5);
	Modificador m2 = new Freno(5, 5);
	Modificador m3 = new Duplicador(7, 9);
	Modificador m4 = new Acelerador(4, 6);
	Pelota p1 = new Pelota(7, 8, 1);

	@Test
	public void test() {
		lm.aniadirModificador(m1);
		assertFalse(lm.puedePoner(5, 5));
		assertTrue(m1.equals(lm.buscarModificador(5, 5)));
		assertEquals(lm.buscarModificador(7, 5), null);

		assertEquals(lm.getNumDuplicadores(), 0);
		lm.aniadirModificador(m3);
		assertEquals(lm.getNumDuplicadores(), 1);

		lm.aniadirModificador(m3);
		assertEquals(lm.getNumDuplicadores(), 1);

		assertFalse(lm.puedePoner(m4.getPosx(), m4.getPosy()));
		assertEquals(lm.getNumAceleradores(), 0);
		assertEquals(lm.buscarModificador(p1.getPosx(), p1.getPosy()), m3);

	}

}
