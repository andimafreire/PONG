package packVentanas;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import packDB.ConnSQL;

public class Puntuaciones {

	private static int width = 350, height = 300;
	private static ConnSQL db = new ConnSQL();
	private static JFrame frame;

	public static void crear(Boolean fin) {
		frame = new JFrame("Puntuaciones");
		frame.setSize(width, height);
		if (fin) {
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		placeComponents(panel);

		frame.setVisible(true);
	}

	private static void placeComponents(JPanel panel) {

		panel.setLayout(null);
		String[][] puntuaciones = db.getRanking();
		
		for (int i = 0; i < puntuaciones[0].length; i++) {
			JLabel ranking = new JLabel("TOP " + (i + 1) + ":   " + puntuaciones[0][i] + " (" + puntuaciones[2][i]
					+ " puntos vs " + puntuaciones[1][i] + ")");
			ranking.setBounds(20, 25 * i, 290, 25);
			panel.add(ranking);
		}
	}
}
