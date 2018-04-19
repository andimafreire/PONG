package packJuego;


public class Pelota {
	private int posx;
	private int posy;
	private double velx;
	private double vely;
	private int radio;
	
	public Pelota(int pPosx, int pPosy) {
		posx = pPosx;
		posy = pPosy;
		radio = 10;
	}
	
	public void mover() {
		posx += velx;
		posy += vely;
	}
	
	
}
