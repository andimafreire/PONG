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
	public static final Color COLOR_RAQUETA = Color.BLACK;
	public static final int VELOCIDAD_RAQUETA = 12;

	// Datos de la pelota
	public static final int RADIO_PELOTA = 12;
	public static final int VELOCIDAD_INICIAL = 7;
	public static final int NUM_MAX_PELOTAS = 2;
	private static Color color_pelota = Color.BLACK;
	public static final int FRECUENCIA_AUMENTO = 10000;// 10 SEGUNDOS

	// Datos de los Modificadores
	public static final int ANCHURA_MODIFICADOR = 10;
	public static final int ALTURA_MODIFICADOR = 20;
	public static final int FRECUENCIA_APARICION = 5000;// 5 SEGUNDOS
	public static final int NUM_MAX_DUPLICADORES = 1;
	public static final Color COLOR_ACELERADOR = Color.YELLOW;
	public static final Color COLOR_FRENO = Color.RED;
	public static final Color COLOR_DUPLICADOR = Color.ORANGE;
	public static final int NUM_MAX_ACELERADORES = 3;

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
		case "5":
			tantos_victoria = 5;
			break;
		case "10":
			tantos_victoria = 10;
			break;
		case "15":
			tantos_victoria = 15;
			break;
		case "20":
			tantos_victoria = 20;
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
