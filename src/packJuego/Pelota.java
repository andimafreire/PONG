package packJuego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Pelota {
	private int posx;
	private int posy;
	private double velx;
	private double vely;
	private int radio;
	private int id;
	private int velTotal;

	public Pelota(int pPosx, int pPosy, int pId) {
		id = pId;
		posx = pPosx;
		posy = pPosy;
		velTotal = 7;
		radio = 12;
		Double angulo = Math.toRadians(5.0);
		velx = velTotal * Math.cos(angulo);
		vely = velTotal * Math.sin(angulo);
		/*
		 * if(Math.random() < 0.5) { velx *= -1; }
		 */
	}

	public void mover() {
		posx += velx;
		posy += vely;
	}

	public void comprobarParedes(int pAltura, int pAnchura) {
		if ((posy <= 0 && vely < 0) || (posy >= pAltura - radio * 2 && vely > 0)) {
			vely *= -1;
		}
		if (posx <= 0) {
			Juego.getJuego().marcarTanto(true, id);// tanto para jugador 1
		} else if (posx + radio * 2 >= pAnchura) {
			Juego.getJuego().marcarTanto(false, id);// tanto para jugador 2
		}
	}

	public void pintar(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(posx, posy, radio * 2, radio * 2);
	}

	public void comprobarRaqueta(Raqueta pRaqueta) {
		if(colision(pRaqueta)) {
			
			if (pRaqueta.getPosx() == 0) {// colisión con raqueta de jugador2
				if (pRaqueta.getVelocidad() > 0) {//raqueta en movimiento hacía abajo
					Double angulo = Math.toRadians(ThreadLocalRandom.current().nextInt(10, 80));
					velx = velTotal* Math.cos(angulo);
					vely = velTotal* Math.sin(angulo);
				}else if(pRaqueta.getVelocidad() < 0) {//raqueta en movimiento hacía arriba
					Double angulo = Math.toRadians(ThreadLocalRandom.current().nextInt(280, 350));
					velx = velTotal* Math.cos(angulo);
					vely = velTotal* Math.sin(angulo);
				}else velx *= -1;
			
			}else {// colisión con raqueta de jugador1
				if (pRaqueta.getVelocidad() > 0) {//raqueta en movimiento hacía abajo
					Double angulo = Math.toRadians(ThreadLocalRandom.current().nextInt(100, 170));
					velx = velTotal* Math.cos(angulo);
					vely = velTotal* Math.sin(angulo);
				}else if(pRaqueta.getVelocidad() < 0) {//raqueta en movimiento hacía arriba
					Double angulo = Math.toRadians(ThreadLocalRandom.current().nextInt(190, 260));
					velx = velTotal* Math.cos(angulo);
					vely = velTotal* Math.sin(angulo);
				} else velx *= -1;
			}
		}
	}
	

	private boolean colision(Raqueta pRaqueta) {
		if(posy+2*radio >= pRaqueta.getPosy() && posy <= pRaqueta.getPosy()+100) {
			if (pRaqueta.getPosx() == 0) {
				if(posx <= 20) {
					if(velx < 0) return true;
				}

			}else {
				if(posx+2*radio >= pRaqueta.getPosx()) {
					if(velx > 0) return true;
				}
			}
		}
		return false;
	}

	public int getId() {
		return id;
	}

}
