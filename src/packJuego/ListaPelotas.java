package packJuego;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import packVentanas.VentanaJuego;

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

	public void eliminarPelota(int pId) {
		pelotas.remove(buscarPelota(pId));
		if (pelotas.size()<=0) {
			Pelota p = new Pelota(VentanaJuego.anchura/2, VentanaJuego.altura/2, pId);
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
