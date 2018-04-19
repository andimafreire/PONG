package packJuego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class Pelota {
	private int posx;
	private int posy;
	private double velx;
	private double vely;
	private int radio;
	private int id;
	private double angulo;

	public Pelota(int pPosx, int pPosy, int pId) {
		id = pId;
		posx = pPosx;
		posy = pPosy;
		radio = 10;
		angulo = Math.toRadians(0);
		velx = 1 * Math.cos(angulo);
		vely = 1 * Math.sin(angulo);
		/*
		 * if(Math.random() < 0.5) { velx *= -1; }
		 */
	}

	public void mover() {
		posx += velx;
		posy += vely;
	}

	public void comprobarParedes(int pAltura, int pAnchura) {
		if (posy <= 0 + radio || posy >= pAltura - radio * 2) {
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
			velx *= -1;
			if (pRaqueta.getPosx() == 0) {
				
				if (pRaqueta.getVelocidad() > 0) {
					if (angulo < Math.toRadians(70)) {
						angulo = Math.toRadians(angulo+Math.toRadians(20));
						velx = 7* Math.cos(angulo);
						vely = 7* Math.sin(angulo);
					}
				}else if(pRaqueta.getVelocidad() < 0) {
					if (angulo > Math.toRadians(290)) {
						angulo = Math.toRadians(angulo-Math.toRadians(20));
						velx = 7* Math.cos(angulo);
						vely = 7* Math.sin(angulo);
					}
				}
			}else {
				System.out.println("colision mi pala");
				if (pRaqueta.getVelocidad() > 0) {
					if (angulo > Math.toRadians(110)) {
						System.out.println("angulos");
						angulo = Math.toRadians(angulo-Math.toRadians(20));
						velx = 7* Math.cos(angulo);
						vely = 7* Math.sin(angulo);
					}
				}else if(pRaqueta.getVelocidad() < 0) {
					if (angulo < Math.toRadians(250)) {
						angulo = Math.toRadians(angulo+Math.toRadians(20));
						velx = 7* Math.cos(angulo);
						vely = 7* Math.sin(angulo);
					}
				}
			}
		}
	}

	private boolean colision(Raqueta pRaqueta) {
		if(posy >= pRaqueta.getPosy() && posy+2*radio <= pRaqueta.getPosy()+100) {
			if (pRaqueta.getPosx() == 0) {
				if(posx <= 20) {
					if(90 <= Math.toDegrees(angulo) && Math.toDegrees(angulo) <= 270) return true;
				}

			}else {
				if(posx+2*radio >= pRaqueta.getPosx()) {
					System.out.println(Math.toDegrees(angulo));
					if(90 >= Math.toDegrees(angulo) || Math.toDegrees(angulo) >= 270) return true;
				}
			}
		}
		return false;
	}

	public int getId() {
		return id;
	}

}
