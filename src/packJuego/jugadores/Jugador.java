package packJuego.jugadores;

import java.awt.Graphics;

import packJuego.Raqueta;

public class Jugador {

	private String usuario;
	private Raqueta raqueta;
	private int tantos;
	private boolean ralentizar;

	public Jugador(int pX, String pUsuario) {
		raqueta = new Raqueta(pX);
		tantos = 0;
		usuario = pUsuario;
		ralentizar = false;
	}

	public void update() {
		raqueta.update();
	}

	public void pintar(Graphics g) {
		raqueta.pintar(g);
	}

	public void mover(int i) {
		if (ralentizar) raqueta.mover(i/2);
		else raqueta.mover(i);
	}

	public void sumarTanto() {
		tantos++;
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

	public void ralentizar() {
		ralentizar = true;
	}

	public void desRalentizar() {
		ralentizar = false;
	}
}
