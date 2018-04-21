package packJuego;

import java.awt.Graphics;
import java.util.concurrent.ThreadLocalRandom;

public class Pelota {
	private int posx;
	private int posy;

	private double velx;
	private double vely;

	private int id;
	private int radio;
	private int velTotal;

	public Pelota(int pPosx, int pPosy, int pId) {
		posx = pPosx;
		posy = pPosy;
		id = pId;

		velTotal = DatosJuego.VELOCIDAD_INICIAL;
		radio = DatosJuego.RADIO_PELOTA;

		Double angulo = Math.toRadians(DatosJuego.ANGULO_INICIAL);
		velx = velTotal * Math.cos(angulo);
		vely = velTotal * Math.sin(angulo);
	}

	public void mover() {
		posx += velx;
		posy += vely;
	}

	public void comprobarParedes() {
		if ((posy <= DatosJuego.LIMITE_SUP && vely < 0) || (posy >= DatosJuego.ALTURA - radio * 2 && vely > 0)) {
			vely *= -1;
		}

		if (posx <= DatosJuego.LIMITE_IZQ) {
			Juego.getJuego().marcarTanto(true, id); // tanto para jugador 1
		} else if (posx + radio * 2 >= DatosJuego.ANCHURA) {
			Juego.getJuego().marcarTanto(false, id); // tanto para jugador 2
		}
	}

	public void pintar(Graphics g) {
		g.setColor(DatosJuego.getColorPelota());
		g.fillOval(posx, posy, radio * 2, radio * 2);
	}

	public void comprobarRaqueta(Raqueta pRaqueta) {
		if (colision(pRaqueta)) {
			if (pRaqueta.getPosx() == DatosJuego.LIMITE_IZQ) { // colisión con raqueta de jugador2
				if (pRaqueta.getVelocidad() > 0) { // raqueta en movimiento hacía abajo
					Double angulo = Math.toRadians(ThreadLocalRandom.current().nextInt(10, 80));
					velx = velTotal * Math.cos(angulo);
					vely = velTotal * Math.sin(angulo);
				} else if (pRaqueta.getVelocidad() < 0) { // raqueta en movimiento hacía arriba
					Double angulo = Math.toRadians(ThreadLocalRandom.current().nextInt(280, 350));
					velx = velTotal * Math.cos(angulo);
					vely = velTotal * Math.sin(angulo);
				} else {
					velx *= -1;
				}
			} else { // colisión con raqueta de jugador1
				if (pRaqueta.getVelocidad() > 0) { // raqueta en movimiento hacía abajo
					Double angulo = Math.toRadians(ThreadLocalRandom.current().nextInt(100, 170));
					velx = velTotal * Math.cos(angulo);
					vely = velTotal * Math.sin(angulo);
				} else if (pRaqueta.getVelocidad() < 0) { // raqueta en movimiento hacía arriba
					Double angulo = Math.toRadians(ThreadLocalRandom.current().nextInt(190, 260));
					velx = velTotal * Math.cos(angulo);
					vely = velTotal * Math.sin(angulo);
				} else {
					velx *= -1;
				}
			}
		}
	}

	private boolean colision(Raqueta pRaqueta) {
		if (posy + 2 * radio >= pRaqueta.getPosy() && posy <= pRaqueta.getPosy() + DatosJuego.ALTURA_RAQUETA) {
			if (pRaqueta.getPosx() == DatosJuego.LIMITE_IZQ) {
				if (posx <= DatosJuego.ANCHURA_RAQUETA) {
					if (velx < 0) {
						return true;
					}
				}
			} else {
				if (posx + 2 * radio >= pRaqueta.getPosx()) {
					if (velx > 0) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public int getId() {
		return id;
	}
}
