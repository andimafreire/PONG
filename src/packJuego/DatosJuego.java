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
	private static int tantos_victoria = 22;

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

	public static void setColorPelota(String pColor) {
		switch (pColor) {
		case "Negro":
			color_pelota = Color.BLACK;
			break;
		case "Azul":
			color_pelota = Color.BLUE;
			break;
		case "Cyan":
			color_pelota = Color.CYAN;
			break;
		case "Gris":
			color_pelota = Color.DARK_GRAY;
			break;
		case "Verde":
			color_pelota = Color.GREEN;
			break;
		case "Magenta":
			color_pelota = Color.MAGENTA;
			break;
		case "Naranja":
			color_pelota = Color.ORANGE;
			break;
		case "Rosa":
			color_pelota = Color.PINK;
			break;
		case "Rojo":
			color_pelota = Color.RED;
			break;
		case "Amarillo":
			color_pelota = Color.YELLOW;
			break;
		default:
			color_pelota = Color.BLACK;
			break;
		}
	}

	public static Color getColorPelota() {
		return color_pelota;
	}

	public static void setTantosVictoria(String pTantos) {
		switch (pTantos) {
		case "15":
			tantos_victoria = 15;
			break;
		case "16":
			tantos_victoria = 16;
			break;
		case "17":
			tantos_victoria = 17;
			break;
		case "18":
			tantos_victoria = 18;
			break;
		case "19":
			tantos_victoria = 19;
			break;
		case "20":
			tantos_victoria = 20;
			break;
		case "21":
			tantos_victoria = 21;
			break;
		case "22":
			tantos_victoria = 22;
			break;
		case "23":
			tantos_victoria = 23;
			break;
		case "24":
			tantos_victoria = 24;
			break;
		case "25":
			tantos_victoria = 25;
			break;
		default:
			tantos_victoria = 22;
			break;
		}
	}

	public static int getTantosVictoria() {
		return tantos_victoria;
	}
}
