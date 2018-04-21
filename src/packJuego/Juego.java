package packJuego;

import java.awt.Font;
import java.awt.Graphics;

import packVentanas.VentanaJuego;

public class Juego {

	private static Juego miJuego;
	private ListaPelotas misPelotas;
	private Jugador jugador1;
	private Jugador jugador2;
	private Reloj reloj;

	private Juego() {
		misPelotas = new ListaPelotas();
		misPelotas.aniadirPelota(new Pelota(DatosJuego.ANCHURA / 2, DatosJuego.ALTURA / 2, 0));
		if (reloj == null) {
			this.reloj = new Reloj();
		} else {
			reloj.reset();
		}
	}

	public static Juego getJuego() {
		if (miJuego == null) {
			miJuego = new Juego();
		}
		return miJuego;
	}

	public Reloj getReloj() {
		return reloj;
	}

	public void setJugadores(String pUsuario1, String pUsuario2) {
		jugador1 = new Usuario(DatosJuego.ANCHURA - DatosJuego.ANCHURA_RAQUETA, pUsuario1);
		if (pUsuario2.equals("MaquinaL")) {
			jugador2 = new MaquinaL(DatosJuego.LIMITE_IZQ, pUsuario2);
		} else if (pUsuario2.equals("MaquinaT")) {
			jugador2 = new MaquinaT(DatosJuego.LIMITE_IZQ, pUsuario2);
		} else {
			jugador2 = new Usuario(DatosJuego.LIMITE_IZQ, pUsuario2);
		}
	}

	public void update() {
		misPelotas.mover();
		misPelotas.comprobarParedes();
		jugador1.update();
		jugador2.update();
		misPelotas.comprobarRaqueta(jugador1.getRaqueta());
		misPelotas.comprobarRaqueta(jugador2.getRaqueta());
	}

	public void pintar(Graphics g) {
		misPelotas.pintar(g);
		jugador1.pintar(g);
		jugador2.pintar(g);
		g.setColor(DatosJuego.COLOR_TEXTO);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		reloj.pintar(g);
		g.drawString(String.valueOf(jugador2.getNombre()), DatosJuego.LIMITE_IZQ + 40, 40);
		g.drawString(String.valueOf(jugador2.getTantos()), DatosJuego.LIMITE_IZQ + 40, 65);
		g.drawString(String.valueOf(jugador1.getNombre()),
				DatosJuego.ANCHURA - g.getFontMetrics().stringWidth(jugador1.getNombre()) - 40, 40);
		g.drawString(String.valueOf(jugador1.getTantos()), DatosJuego.ANCHURA - 50, 65);
	}

	public void marcarTanto(boolean b, int pId) {
		if (b == true) {
			jugador1.sumarTanto();
			if (jugador1.getTantos() >= DatosJuego.getTantosVictoria()) {
				Juego.getJuego().ganar(jugador1.getNombre(), jugador2.getNombre(), jugador1.getTantos());
			}
		} else {
			jugador2.sumarTanto();
			if (jugador2.getTantos() >= DatosJuego.getTantosVictoria()) {
				Juego.getJuego().ganar(jugador2.getNombre(), jugador1.getNombre(), jugador2.getTantos());
			}
		}
		misPelotas.eliminarPelota(pId);
	}

	public void moverJugador2(int p) {
		jugador2.mover(p);
	}

	public void moverJugador1(int p) {
		jugador1.mover(p);
	}

	public void ganar(String pUsuario, String pRival, int pTantos) {
		VentanaJuego.end(pUsuario, pRival, pTantos);
	}
}
