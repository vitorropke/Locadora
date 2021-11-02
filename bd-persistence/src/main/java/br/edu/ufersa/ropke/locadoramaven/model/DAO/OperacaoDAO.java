package br.edu.ufersa.ropke.locadoramaven.model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class OperacaoDAO<VO> implements OperacaoInterDAO<VO> {
	private static Connection conexao = null;
	private static final String url = "jdbc:postgresql://localhost:5433/locadora_teste";
	private static final String usuario = "postgres";
	private static final String senha = "1";

	public static Connection getConnection() {
		if (conexao == null) {
			try {
				conexao = DriverManager.getConnection(url, usuario, senha);
				System.out.println("Conexao estabelecida");
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

	public abstract void cadastrar(VO entidade) throws SQLException;

	public abstract void alterar(VO entidade) throws SQLException;

	public abstract void deletar(VO entidade) throws SQLException;

	public abstract ResultSet pesquisar(VO entidade) throws SQLException;

	public abstract ResultSet listar() throws SQLException;
}
