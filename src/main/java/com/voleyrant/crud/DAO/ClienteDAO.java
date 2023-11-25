package src.main.java.com.voleyrant.crud.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import src.main.java.com.voleyrant.crud.model.Cliente;
import src.main.java.com.voleyrant.crud.util.Conn;

public class ClienteDAO {
  public void salvarCliente(Cliente cliente) {
    Connection conexao = null;
    PreparedStatement statement = null;

    try {
      conexao = Conn.obterConexao();
      String query = "INSERT INTO clientes (id, nome, data_nasc, tel, email, senha, id_cliente, time, one_piece, uf, cidade) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
      statement = conexao.prepareStatement(query);

      // Setar os parâmetros do PreparedStatement com os valores do cliente
      statement.setInt(1, cliente.getId());
      statement.setString(2, cliente.getNome());
      // ... adicionar os demais parâmetros conforme necessário

      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace(); // Tratar a exceção, se necessário
    } finally {
      Conn.fecharConexao(conexao);
    }
  }
}
