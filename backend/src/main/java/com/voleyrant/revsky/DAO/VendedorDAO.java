package backend.src.main.java.com.voleyrant.revsky.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import backend.src.main.java.com.voleyrant.revsky.model.Vendedor;
import backend.src.main.java.com.voleyrant.revsky.util.ConnectionFactory;

public class VendedorDAO {
  public void salvarVendedor(Vendedor vendedor) {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = ConnectionFactory.createConnectionToMySQL();
      String query = "INSERT INTO vendedores " +
              "(nome, data_nasc, tel, email, senha) " +
              "VALUES " +
              "( ?, ?, ?, ?, ?)";
      statement = connection.prepareStatement(query);

      // Setar os parâmetros do PreparedStatement com os valores do vendedor
      statement.setString(1, vendedor.getNome());
      statement.setDate(2, new Date(vendedor.getDataNasc().getTime()));
      statement.setString(3, vendedor.getTel());
      statement.setString(4, vendedor.getEmail());
      statement.setString(5, vendedor.getSenha());
      // statement.setInt(6, vendedor.getVinculoLoja());


      statement.executeUpdate();
    } catch (SQLException e) {
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
