package packJuego;

import java.awt.Graphics;

import packJuego.modificadores.Modificador;

public class Pelota {
	private Double posx;
	private Double posy;
	private double vecx;
	private double vecy;

	private int id;
	private int radio;
	private int velocidad;

	public Pelota(int pPosx, int pPosy, int pId) {
		posx = (double) pPosx;
		posy = (double) pPosy;
		id = pId;
		vecx = 1;
		vecy = 0;
		velocidad = DatosJuego.VELOCIDAD_INICIAL;
		radio = DatosJuego.RADIO_PELOTA;

	}

	public void mover() {
		posx += vecx * velocidad;
		posy += vecy * velocidad;
	}

	public void comprobarParedes() {
		if ((posy <= DatosJuego.LIMITE_SUP && vecy < 0) || (posy >= DatosJuego.ALTURA - radio * 2 && vecy > 0)) {
			vecy *= -1;
		}

		if (posx <= DatosJuego.LIMITE_IZQ) {
			Juego.getJuego().marcarTanto(true, id); // tanto para jugador 1
		} else if (posx + radio * 2 >= DatosJuego.ANCHURA) {
			Juego.getJuego().marcarTanto(false, id); // tanto para jugador 2
		}
	}

	public void pintar(Graphics g) {
		g.setColor(DatosJuego.getColorPelota());
		g.fillOval(posx.intValue(), posy.intValue(), radio * 2, radio * 2);
	}

	public void comprobarRaqueta(Raqueta pRaqueta) {
		if (colision(pRaqueta)) {
			vecx *= -1;
			if (pRaqueta.getVelocidad() > 0) {
				vecy += 1; // se suma el vector de la pelota con el vector (0,1)
							// de la raqueta
				normalizarVec(); // se normaliza el nuevo vector (se mantiene la
									// dirección, pero cambia el modulo a 1)
			} else if (pRaqueta.getVelocidad() < 0) {
				vecy -= 1;
				normalizarVec();
			}
		}
	}

	private void normalizarVec() {
		double mag = Math.sqrt(vecx * vecx + vecy * vecy);
		vecx = vecx / mag;
		vecy = vecy / mag;
	}

	private boolean colision(Raqueta pRaqueta) {
		if (posy + 2 * radio >= pRaqueta.getPosy() && posy <= pRaqueta.getPosy() + DatosJuego.ALTURA_RAQUETA) {
			if (pRaqueta.getPosx() == DatosJuego.LIMITE_IZQ) {
				if (posx <= DatosJuego.ANCHURA_RAQUETA) {
					if (vecx < 0) {
						return true;
					}
				}
			} else {
				if (posx + 2 * radio >= pRaqueta.getPosx()) {
					if (vecx > 0) {
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

	public void aumentarVelocidad() {
		velocidad++;
	}

	public int getPosy() {
		return posy.intValue();
	}

	public void comprobarModificadores() {
		Modificador m = Juego.getJuego().buscarModificador(posx.intValue(), posy.intValue());
		if (m != null) {
			System.out.println(m.getClass().getSimpleName());
			switch (m.getClass().getSimpleName()) {
			case "Acelerador":
				System.out.println("Acelerador activado");
				velocidad *= 2;
				break;
			case "Freno":
				System.out.println("Freno activado");
				if (vecx < 0)
					Juego.getJuego().ralentizarJ2();
				else
					Juego.getJuego().ralentizarJ1();
				break;
			case "Duplicador":
				System.out.println("Duplicador activado");
				Juego.getJuego().aniadirPelota();
				break;
			}
		}
	}
}
