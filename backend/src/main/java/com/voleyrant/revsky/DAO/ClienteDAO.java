package backend.src.main.java.com.voleyrant.revsky.DAO;

import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;

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

}
