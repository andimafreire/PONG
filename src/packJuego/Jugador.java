package packJuego;

import java.awt.Graphics;

public class Jugador {

	private String usuario;
	private Raqueta raqueta;
	private int tantos;

	public Jugador(int pX, String pUsuario) {
		raqueta = new Raqueta(pX);
		tantos = 0;
		usuario = pUsuario;
	}

	public void update() {
		raqueta.update();
	}

	public void pintar(Graphics g) {
		raqueta.pintar(g);
	}

	public void mover(int i) {
		raqueta.mover(i);
	}

	public void sumarTanto() {
		tantos++;
		if (tantos >= DatosJuego.getTantosVictoria()) {
			Juego.getJuego().ganar(usuario);
		}
	}

	public int getTantos() {
		return tantos;
	}

	public Raqueta getRaqueta() {
		return raqueta;
	}

	public String getNombre() {
		return usuario;
	}
}
