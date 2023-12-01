package backend.src.main.java.com.voleyrant.revsky.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import backend.src.main.java.com.voleyrant.revsky.model.Cliente;
import backend.src.main.java.com.voleyrant.revsky.util.ConnectionFactory;

public class ClienteDAO {
  public void salvarCliente(Cliente cliente) {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = ConnectionFactory.createConnectionToMySQL();
      String query = "INSERT INTO clientes " +
              "(nome, data_nasc, tel, email, senha, time, one_piece) " +
              "VALUES " +
              "(?, ?, ?, ?, ?, ?, ?)";
      statement = connection.prepareStatement(query);

      // Setar os parâmetros do PreparedStatement com os valores do cliente
      statement.setString(1, cliente.getNome());
      statement.setDate(2, new Date(cliente.getDataNasc().getTime()));
      statement.setString(3, cliente.getTel());
      statement.setString(4, cliente.getEmail());
      statement.setString(5, cliente.getSenha());
      statement.setString(6, cliente.getTime());
      statement.setBoolean(7, cliente.getFlagOP());

      statement.execute();
    } catch (Exception e) {
      e.printStackTrace(); // Tratar a exceção, se necessário
    } finally {
      try {
        if (statement != null) {
          statement.close();
        }
        if (connection != null) {
          connection.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
