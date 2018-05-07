package packJuego.modificadores;

import java.awt.Graphics;
import packJuego.DatosJuego;
import java.awt.Color;

public abstract class Modificador {
	private int ancho;
	private int alto;
	private int posx;
	private int posy;
	private Color color;

	public Modificador(int pPosx, int pPosy) {
		ancho = DatosJuego.ANCHURA_MODIFICADOR;
		alto = DatosJuego.ALTURA_MODIFICADOR;
		posx = pPosx;
		posy = pPosy;
	}

	public void pintar(Graphics g) {
		g.setColor(color);
		g.fillRect(posx, posy, ancho, alto);
	}

	public int getPosx() {
		return posx;
	}

	public int getPosy() {
		return posy;
	}

	protected void setColor(Color pColor) {
		color = pColor;
	}

}
