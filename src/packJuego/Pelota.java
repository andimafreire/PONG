package packJuego;

import java.awt.Color;
import java.awt.Graphics;


public class Pelota {
	private int posx;
	private int posy;
	private double velx;
	private double vely;
	private int radio;
	
	public Pelota(int pPosx, int pPosy) {
		posx = pPosx;
		posy = pPosy;
		radio = 10;
		double angulo = Math.PI;
		velx = 7* Math.cos(angulo);
		vely = 7* Math.sin(angulo);
		if(Math.random() < 0.5) {
			velx *= -1;
		}
	}
	
	public void mover() {
		posx += velx;
		posy += vely;
	}
	
	public void comprobarParedes(int pAltura, int pAnchura) {
		if(posy <= 0 || posy >= pAltura) {
			vely *= -1;
		}
		if(posx <= 0) {
			Juego.marcarTanto(true);//tanto para jugador 1
		}else if(posx+radio*2 >= pAnchura) {
			Juego.marcarTanto(false);//tanto para jugador 2
		}	
	}
	
	public void pintar(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(posx, posy, radio*2, radio*2);
	}

	public void comprobarRaqueta(Raqueta raqueta) {
		// TODO Auto-generated method stub
		
	}
	
}
