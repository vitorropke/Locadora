package br.edu.ufersa.ropke.locadoramaven.model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ConexaoDAO {
	private static Connection conexao = null;
	private static final String url = "jdbc:postgresql://localhost:5432/locadora_teste";
	private static final String usuario = "postgres";
	private static final String senha = "1";

	public static Connection getConnection() {
		if (conexao == null) {
			try {
				conexao = DriverManager.getConnection(url, usuario, senha);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return conexao;
		} else {
			return conexao;
		}
	}

	public static void closeConnection() {
		if (conexao != null) {
			try {
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
