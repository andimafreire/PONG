package packJuego;

import java.awt.Graphics;
import java.util.Observable;

public class Reloj extends Observable implements Runnable {
	private long temporizador1;
	private long temporizador2;
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
		temporizador1 = System.currentTimeMillis();
		while (!stopped) {
			if (System.currentTimeMillis() - temporizador1 >= DatosJuego.FRECUENCIA_AUMENTO) {
				Juego.getJuego().aumentarVelocidad();
				temporizador1 = System.currentTimeMillis();
			}
			if (System.currentTimeMillis() - temporizador2 >= DatosJuego.FRECUENCIA_APARICION) {
				Juego.getJuego().insertarModificador();
				temporizador2 = System.currentTimeMillis();
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
	
	public void resetearTemporizador() {
		temporizador1 =  System.currentTimeMillis();
		temporizador2 =  System.currentTimeMillis();
	}
}
