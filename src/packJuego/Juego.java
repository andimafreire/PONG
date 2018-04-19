package packJuego;

import java.awt.Graphics;
import packVentanas.VentanaJuego;

public class Juego{
	

	private ListaPelotas misPelotas;
	private Jugador jugador1;
	private Jugador jugador2;
	private static Juego miJuego;
	
	
	private Juego() {
		misPelotas = new ListaPelotas();
		misPelotas.aniadirPelota(new Pelota(VentanaJuego.anchura/2, VentanaJuego.altura/2, 0));
	}
	
	public static Juego getJuego() {
		if (miJuego==null) miJuego = new Juego();
		return miJuego;
	}
	
	public void setJugadores(String pUsuario1, String pUsuario2) {
		jugador1 = new Usuario(VentanaJuego.anchura - 20, VentanaJuego.altura, pUsuario1);
		if(pUsuario2.equals("MaquinaL"))
			jugador2 = new MaquinaL(0, VentanaJuego.altura);
		else if (pUsuario2.equals("MaquinaL"))
			jugador2 = new MaquinaT(0, VentanaJuego.altura);
		else jugador2 = new Usuario(0, VentanaJuego.altura, pUsuario2);
	}

	
	public void update() {
		misPelotas.mover();
		misPelotas.comprobarParedes(VentanaJuego.altura, VentanaJuego.anchura);
		jugador1.update();
		jugador2.update();
		misPelotas.comprobarRaqueta(jugador1.getRaqueta());
		misPelotas.comprobarRaqueta(jugador2.getRaqueta());
	}
	
	public void pintar(Graphics g) {
		misPelotas.pintar(g);
		jugador1.pintar(g);
		jugador2.pintar(g);
		g.drawString(String.valueOf(jugador2.getTantos()), 32, 40);
		g.drawString(String.valueOf(jugador1.getTantos()), VentanaJuego.anchura-64, 40);
	}

	public void marcarTanto(boolean b, int pId) {
		if(b == true) jugador1.sumarTanto();
		else jugador2.sumarTanto();
		misPelotas.eliminarPelota(pId);
	}

	public void moverJugador2(int p) {
		 jugador2.mover(p);		 
	}

	public void moverJugador1(int p) {
		 jugador1.mover(p);
	}

	
}
