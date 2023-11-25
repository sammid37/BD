package src.main.java.com.voleyrant.crud.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {

  public static Connection obterConexao() throws SQLException {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      return DriverManager.getConnection(
        ConfigDB.URL,
        ConfigDB.USUARIO,
        ConfigDB.SENHA
      );
    } catch (ClassNotFoundException e) {
      throw new SQLException("Driver JDBC não encontrado", e);
    }
  }

  public static void fecharConexao(Connection conexao) {
    if (conexao != null) {
      try {
        conexao.close();
      } catch (SQLException e) {
        e.printStackTrace(); // Tratar a exceção de fechamento, se necessário
      }
    }
  }
}
