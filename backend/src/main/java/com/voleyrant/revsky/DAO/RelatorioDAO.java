package backend.src.main.java.com.voleyrant.revsky.DAO;

import backend.src.main.java.com.voleyrant.revsky.enumeracoes.StatusPedido;
import backend.src.main.java.com.voleyrant.revsky.model.Pedido;
import backend.src.main.java.com.voleyrant.revsky.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RelatorioDAO {
  public void gerarRelatorio(){
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    try {
      connection = ConnectionUtil.iniciarConexao();
      String query = "SELECT \n" +
              "    (SELECT COUNT(*) FROM clientes) AS TotalClientes,\n" +
              "    (SELECT COUNT(*) FROM produtos) AS TotalProdutos,\n" +
              "CURDATE() AS ReportDate;\n"; // Adiciona a data de geração do relatório

      statement = ConnectionUtil.prepararQuery(connection, query);
      resultSet = statement.executeQuery();
      // Iterar sobre os resultados e exibir cada linha
      while (resultSet.next()) {
        int totalClientes = resultSet.getInt("TotalClientes");
        int totalProdutos = resultSet.getInt("TotalProdutos");
        String reportDate = resultSet.getString("ReportDate");

        System.out.println("Total de Clientes: " + totalClientes);
        System.out.println("Total de Produtos: " + totalProdutos);
        System.out.println("Data de Geração do Relatório: " + reportDate);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      ConnectionUtil.fecharConexao(connection, statement);
    }
  }

  public void obterListaDeClientes() {
    System.out.println("obter lista de clientes");
  }

  public List<String> obterPedidosDoMes() {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    List<String> listaPedidos = new ArrayList<>();

    try {
      connection = ConnectionUtil.iniciarConexao();
      String query = "SELECT\n" +
        "p.id_pedido AS 'Id da Venda', \n" +
          "c.nome AS 'Nome do Cliente', \n" +
          "CONCAT('R$ ', FORMAT(p.valor_total, 2)) AS 'Valor do Pedido', \n" +
          "CONCAT('R$ ', FORMAT(p.desconto, 2)) AS 'Desconto', \n" +
          "CONCAT('R$ ', FORMAT(p.valor_total - p.desconto, 2)) AS 'Valor total da compra', \n" +
                "p.status AS 'Status' \n" +
        "FROM \n" +
        "pedidos p \n" +
        "JOIN \n" +
        "clientes c ON p.id_cliente_pedido = c.id_cliente; \n";

      statement = ConnectionUtil.prepararQuery(connection, query);
      resultSet = statement.executeQuery();

      // Iterar sobre os resultados, armazenar cada linha
      while (resultSet.next()) {
        String idVenda = resultSet.getString("Id da Venda");
        String nomeCliente = resultSet.getString("Nome do Cliente");
        String valorPedido = resultSet.getString("Valor do Pedido");
        String desconto = resultSet.getString("Desconto");
        String valorTotalCompra = resultSet.getString("Valor total da compra");
        String status = resultSet.getString("Status");

        String pedidoString = idVenda + ", " + nomeCliente + ", " + valorPedido + ", " +
                desconto + ", " + valorTotalCompra + ", " + status;

        listaPedidos.add(pedidoString);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      ConnectionUtil.fecharConexao(connection, statement);
    }
    return listaPedidos;
  }
  public void obterDescricaoProd() {}
}
