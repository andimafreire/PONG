package packJuego;

public class Jugador {

	private Raqueta raqueta;
	private int tantos;
	
	public Jugador(int pX, int pAltoJuego) {
		raqueta = new Raqueta(pX, pAltoJuego);
	}
	
}
