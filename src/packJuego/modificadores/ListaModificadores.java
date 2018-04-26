package packJuego.modificadores;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import packJuego.DatosJuego;

public class ListaModificadores {
	private ArrayList<Modificador> modificadores;

	public ListaModificadores() {
		modificadores = new ArrayList<Modificador>();
	}

	private Iterator<Modificador> getIterator() {
		return modificadores.iterator();
	}

	public void pintar(Graphics g) {
		for (Modificador m : modificadores) {
			m.pintar(g);
		}
	}
	public void aniadirModificador(Modificador pModificador) {
		if (!modificadores.contains(pModificador)) {
			modificadores.add(pModificador);
		}
	}

	public Modificador buscarModificador(int pPosx, int pPosy) {
		boolean found = false;
		Iterator<Modificador> itr = getIterator();
		Modificador m = null;
		while (itr.hasNext() && !found) {
			m = itr.next();
			if (colision(m.getPosx(),m.getPosy(),pPosx,pPosy)) {
				found = true;
			}
		}
		if (found) {
			modificadores.remove(m);
			return m;
		} else {
			return null;
		}
	}

	private boolean colision(int pPosxM, int pPosyM, int pPosxP, int pPosyP) {
		if (pPosyP + 2 * DatosJuego.RADIO_PELOTA >= pPosyM && pPosyP <= pPosyM + DatosJuego.ALTURA_MODIFICADOR &&
				pPosxP + 2 * DatosJuego.RADIO_PELOTA >= pPosxM && pPosxP <= pPosxM + DatosJuego.ANCHURA_MODIFICADOR)
				return true;
		else return false;
	}

	public boolean puedePoner(int pX, int pY) {
		boolean puede = true;
		Iterator<Modificador> itr = getIterator();
		Modificador m = null;
		while (itr.hasNext() && puede) {
			m = itr.next();
			if (superpuesto(m.getPosx(),m.getPosy(),pX,pY)) {
				puede = false;
			}
		}
		return puede;
	}

	private boolean superpuesto(int pPosxM1, int pPosyM1, int pPosxM2, int pPosyM2) {
		if (pPosyM1 + DatosJuego.ALTURA_MODIFICADOR >= pPosyM2 && pPosyM1 <= pPosyM2 + DatosJuego.ALTURA_MODIFICADOR &&
				pPosxM1 +  DatosJuego.ANCHURA_MODIFICADOR >= pPosxM2 && pPosxM1 <= pPosxM2 + DatosJuego.ANCHURA_MODIFICADOR)
				return true;
		else return false;
	}

	public int getNumDuplicadores() {
		int num = 0;
		for (Modificador m : modificadores) {
			if (m instanceof Duplicador) num++;
		}
		return num;
	}

	public int getNumAceleradores() {
		int num = 0;
		for (Modificador m : modificadores) {
			if (m instanceof Acelerador) num++;
		}
		return num;
	}
}
