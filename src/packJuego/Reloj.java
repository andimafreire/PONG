package packJuego;

import java.awt.Graphics;
import java.util.Observable;

public class Reloj extends Observable implements Runnable {
	private Thread t;
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
		while (!stopped) {
			if (segundos == 60) {
				minutos++;
				segundos = 0;
			}
			setChanged();
			String tiempo = (segundos < 10) ? minutos + ":0" + segundos : minutos + ":" + segundos;
			notifyObservers(tiempo);
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
		String tmpMin = Integer.toString(minutos);
		String tmpSeg = Integer.toString(segundos);

		if (minutos >= 0 && minutos <= 9) {
			tmpMin = "0" + tmpMin;
		}
		if (segundos >= 0 && segundos <= 9) {
			tmpSeg = "0" + tmpSeg;
		}
		g.drawString(String.valueOf(tmpMin + ":" + tmpSeg),
				DatosJuego.ANCHURA / 2 - g.getFontMetrics().stringWidth(tmpMin + ":" + tmpSeg) / 2, 20);
	}
}
