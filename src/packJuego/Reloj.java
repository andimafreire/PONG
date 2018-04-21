package packJuego;

import java.awt.Graphics;
import java.util.Observable;

public class Reloj extends Observable implements Runnable {
	private long start;
	private Thread t;
	private String tiempo;
	private int minutos;
	private int segundos;
	private volatile boolean stopped;

	public Reloj() {
		this.t = new Thread(this, "Reloj");
		this.t.start();
	}

	public int getMinutos() {
		return minutos;
	}

	public int getSegundos() {
		return segundos;
	}

	@Override
	public void run() {
		minutos = 0;
		segundos = 0;
		start = System.currentTimeMillis();
		while (!stopped) {
			if (System.currentTimeMillis() - start >= 10000) {
				Juego.getJuego().aumentarVelocidad();
				start = System.currentTimeMillis();
			}
			if (segundos == 60) {
				minutos++;
				segundos = 0;
			}
			tiempo = (segundos < 10) ? minutos + ":0" + segundos : minutos + ":" + segundos;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			segundos++;
		}
	}

	public void reset() {
		minutos = 0;
		segundos = 0;
	}

	public void pintar(Graphics g) {
		g.drawString(tiempo, DatosJuego.ANCHURA / 2 - g.getFontMetrics().stringWidth(tiempo) / 2, 20);
	}
}
