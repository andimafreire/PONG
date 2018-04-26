package packJuego.jugadores;

import packJuego.DatosJuego;
import packJuego.Juego;

public class MaquinaL extends Jugador {

	public MaquinaL(int pX, String pUsuario) {
		super(pX, pUsuario);
	}
	
	@Override
	public void update(){
		super.update();
		if (getRaqueta().getPosy() + DatosJuego.ALTURA_RAQUETA < Juego.getJuego().getPosyPrimeraPelota()) {
			getRaqueta().mover(DatosJuego.VELOCIDAD_RAQUETA);
		} else if (getRaqueta().getPosy() > Juego.getJuego().getPosyPrimeraPelota()+DatosJuego.RADIO_PELOTA*2) {
			getRaqueta().mover(-DatosJuego.VELOCIDAD_RAQUETA);
		}
		else getRaqueta().mover(0);
	}
}
