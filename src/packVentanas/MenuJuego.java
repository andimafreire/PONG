package packVentanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

import packJuego.DatosJuego;

import java.awt.Font;
import java.awt.SystemColor;

public class MenuJuego {

	private static int width = 250, height = 250;
	private static String usuario;
	private static JFrame frame;

	public static void crear(String pUsuario) {
		usuario = pUsuario;
		frame = new JFrame("Pong - Grupo Doce");
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		placeComponents(panel);

		frame.setVisible(true);
	}

	private static void placeComponents(JPanel panel) {

		panel.setLayout(null);

		// Jugar contra la Máquina Tonta
		JButton btnJvMT = new JButton("Jugador contra M\u00E1quina Tonta");
		btnJvMT.setBounds(10, 11, 222, 25);
		btnJvMT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				VentanaJuego.empezar(usuario, "MaquinaT");
			}
		});
		panel.add(btnJvMT);

		// Jugar contra la Máquina Lista
		JButton btnJvML = new JButton("Jugador contra M\u00E1quina Lista");
		btnJvML.setBounds(10, 47, 222, 25);
		btnJvML.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				VentanaJuego.empezar(usuario, "MaquinaL");
			}
		});
		panel.add(btnJvML);

		// Jugar contra otro Jugador
		JButton btnJvJ = new JButton("Jugador contra Jugador");
		btnJvJ.setBounds(10, 83, 222, 25);
		btnJvJ.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				SegundoLogin.crear(usuario);
			}
		});
		panel.add(btnJvJ);

		// Etiqueta seleccionar color de las pelotas
		JTextArea txtSelColor = new JTextArea();
		txtSelColor.setEditable(false);
		txtSelColor.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtSelColor.setBackground(SystemColor.menu);
		txtSelColor.setText("Color de las pelotas:");
		txtSelColor.setBounds(10, 119, 132, 20);
		panel.add(txtSelColor);

		// Desplegable de colores
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setToolTipText("");
		comboBox.setBounds(143, 119, 89, 20);
		comboBox.addItem("Negro");
		comboBox.addItem("Azul");
		comboBox.addItem("Cyan");
		comboBox.addItem("Gris");
		comboBox.addItem("Verde");
		comboBox.addItem("Magenta");
		comboBox.addItem("Naranja");
		comboBox.addItem("Rosa");
		comboBox.addItem("Rojo");
		comboBox.addItem("Amarillo");
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatosJuego.setColorPelota(comboBox.getSelectedItem().toString());
			}
		});
		panel.add(comboBox);

		// Etiqueta puntos para ganar
		JTextArea txtPtsGanar = new JTextArea();
		txtPtsGanar.setText("Puntos para ganar:");
		txtPtsGanar.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtPtsGanar.setEditable(false);
		txtPtsGanar.setBackground(SystemColor.menu);
		txtPtsGanar.setBounds(10, 150, 132, 20);
		panel.add(txtPtsGanar);

		// Desplegable de puntos para ganar
		JComboBox<String> comboBox2 = new JComboBox<String>();
		comboBox2.setToolTipText("");
		comboBox2.setBounds(143, 150, 89, 20);
		/* Detallado
		comboBox2.addItem("15");
		comboBox2.addItem("16");
		comboBox2.addItem("17");
		comboBox2.addItem("18");
		comboBox2.addItem("19");
		comboBox2.addItem("20");
		comboBox2.addItem("21");
		comboBox2.addItem("22");
		comboBox2.addItem("23");
		comboBox2.addItem("24");
		comboBox2.addItem("25");
		*/
		comboBox2.addItem("5");
		comboBox2.addItem("10");
		comboBox2.addItem("15");
		comboBox2.addItem("20");
		comboBox2.addItem("25");
		comboBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatosJuego.setTantosVictoria(comboBox2.getSelectedItem().toString());
			}
		});
		panel.add(comboBox2);

		// Jugar contra otro Jugador
		JButton btnRanking = new JButton("Ver el ranking actual");
		btnRanking.setBounds(10, 181, 222, 25);
		btnRanking.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Puntuaciones.crear(false);
			}
		});
		panel.add(btnRanking);
		
		DatosJuego.setTantosVictoria(comboBox2.getSelectedItem().toString());
		DatosJuego.setColorPelota(comboBox.getSelectedItem().toString());
		
	}
}
