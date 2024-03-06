package backend.src.main.java.com.voleyrant.revsky.model;

import backend.src.main.java.com.voleyrant.revsky.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

public class Vendedor extends User {
  private int idVendedor;
  // private Loja loja;

  // Construtor
  public Vendedor(String nome, Date dataNascimento, String tel, String email, String senha) {
    super(nome, dataNascimento, tel, email, senha);
    // this.loja = loja;
  }

  public void criarVendedor(Vendedor vendedor) {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = ConnectionUtil.iniciarConexao();
      String query = "INSERT INTO vendedores " +
              "(nome, data_nasc, tel, email, senha) " +
              "VALUES " +
              "(?, ?, ?, ?, ?)";

      statement = ConnectionUtil.prepararQuery(connection, query);

      // Setar os parâmetros do PreparedStatement com os valores do cliente
      statement.setString(1, vendedor.getNome());
      statement.setDate(2, new java.sql.Date(vendedor.getDataNasc().getTime()));
      statement.setString(3, vendedor.getTel());
      statement.setString(4, vendedor.getEmail());
      statement.setString(5, vendedor.getSenha());

      statement.execute();
    } catch (Exception e) {
      e.printStackTrace(); // Tratar a exceção, se necessário
    } finally {
      ConnectionUtil.fecharConexao(connection, statement);
    }
  }

  // get e set

  public int getIdVendedor() { return idVendedor; }
  public void setIdVendedor(int idVendedor) { this.idVendedor = idVendedor; }  

  // public Loja getVinculoLoja() { return loja; }
  // public void setVinculoLoja(Loja loja) { this.loja = loja; }
}
