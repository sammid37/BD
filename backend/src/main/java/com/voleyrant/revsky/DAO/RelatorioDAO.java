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
  public String obterResumo(){
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    String resumoString = "";
    try {
      connection = ConnectionUtil.iniciarConexao();
      String query = "SELECT" +
          "\nCURDATE() AS DataGeracaoRelatorio," +
          "\n(SELECT COUNT(*) FROM clientes) AS 'Nº de Clientes Cadastrados'," +
          "\n(SELECT COUNT(*) FROM pedidos WHERE pedidos.status = 'FINALIZADO') AS 'Nº de Pedidos (FINALIZADOS)'," +
          "\nCONCAT('R$ ', FORMAT((SELECT SUM(valor_total - desconto) FROM pedidos WHERE pedidos.status = 'FINALIZADO'), 2)) AS 'Ganho em Pedidos'," +
          "\n(SELECT COUNT(*) FROM produtos) AS 'Nº de Produtos Cadastrados'," +
          "\n(SELECT COUNT(*) FROM produtos WHERE estoque > 0) AS 'Nº Total de Produtos em Estoque';";

      statement = ConnectionUtil.prepararQuery(connection, query);
      resultSet = statement.executeQuery();

      while (resultSet.next()) {
        String reportDate = resultSet.getString("DataGeracaoRelatorio");
        String totalClientes = resultSet.getString("Nº de Clientes Cadastrados");
        String totalPedidosFinalizado = resultSet.getString("Nº de Pedidos (FINALIZADOS)");
        String valorTotalPedidosFinalizados = resultSet.getString("Ganho em Pedidos");
        String totalProdutos = resultSet.getString("Nº de Produtos Cadastrados");
        String totalProdutosEStoque = resultSet.getString("Nº Total de Produtos em Estoque");

        resumoString = "Data da geração do relatório " + reportDate +
                "\n" + "Nº de Clientes Cadastrados: " + totalClientes +
                "\n" + "Nº de Pedidos (FINALIZADOS): " + totalPedidosFinalizado +
                ", " + "Ganho em Pedidos: " + valorTotalPedidosFinalizados +
                "\n" + "Nº de Produtos Cadastrados: "+ totalProdutos + ", Nº Total de Produtos em Estoque: " + totalProdutosEStoque;
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      ConnectionUtil.fecharConexao(connection, statement);
    }
    return resumoString;
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
}
