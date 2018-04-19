package packVentanas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import packJuego.Juego;

public class VentanaJuego extends Canvas implements Runnable,KeyListener{
	
	private static String title = "Pong GrupoDoce";
	public final static int altura = 480;
	public final static int anchura = 640;
	
	private boolean running = false;
	private final int fps = 420;
	private Thread thread;
	private JFrame frame;
	
	
	public VentanaJuego() {
		frame = new JFrame();
		Dimension size = new Dimension(anchura,altura);
		setPreferredSize(size);
		setFocusable(true);
		requestFocus();
	}

	public static void main(String[] args) {
		Juego.getJuego().setJugadores("jsdj", "dsd");
		VentanaJuego juego = new VentanaJuego();
		juego.frame.setResizable(false);
		juego.frame.setTitle(title);
		juego.frame.add(juego);
		juego.frame.pack();
		juego.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		juego.frame.setLocationRelativeTo(null);
		juego.frame.setVisible(true);
		juego.start();
	
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
		addKeyListener(this);
	}
	

	
	public synchronized void stop() {
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
		final double ns = 1000000000.0/fps;
		double delta = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime = now;
			if(delta >=1) {
				Juego.getJuego().update();
				delta--;
			}
			pintar();
		}
	}
	
	public void pintar() {
		BufferStrategy bs = getBufferStrategy();
		if(bs==null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setFont(new Font("Arial",Font.BOLD,32));
		Juego.getJuego().pintar(g);
		g.dispose();
		bs.show();
	}
	

	@Override
	public void keyPressed(KeyEvent arg0) {
		int code = arg0.getKeyCode();
		if(code == KeyEvent.VK_W) Juego.getJuego().moverJugador2(-2); 
		if(code == KeyEvent.VK_S)  Juego.getJuego().moverJugador2(2);
		if(code == KeyEvent.VK_UP) Juego.getJuego().moverJugador1(-2); 
		if(code == KeyEvent.VK_DOWN) Juego.getJuego().moverJugador1(2);
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		int code = arg0.getKeyCode();
		if(code == KeyEvent.VK_W) Juego.getJuego().moverJugador2(0); 
		if(code == KeyEvent.VK_S) Juego.getJuego().moverJugador2(0);
		if(code == KeyEvent.VK_UP) Juego.getJuego().moverJugador1(0); 
		if(code == KeyEvent.VK_DOWN) Juego.getJuego().moverJugador1(0);
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
	
	}

	
}
