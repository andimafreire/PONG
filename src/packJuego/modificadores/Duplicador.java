package packJuego.modificadores;

import packJuego.DatosJuego;

public class Duplicador extends Modificador {

	public Duplicador(int pPosx, int pPosy) {
		super(pPosx, pPosy);
		setColor(DatosJuego.COLOR_DUPLICADOR);
	}
}
