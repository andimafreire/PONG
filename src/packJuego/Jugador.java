package packJuego;

import java.awt.Graphics;

public class Jugador {

	private Raqueta raqueta;
	private int tantos;
	
	public Jugador(int pX, int pAltoJuego) {
		raqueta = new Raqueta(pX, pAltoJuego);
		tantos = 0;
	}
	
	public void sumarTanto() {
		tantos++;
		
	}

	public void update() {
		raqueta.update();
		
	}

	public void pintar(Graphics g) {
		raqueta.pintar(g);	
	}

	public int getTantos() {	
		return tantos;
	}

	public void mover(int i) {
		raqueta.mover(i);	
	}

	public Raqueta getRaqueta() {
		return raqueta;
	}
	
}
