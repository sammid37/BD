package src.main.java.com.voleyrant.crud.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import src.main.java.com.voleyrant.crud.model.Vendedor;
import src.main.java.com.voleyrant.crud.util.Conn;

public class VendedorDAO {
  public void salvarVendedor(Vendedor vendedor) {
    Connection conexao = null;
    PreparedStatement statement = null;

    try {
      conexao = Conn.obterConexao();
      String query = "INSERT INTO vendedores (id, nome, data_nasc, tel, email, senha, id_vendedor, loja_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
      statement = conexao.prepareStatement(query);

      // Setar os parâmetros do PreparedStatement com os valores do vendedor
      statement.setInt(1, vendedor.getId());
      statement.setString(2, vendedor.getNome());
      // ... adicionar os demais parâmetros conforme necessário

      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace(); // Tratar a exceção, se necessário
    } finally {
      Conn.fecharConexao(conexao);
    }
  }
}
