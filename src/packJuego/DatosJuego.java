package packJuego;

import java.awt.Color;

public class DatosJuego {

	// Datos generales del juego
	public static final String TITULO = "Pong - Grupo Doce";
	public static final int ALTURA = 480;
	public static final int ANCHURA = 640;
	public static final int LIMITE_IZQ = 0;
	public static final int LIMITE_SUP = 0;
	public static final int FPS = 60;
	public static final Color FONDO = Color.WHITE;
	public static final Color COLOR_TEXTO = Color.BLACK;

	// Datos de las raquestas
	public static final int ALTURA_RAQUETA = 100;
	public static final int ANCHURA_RAQUETA = 20;
	public static final Color COLOR_RAQUETA = Color.BLUE;

	// Datos de la pelota
	public static final int RADIO_PELOTA = 12;
	public static final int VELOCIDAD_INICIAL = 7;
	public static final double ANGULO_INICIAL = 0.0;
	private static Color color_pelota = Color.BLACK;

	private DatosJuego() {}

	public static void setColorPelota(Color pColor) {
		color_pelota = pColor;
	}
	
	public static Color getColorPelota() {
		return color_pelota;
	}
}
