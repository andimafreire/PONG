package packJuego;

import java.awt.Font;
import java.awt.Graphics;
import java.util.concurrent.ThreadLocalRandom;

import packJuego.jugadores.Jugador;
import packJuego.jugadores.MaquinaL;
import packJuego.jugadores.MaquinaT;
import packJuego.jugadores.Usuario;
import packJuego.modificadores.Acelerador;
import packJuego.modificadores.Duplicador;
import packJuego.modificadores.Freno;
import packJuego.modificadores.ListaModificadores;
import packJuego.modificadores.Modificador;
import packVentanas.VentanaJuego;

public class Juego {

	private static Juego miJuego;
	private ListaPelotas misPelotas;
	private ListaModificadores modificadores;
	private Jugador jugador1;
	private Jugador jugador2;

	private Reloj reloj;

	private Juego() {
		misPelotas = new ListaPelotas();
		modificadores = new ListaModificadores();
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

	public String getNomJugador2() {
		return jugador2.getNombre();
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
		misPelotas.comprobarModificadores();
		jugador1.update();
		jugador2.update();
		misPelotas.comprobarRaqueta(jugador1.getRaqueta());
		misPelotas.comprobarRaqueta(jugador2.getRaqueta());
	}

	public void pintar(Graphics g) {
		misPelotas.pintar(g);
		modificadores.pintar(g);
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
		jugador1.desRalentizar();
		jugador2.desRalentizar();
		reloj.resetearTemporizador();
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

	public void aumentarVelocidad() {
		misPelotas.aumentarVelocidad();
	}

	public int getPosyPrimeraPelota() {
		return misPelotas.getPosyPrimeraPelota();
	}

	public Modificador buscarModificador(int pPosx, int pPosy) {
		return modificadores.buscarModificador(pPosx, pPosy);
	}

	public void aniadirPelota() {
		for (int i = 0; i < DatosJuego.NUM_MAX_PELOTAS; i++) {
			if (misPelotas.buscarPelota(i) == null)
				misPelotas.aniadirPelota(new Pelota(DatosJuego.ANCHURA / 2, DatosJuego.ALTURA / 2, i));
		}
	}

	public void ralentizarJ2() {
		jugador2.ralentizar();
	}
	
	public Jugador getJugador1() {
		return jugador1;
	}
	
	public Jugador getJugador2() {
		return jugador2;
	}

	public void ralentizarJ1() {
		jugador1.ralentizar();
	}

	public void insertarModificador() {
		int x = obtenerXAleatoria();
		int y = obtenerYAleatoria();
		while (!modificadores.puedePoner(x, y)) {
			x = obtenerXAleatoria();
			y = obtenerYAleatoria();
		}
		if (Math.random() <= 0.2) {
			if (misPelotas.size() < DatosJuego.NUM_MAX_PELOTAS
					&& modificadores.getNumDuplicadores() < DatosJuego.NUM_MAX_DUPLICADORES)
				modificadores.aniadirModificador(new Duplicador(x, y));

		} else if (Math.random() > 0.2 && Math.random() <= 0.6) {
			if (modificadores.getNumAceleradores() < DatosJuego.NUM_MAX_ACELERADORES)
				modificadores.aniadirModificador(new Acelerador(x, y));

		} else
			modificadores.aniadirModificador(new Freno(x, y));

	}

	private int obtenerXAleatoria() {
		return ThreadLocalRandom.current().nextInt(DatosJuego.ANCHURA_RAQUETA * 2,
				DatosJuego.ANCHURA - DatosJuego.ANCHURA_RAQUETA * 2 - DatosJuego.ANCHURA_MODIFICADOR);
	}

	private int obtenerYAleatoria() {
		return ThreadLocalRandom.current().nextInt(0, DatosJuego.ALTURA - DatosJuego.ALTURA_MODIFICADOR);
	}
}
