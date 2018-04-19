package packJuego;

public class Usuario extends Jugador{

	private String usuario;
	
	
	public Usuario(int pX, int pAltoJuego, String pUsuario){
		super(pX, pAltoJuego);
		usuario = pUsuario;
	}
}
