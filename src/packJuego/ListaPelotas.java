package packJuego;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

public class ListaPelotas {

	private ArrayList<Pelota> pelotas;

	public ListaPelotas(){
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

	public void eliminarPelota(Pelota pPelota) {
		if (pelotas.contains(pPelota)) {
			pelotas.remove(pPelota);
		}
	}

	public Pelota buscarPelota(Pelota pPelota) {
		boolean found = false;
		Iterator<Pelota> itr = getIterator();
		Pelota p = itr.next();
		while (itr.hasNext() && !found) {
			if (itr.equals(pPelota)) {
				found = true;
			} else {
				p = itr.next();
			}
		}
		if (found) {
			return p;
		} else {
			return null;
		}
	}

	public void mover() {
		for (Pelota p : pelotas) p.mover();
	}

	public void comprobarParedes(int altura, int anchura) {
		for (Pelota p : pelotas) p.comprobarParedes(altura, anchura);	
	}

	public void pintar(Graphics g) {
		for (Pelota p : pelotas) p.pintar(g);
	}

	public void comprobarRaqueta(Raqueta raqueta) {
		for (Pelota p : pelotas) p.comprobarRaqueta(raqueta);	
	}
}
