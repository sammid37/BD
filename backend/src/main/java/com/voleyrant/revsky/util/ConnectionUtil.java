package backend.src.main.java.com.voleyrant.revsky.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class ConnectionUtil {
  public static Connection iniciarConexao() throws SQLException {
    Connection connection = ConnectionFactory.createConnectionToMySQL();
    return connection;
  }

  public static PreparedStatement prepararQuery(Connection connection, String query) throws SQLException {
    return connection.prepareStatement(query);
  }

  public static void fecharConexao(Connection connection, PreparedStatement statement) {
    try {
      if (statement != null) { statement.close(); }
      if (connection != null) { connection.close(); }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
  

