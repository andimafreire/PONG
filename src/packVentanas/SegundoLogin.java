package packVentanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import packDB.ConnSQL;

public class SegundoLogin {

	private static int width = 300, height = 150;
	private static ConnSQL db = new ConnSQL();
	private static JFrame frame;
	private static String usuario;

	public static void crear(String pUsuario) {
		usuario = pUsuario;
		frame = new JFrame("Pong - Grupo Doce");
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		JPanel panel = new JPanel();
		frame.add(panel);
		placeComponents(panel);

		frame.setVisible(true);
	}

	private static void placeComponents(JPanel panel) {

		panel.setLayout(null);

		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(10, 10, 80, 25);
		panel.add(usernameLabel);

		final JTextField usernameField = new JTextField(20);
		usernameField.setBounds(100, 10, 160, 25);
		panel.add(usernameField);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);

		final JPasswordField passwordField = new JPasswordField(20);
		passwordField.setBounds(100, 40, 160, 25);
		panel.add(passwordField);

		JButton loginButton = new JButton("Login or register");
		loginButton.setBounds(110, 75, 140, 25);
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String username = usernameField.getText();
				username = username.toLowerCase();
				System.out.println("Username: " + username);
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(passwordField.getPassword());
				String password = stringBuilder.toString();
				System.out.println("Password: " + password);
				System.out.println("------------------------");
				// Iniciar sesion
				if (usuario.equals(username)) {
					JOptionPane.showMessageDialog(null, "Este usuario lo tiene tu rival, mel�n");
				} else {
					if (db.userExists(username)) {
						if (db.login(username, password)) {
							System.out.println("Logged in!");
							// Pasar a la ventana de opciones
							frame.setVisible(false);
							VentanaJuego.empezar(usuario, username);
						} else {
							JOptionPane.showMessageDialog(null, "Contrase�a incorrecta");
						}
						// Registrar
					} else if (username.length() > 0 && username.length() <= 30 && password.length() > 0
							&& password.length() <= 30) {
						db.register(username, password);
						// Pasar a la ventana de opciones
						JOptionPane.showMessageDialog(null, "Nuevo usuario registrado");
						frame.setVisible(false);
						VentanaJuego.empezar(usuario, username);
					} else {
						JOptionPane.showMessageDialog(null, "Formato incorrecto");
					}
				}
			}
		});
		panel.add(loginButton);
	}
}
