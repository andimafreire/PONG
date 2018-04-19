package packJuego;

import java.awt.Color;
import java.awt.Graphics;

public class Raqueta {

	private int alto = 100;
	private int ancho = 25;
	private int posx;
	private int altoJuego;
	private int posy;
	private int velocidad;
	
	public Raqueta(int pX, int pAltoJuego){
		posx = pX;
		velocidad = 0;
		altoJuego = pAltoJuego;
		posy = pAltoJuego/2-alto/2;
	}
	
	public void update() {
		posy += velocidad;
		if (posy < 0) posy = 0;
		else if(posy > altoJuego-alto) posy = altoJuego-alto;
	}
	
	public void mover(int p) {
		velocidad = p;
	}
	
	public void pintar(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(posx, posy, ancho, alto);
	}
}
