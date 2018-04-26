package packJuego.jugadores;

import packJuego.DatosJuego;

public class MaquinaT extends Jugador {

	public MaquinaT(int pX, String pUsuario) {
		super(pX, pUsuario);
		getRaqueta().mover(-10);
	}
	
	@Override
	public void update(){
		super.update();
		if (getRaqueta().getPosy() <= 0) {
			getRaqueta().mover(10);
		} else if (getRaqueta().getPosy() >= DatosJuego.ALTURA - DatosJuego.ALTURA_RAQUETA) {
			getRaqueta().mover(-10);
		}
	}
}
