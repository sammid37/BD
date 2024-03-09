package backend.src.main.java.com.voleyrant.revsky.DAO;

import java.sql.*;

import backend.src.main.java.com.voleyrant.revsky.model.Cliente;
import backend.src.main.java.com.voleyrant.revsky.model.Vendedor;
import backend.src.main.java.com.voleyrant.revsky.util.ConnectionFactory;
import backend.src.main.java.com.voleyrant.revsky.util.ConnectionUtil;

public class VendedorDAO {
  public void criarVendedor(Vendedor vendedor) {
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
  public Vendedor buscarPorEmail(String email) {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    Vendedor vendedor = null;

    try {
      connection = ConnectionUtil.iniciarConexao();
      String query = "SELECT * FROM vendedores WHERE email = ?";

      statement = ConnectionUtil.prepararQuery(connection, query);

      // Setar os parâmetros do PreparedStatement com os valores do cliente
      statement.setString(1, email);
      resultSet = statement.executeQuery();

      if (resultSet.next()) {
        vendedor = extrairVendedorDoResultSet(resultSet);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      ConnectionUtil.fecharConexao(connection, statement);
    }
    return vendedor;
  }

  private Vendedor extrairVendedorDoResultSet(ResultSet resultSet) throws SQLException {
    // Extrair dados do ResultSet e criar um objeto Vendedor
    return new Vendedor(
            resultSet.getString("nome"),
            resultSet.getDate("data_nasc"),
            resultSet.getString("tel"),
            resultSet.getString("email"),
            resultSet.getString("senha")
    );
  }

}
