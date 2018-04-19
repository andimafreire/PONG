package packJuego;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class Juego extends Canvas implements Runnable,KeyListener{
	
	private static String title = "Pong GrupoDoce";
	private int altura = 480;
	private int anchura = 640;
	private boolean running = false;
	private final int fps = 60;
	private Thread thread;
	private JFrame frame;
	private ListaPelotas misPelotas;
	private Jugador jugador1;
	private Jugador jugador2;
	
	
	public static void main(String[] args) {
		new Juego("xgs","dsf");
	
	}
	
	public Juego(String pUsuario1, String pUsuario2) {
		frame = new JFrame();
		misPelotas = new ListaPelotas();
		misPelotas.aniadirPelota(new Pelota(anchura/2,altura/2));
		jugador1 = new Usuario(anchura - 20, altura, pUsuario1);
		
		if(pUsuario2.equals("MaquinaL"))
			jugador2 = new MaquinaL(0, altura);
		else if (pUsuario2.equals("MaquinaL"))
			jugador2 = new MaquinaT(0, altura);
		else jugador2 = new Usuario(0, altura, pUsuario2);
		
		Dimension size = new Dimension(anchura,altura);
		setPreferredSize(size);
		setFocusable(true);
		requestFocus();
		frame.setResizable(false);
		frame.setTitle(title);
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		start();
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
				update();
				delta--;
			}

			pintar();
		}
	}

	public void update() {
		misPelotas.mover();
		misPelotas.comprobarParedes(altura, anchura);
		jugador1.update();
		jugador2.update();
		misPelotas.comprobarRaqueta(jugador1.getRaqueta());
		misPelotas.comprobarRaqueta(jugador2.getRaqueta());
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
		misPelotas.pintar(g);
		jugador1.pintar(g);
		jugador2.pintar(g);
		g.setFont(new Font("Ubuntu",Font.BOLD,32));
		g.drawString(String.valueOf(jugador2.getTantos()), 32, 40);
		g.drawString(String.valueOf(jugador1.getTantos()), anchura-64, 40);
		g.dispose();
		bs.show();
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		int code = arg0.getKeyCode();
		if(code == KeyEvent.VK_W) jugador2.mover(-10); 
		if(code == KeyEvent.VK_S) jugador2.mover(10);
		if(code == KeyEvent.VK_UP) jugador1.mover(-10); 
		if(code == KeyEvent.VK_DOWN) jugador1.mover(10);
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		int code = arg0.getKeyCode();
		if(code == KeyEvent.VK_W) jugador2.mover(0); 
		if(code == KeyEvent.VK_S) jugador2.mover(0);
		if(code == KeyEvent.VK_UP) jugador1.mover(0); 
		if(code == KeyEvent.VK_DOWN) jugador1.mover(0);
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
	
	}

	public static void marcarTanto(boolean b) {
		
		
	}

	
}
