package backend.src.main.java.com.voleyrant.revsky.DAO;

import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import backend.src.main.java.com.voleyrant.revsky.model.Cliente;
import backend.src.main.java.com.voleyrant.revsky.util.ConnectionUtil;

public class ClienteDAO {
  // CREATE
  public void criarCliente(Cliente cliente) {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = ConnectionUtil.iniciarConexao();
      String query = "INSERT INTO clientes " +
                     "(nome, data_nasc, tel, email, senha, time, one_piece, cidade, estado) " +
                     "VALUES " +
                     "(?, ?, ?, ?, ?, ?, ?, ?, ?)";

      statement = ConnectionUtil.prepararQuery(connection, query);

      // Setar os parâmetros do PreparedStatement com os valores do cliente
      statement.setString(1, cliente.getNome());
      statement.setDate(2, new Date(cliente.getDataNasc().getTime()));
      statement.setString(3, cliente.getTel());
      statement.setString(4, cliente.getEmail());
      statement.setString(5, cliente.getSenha());
      statement.setString(6, cliente.getTime());
      statement.setBoolean(7, cliente.getFlagOP());
      statement.setString(8, cliente.getCidade());
      statement.setString(9, cliente.getEstado());

      statement.execute();
    } catch (Exception e) {
      e.printStackTrace(); // Tratar a exceção, se necessário
    } finally {
      ConnectionUtil.fecharConexao(connection, statement);
    }
  }

  // READ
  public Cliente lerClientePorId(int id) {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    Cliente cliente = null;
    
    try {
      connection = ConnectionUtil.iniciarConexao();
      String query = "SELECT * FROM clientes WHERE id_cliente = ?";

      statement = ConnectionUtil.prepararQuery(connection, query);
      
      // Setar os parâmetros do PreparedStatement com os valores do cliente
      statement.setInt(1, id);
      resultSet = statement.executeQuery();
      
      if (resultSet.next()) {
        System.out.println("Oi");
        cliente = extrairClienteDoResultSet(resultSet);
        System.out.println("haha");
      }

    } catch (Exception e) {
      e.printStackTrace(); // Tratar a exceção, se necessário
    } finally {
      ConnectionUtil.fecharConexao(connection, statement);
    }

    return cliente;
  }

  // UPDATE
  public void editarClientePorId(int id, Cliente cliente) {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = ConnectionUtil.iniciarConexao();
      String query = "UPDATE clientes SET nome = ?, data_nasc = ?, tel = ?, email = ?, senha = ?, time = ?, one_piece = ?, cidade = ?, estado = ? WHERE id_cliente = ?";
            
      statement = ConnectionUtil.prepararQuery(connection, query);

      // Setar os parâmetros do PreparedStatement com os valores do cliente
      statement.setString(1, cliente.getNome());
      statement.setDate(2, new Date(cliente.getDataNasc().getTime()));
      statement.setString(3, cliente.getTel());
      statement.setString(4, cliente.getEmail());
      statement.setString(5, cliente.getSenha());
      statement.setString(6, cliente.getTime());
      statement.setBoolean(7, cliente.getFlagOP());
      statement.setString(8, cliente.getCidade());
      statement.setString(9, cliente.getEstado());
      statement.setInt(10, id);

      statement.execute();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      ConnectionUtil.fecharConexao(connection, statement);
    }
  }

  public void atualizarEmail(String novoEmail, int clientId) {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = ConnectionUtil.iniciarConexao();

      String query = "UPDATE clientes SET  email = ? WHERE id_cliente = ?";

      statement = ConnectionUtil.prepararQuery(connection, query);
      statement.setString(1, novoEmail);
      statement.setInt(2, clientId);
      statement.execute();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      ConnectionUtil.fecharConexao(connection, statement);
    }
  }

  public void atualizarTelefone(String novoTelefone, int clientId) {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = ConnectionUtil.iniciarConexao();

      String query = "UPDATE clientes SET tel = ? WHERE id_cliente = ?";

      statement = ConnectionUtil.prepararQuery(connection, query);
      statement.setString(1, novoTelefone);
      statement.setInt(2, clientId);
      statement.execute();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      ConnectionUtil.fecharConexao(connection, statement);
    }
  }

  public void atualizarSenha(String novaSenha, int clientId) {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = ConnectionUtil.iniciarConexao();

      String query = "UPDATE clientes SET senha = ? WHERE id_cliente = ?";

      statement = ConnectionUtil.prepararQuery(connection, query);
      statement.setString(1, novaSenha);
      statement.setInt(2, clientId);
      statement.execute();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      ConnectionUtil.fecharConexao(connection, statement);
    }
  }

  public void atualizarCidadeEstado(String novaCidade, String novoEstado, int clientId) {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = ConnectionUtil.iniciarConexao();

      String query = "UPDATE clientes SET cidade = ?, estado = ? WHERE id_cliente = ?";

      statement = ConnectionUtil.prepararQuery(connection, query);
      statement.setString(1, novaCidade);
      statement.setString(2, novoEstado);
      statement.setInt(3, clientId);
      statement.execute();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      ConnectionUtil.fecharConexao(connection, statement);
    }
  }

  // DELETE
  public void removerClientePorId(int id) {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = ConnectionUtil.iniciarConexao();
      String query = "DELETE FROM clientes WHERE id_cliente = ?";

      statement = ConnectionUtil.prepararQuery(connection, query);
      statement.setInt(1, id);

      statement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      ConnectionUtil.fecharConexao(connection, statement);
    }
  }

  public Cliente buscarPorEmail(String email) {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    Cliente cliente = null;

    try {
      connection = ConnectionUtil.iniciarConexao();
      String query = "SELECT * FROM clientes WHERE email = ?";

      statement = ConnectionUtil.prepararQuery(connection, query);

      // Setar os parâmetros do PreparedStatement com os valores do cliente
      statement.setString(1, email);
      resultSet = statement.executeQuery();

      if (resultSet.next()) {
        cliente = extrairClienteDoResultSet(resultSet);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      ConnectionUtil.fecharConexao(connection, statement);
    }
    return cliente;
  }

  // utils
  private Cliente extrairClienteDoResultSet(ResultSet resultSet) throws SQLException {
    // Extrair dados do ResultSet e criar um objeto Cliente
    return new Cliente(
        resultSet.getInt("id_cliente"),
        resultSet.getString("nome"),
        resultSet.getDate("data_nasc"),
        resultSet.getString("tel"),
        resultSet.getString("email"),
        resultSet.getString("senha"),
        resultSet.getString("time"),
        resultSet.getBoolean("one_piece"),
        resultSet.getString("estado"),
        resultSet.getString("cidade")
    );
  }
}
