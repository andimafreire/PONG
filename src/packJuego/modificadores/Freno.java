package packJuego.modificadores;

import packJuego.DatosJuego;

public class Freno extends Modificador{

	public Freno(int pPosx, int pPosy) {
		super(pPosx, pPosy);
		setColor(DatosJuego.COLOR_FRENO);
	}

}
