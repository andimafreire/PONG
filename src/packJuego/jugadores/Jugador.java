package packJuego.jugadores;

import java.awt.Graphics;

import packJuego.Raqueta;
import packJuego.StopWatch;

public class Jugador {

	private String usuario;
	private Raqueta raqueta;
	private int tantos;
	private boolean ralentizar;
	private StopWatch temporizador;

	public Jugador(int pX, String pUsuario) {
		raqueta = new Raqueta(pX);
		tantos = 0;
		usuario = pUsuario;
		ralentizar = false;
	}

	public void update() {
		if (temporizador != null && temporizador.elapsedTime() >= 15000) {
			desRalentizar();
		}
		raqueta.update();
	}

	public void pintar(Graphics g) {
		raqueta.pintar(g);
	}

	public void mover(int i) {
		if (ralentizar) {
			raqueta.mover(i / 2);
		} else {
			raqueta.mover(i);
		}
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
	
	public int getVelRaqueta(){
		return raqueta.getVelocidad();
	}

	public void ralentizar() {
		ralentizar = true;
		temporizador = new StopWatch();
	}

	public void desRalentizar() {
		ralentizar = false;
		temporizador = null;
	}
}
