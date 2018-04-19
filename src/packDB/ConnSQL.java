package packDB;

import java.util.*;
import java.sql.*;

public class ConnSQL {

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
	
	public Boolean userExist(String pUsername) {
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
	
	public void register(String pUsername, String pPassword){
		String query = "INSERT INTO sql7233144.Usuario VALUES ('" + pUsername + "', '" + pPassword +  "');";
		
		try {
			Statement st = this.conn.createStatement();
			st.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		System.out.println("Nuevo usuario "+pUsername+" introducido en la BD");
	}
}