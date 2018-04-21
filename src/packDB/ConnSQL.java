package packDB;

import java.sql.*;

public class ConnSQL {

	// Don't touch anything or our ninja frog will annihilate you
	private String url = "jdbc:mysql://sql7.freesqldatabase.com:3306/";
	private String user = "sql7233144";
	private String passwd = "w3sZdbyXe2";
	private String driver = "com.mysql.jdbc.Driver";

	private Connection conn;

	public ConnSQL() {
		try {
			Class.forName(this.driver).newInstance();
			this.conn = DriverManager.getConnection(this.url, this.user, this.passwd);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

	public Boolean login(String pUsername, String pPassword) {
		String query = "SELECT * FROM sql7233144.Usuario WHERE username='" + pUsername + "';";
		String password = null;

		try {
			Statement st = this.conn.createStatement();
			ResultSet res = st.executeQuery(query);
			while (res.next()) {
				password = res.getString("password");
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

		System.out.println("password introducida: " + pPassword + ", password de la DB: " + password);

		if (pPassword.equals(password)) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean userExists(String pUsername) {
		String query = "SELECT username FROM sql7233144.Usuario WHERE username='" + pUsername + "';";
		String username = null;

		try {
			Statement st = this.conn.createStatement();
			ResultSet res = st.executeQuery(query);
			while (res.next()) {
				username = res.getString("username");
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

		if (pUsername.equals(username)) {
			System.out.println("Ya existe este usuario en la BD");
			return true;
		} else {
			System.out.println("No existe este usuario en la BD");
			return false;
		}

	}

	public void register(String pUsername, String pPassword) {
		String query = "INSERT INTO sql7233144.Usuario VALUES ('" + pUsername + "', '" + pPassword + "');";

		try {
			Statement st = this.conn.createStatement();
			st.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		System.out.println("Nuevo usuario " + pUsername + " introducido en la BD");
	}

	public String[][] getRanking() {
		String query = "SELECT * FROM sql7233144.Puntuacion ORDER BY puntos LIMIT 10;";
		String[][] resultado = new String[3][10];

		try {
			Statement st = this.conn.createStatement();
			ResultSet res = st.executeQuery(query);
			int i = 0;
			while (res.next()) {
				resultado[0][i] = res.getString("jugador");
				resultado[1][i] = res.getString("rival");
				resultado[2][i] = res.getString("puntos");
				i++;
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return resultado;
	}
	
	public void addPuntuacion(String pUsername, String pRival, int pPuntos) {
		String query = "SELECT puntos FROM sql7233144.Puntuacion WHERE jugador='" + pUsername + "';";
		String puntos = null;

		try {
			Statement st = this.conn.createStatement();
			ResultSet res = st.executeQuery(query);
			while (res.next()) {
				puntos = res.getString("puntos");
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

		if (puntos != null) {
			if (pPuntos >= Integer.parseInt(puntos)) {
				query = "INSERT INTO sql7233144.Puntuacion VALUES ('" + pUsername + "', '" + pRival + "', '" + pPuntos + "');";

				try {
					Statement st = this.conn.createStatement();
					st.executeUpdate(query);
				} catch (Exception e) {
					System.out.println("Exception: " + e.getMessage());
				}
			}
		} else {
			query = "INSERT INTO sql7233144.Puntuacion VALUES ('" + pUsername + "', '" + pRival + "', '" + pPuntos + "');";

			try {
				Statement st = this.conn.createStatement();
				st.executeUpdate(query);
			} catch (Exception e) {
				System.out.println("Exception: " + e.getMessage());
			}
		}
	}
}
