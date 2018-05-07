package packJuego;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

public class ListaPelotas {

	private ArrayList<Pelota> pelotas;

	public ListaPelotas() {
		pelotas = new ArrayList<Pelota>();
	}

	private Iterator<Pelota> getIterator() {
		return pelotas.iterator();
	}

	public int size() {
		return pelotas.size();
	}

	public void aniadirPelota(Pelota pPelota) {
		if (!pelotas.contains(pPelota)) {
			pelotas.add(pPelota);
		}
	}

	public void eliminarPelota(int pId) {
		pelotas.remove(buscarPelota(pId));
		if (pelotas.size() <= 0) {
			Pelota p = new Pelota(DatosJuego.ANCHURA / 2, DatosJuego.ALTURA / 2, pId);
			pelotas.add(p);
		}
	}

	public Pelota buscarPelota(int pId) {
		boolean found = false;
		Iterator<Pelota> itr = getIterator();
		Pelota p = null;
		while (itr.hasNext() && !found) {
			p = itr.next();
			if (p.getId() == pId) {
				found = true;
			}
		}
		if (found) {
			return p;
		} else {
			return null;
		}
	}

	public void mover() {
		for (int i = 0; i < size(); i++) {
			pelotas.get(i).mover();
		}
	}

	public void comprobarParedes() {
		for (int i = 0; i < size(); i++) {
			pelotas.get(i).comprobarParedes();
		}
	}

	public void pintar(Graphics g) {
		for (int i = 0; i < size(); i++) {
			pelotas.get(i).pintar(g);
		}
	}

	public void comprobarRaqueta(Raqueta raqueta) {
		for (int i = 0; i < size(); i++) {
			pelotas.get(i).comprobarRaqueta(raqueta);
		}
	}

	public void aumentarVelocidad() {
		for (int i = 0; i < size(); i++) {
			pelotas.get(i).aumentarVelocidad();
		}
	}

	public int getPosyPrimeraPelota() {
		return pelotas.get(0).getPosy();
	}

	public void comprobarModificadores() {
		for (int i = 0; i < size(); i++) {
			pelotas.get(i).comprobarModificadores();
		}
	}
}
