package packJuego.modificadores;

import packJuego.DatosJuego;

public class Acelerador extends Modificador {

	public Acelerador(int pPosx, int pPosy) {
		super(pPosx, pPosy);
		setColor(DatosJuego.COLOR_ACELERADOR);
	}
}
