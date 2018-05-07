package packJuego;

import java.awt.Graphics;

public class Raqueta {

	private int alto;
	private int ancho;

	private int posx;
	private int posy;

	private int velocidad;

	public Raqueta(int pX) {
		alto = DatosJuego.ALTURA_RAQUETA;
		ancho = DatosJuego.ANCHURA_RAQUETA;

		posx = pX;
		posy = DatosJuego.ALTURA / 2 - alto / 2;
		velocidad = 0;
	}

	public void update() {
		posy += velocidad;
		if (posy < 0) {
			posy = 0;
		} else if (posy > DatosJuego.ALTURA - alto) {
			posy = DatosJuego.ALTURA - alto;
		}
	}

	public void mover(int p) {
		velocidad = p;
	}

	public void pintar(Graphics g) {
		g.setColor(DatosJuego.COLOR_RAQUETA);
		g.fillRect(posx, posy, ancho, alto);
	}

	public int getPosx() {
		return posx;
	}

	public int getPosy() {
		return posy;
	}

	public int getVelocidad() {
		return velocidad;
	}
}
