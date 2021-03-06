package packVentanas;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import packDB.ConnSQL;
import packJuego.DatosJuego;
import packJuego.Juego;

public class VentanaJuego extends Canvas implements Runnable, KeyListener {
	private static final long serialVersionUID = 1L;

	private static Thread thread;
	private JFrame frame;
	private static boolean running = false;

	public VentanaJuego() {
		frame = new JFrame();
		Dimension size = new Dimension(DatosJuego.ANCHURA, DatosJuego.ALTURA);
		setPreferredSize(size);
		setFocusable(true);
		requestFocus();
	}

	public static void empezar(String pUsuario1, String pUsuario2) {
		Juego.getJuego().setJugadores(pUsuario1, pUsuario2);
		VentanaJuego juego = new VentanaJuego();
		juego.frame.setResizable(false);
		juego.frame.setTitle(DatosJuego.TITULO);
		juego.frame.add(juego);
		juego.frame.pack();
		juego.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		juego.frame.setLocationRelativeTo(null);
		juego.frame.setVisible(true);
		juego.start();
	}

	public static void end(String pGanador, String pPerdedor, int pTantos) {
		int minutos = Juego.getJuego().getReloj().getMinutos();
		int segundos = Juego.getJuego().getReloj().getSegundos();

		double factor;
		switch (pPerdedor) {
		case "MaquinaT":
			factor = 0.50;
			break;
		case "MaquinaL":
			factor = 0.75;
			break;
		default:
			factor = 1.0;
			break;
		}

		int puntos = (int) ((pTantos * 1000 - minutos * 600 - segundos * 60) * factor);
		if (!pGanador.equals("MaquinaT") && !pGanador.equals("MaquinaL")) {
			ConnSQL db = new ConnSQL();
			db.addPuntuacion(pGanador, pPerdedor, puntos);
		}
		JOptionPane.showMessageDialog(null, "El ganador es " + pGanador + "!!");

		Puntuaciones.crear(true);
		stop();
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
		addKeyListener(this);
	}

	public synchronized static void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0 / DatosJuego.FPS;
		double delta = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				Juego.getJuego().update();
				delta--;
			}
			pintar();
		}
	}

	public void pintar() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(DatosJuego.FONDO);
		g.fillRect(0, 0, getWidth(), getHeight());
		Juego.getJuego().pintar(g);
		g.dispose();
		bs.show();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		int code = arg0.getKeyCode();
		if (!Juego.getJuego().getNomJugador2().equals("MaquinaT")
				&& !Juego.getJuego().getNomJugador2().equals("MaquinaL")) {
			if (code == KeyEvent.VK_W)
				Juego.getJuego().moverJugador2(-DatosJuego.VELOCIDAD_RAQUETA);
			if (code == KeyEvent.VK_S)
				Juego.getJuego().moverJugador2(DatosJuego.VELOCIDAD_RAQUETA);
		}
		if (code == KeyEvent.VK_UP)
			Juego.getJuego().moverJugador1(-DatosJuego.VELOCIDAD_RAQUETA);
		if (code == KeyEvent.VK_DOWN)
			Juego.getJuego().moverJugador1(DatosJuego.VELOCIDAD_RAQUETA);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		int code = arg0.getKeyCode();
		if (!Juego.getJuego().getNomJugador2().equals("MaquinaT")
				&& !Juego.getJuego().getNomJugador2().equals("MaquinaL")) {
			if (code == KeyEvent.VK_W)
				Juego.getJuego().moverJugador2(0);
			if (code == KeyEvent.VK_S)
				Juego.getJuego().moverJugador2(0);
		}
		if (code == KeyEvent.VK_UP)
			Juego.getJuego().moverJugador1(0);
		if (code == KeyEvent.VK_DOWN)
			Juego.getJuego().moverJugador1(0);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

}
