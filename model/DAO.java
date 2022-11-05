package model;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DAO {
	private Connection conexao;
	synchronized Connection getConnection() {
		if(conexao == null) {
			try {
				conexao = DriverManager.getConnection("jdbc:mysql://localhost/teste","root","escola123");
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return conexao;
		} else return conexao;
	}
}
