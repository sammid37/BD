package src.main.java.com.voleyrant.crud.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

  public static Connection createConnectionToMySQL() throws SQLException {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver"); // carregando a classe para a JVM
      return DriverManager.getConnection(
        ConnectionConfig.URL,
        ConnectionConfig.USUARIO,
        ConnectionConfig.SENHA
      );
    } catch (ClassNotFoundException e) {
      throw new SQLException("Driver JDBC não encontrado", e);
    }
  }

  public static void main(String[] args) throws  SQLException{
    Connection connection = createConnectionToMySQL(); // Recupera conexão com o BD

    if(connection != null) {
      System.out.println("Conexão obtida com sucesso.");
      connection.close(); // garante que não haja conexões além da necessária
    }
  }
}
