package backend.src.main.java.com.voleyrant.revsky.DAO;

import backend.src.main.java.com.voleyrant.revsky.enumeracoes.FormaPagamento;
import backend.src.main.java.com.voleyrant.revsky.enumeracoes.StatusPedido;
import backend.src.main.java.com.voleyrant.revsky.model.Pedido;
import backend.src.main.java.com.voleyrant.revsky.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    // Método para criar um novo pedido no banco de dados
    public void criarPedido(Pedido pedido) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionUtil.iniciarConexao();
            String query = "INSERT INTO pedidos " +
                           "(id_cliente_pedido, id_vendedor_pedido, valor_total, desconto, forma_pagamento, status) " +
                            "VALUES " + "(?, ?, ?, ?, ?, ?)";
            statement = ConnectionUtil.prepararQuery(connection, query);

            // Setar os parâmetros do PreparedStatement com os valores do Pedido
            statement.setInt(1, pedido.getIdClientePedido());
            statement.setInt(2, pedido.getIdVendedorPedido());
            statement.setDouble(3, pedido.getValorTotal());
            statement.setDouble(4, pedido.getDesconto());
            statement.setString(5, pedido.getFormaPagamento().toString());
            statement.setString(6, pedido.getStatusPedido().toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.fecharConexao(connection, statement);
        }
    }
    // Método para listar todos os pedidos do banco de dados
    public List<Pedido> listarPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionUtil.iniciarConexao();
            String query = "SELECT * FROM pedidos";
            statement = ConnectionUtil.prepararQuery(connection, query);
            resultSet = statement.executeQuery();
            // Iterando sobre o ResultSet para extrair os dados de cada pedido
            while (resultSet.next()) {
                int idPedido = resultSet.getInt("id_pedido");
                int idClientePedido = resultSet.getInt("id_cliente_pedido");
                int idVendedorPedido = resultSet.getInt("id_vendedor_pedido");
                double valorTotal = resultSet.getDouble("valor_total");
                double desconto = resultSet.getDouble("desconto");
                FormaPagamento formaPagamento = FormaPagamento.valueOf(resultSet.getString("forma_pagamento"));
                StatusPedido status = StatusPedido.valueOf(resultSet.getString("status"));

                Pedido pedido = new Pedido(idPedido, idClientePedido, idVendedorPedido, null, valorTotal, desconto, formaPagamento, status);
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.fecharConexao(connection, statement, resultSet);
        }

        return pedidos;
    }
    public List<Pedido> listarPedidosPorClienteId(int idCliente) {
        List<Pedido> pedidos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionUtil.iniciarConexao();
            String query = "SELECT * FROM pedido WHERE id_clientePedido = ?";
            statement = ConnectionUtil.prepararQuery(connection, query);
            statement.setInt(1, idCliente);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idPedido = resultSet.getInt("id_pedido");
                int idVendedorPedido = resultSet.getInt("id_vendedor_pedido");
                double valorTotal = resultSet.getDouble("valor_total");
                double desconto = resultSet.getDouble("desconto");
                FormaPagamento formaPagamento = FormaPagamento.valueOf(resultSet.getString("forma_pagamento"));
                StatusPedido status = StatusPedido.valueOf(resultSet.getString("status"));

                Pedido pedido = new Pedido(idPedido, idCliente, idVendedorPedido, null, valorTotal, desconto, formaPagamento, status);
                pedidos.add(pedido);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.fecharConexao(connection, statement, resultSet);
        }

        return pedidos;
    }
    // Método para atualizar um pedido existente no banco de dados
    public void atualizarPedido(Pedido pedido) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionUtil.iniciarConexao();
            String query = "UPDATE pedidos SET id_cliente_pedido = ?, id_vendedor_pedido = ?, valor_total = ?, desconto = ?, forma_pagamento = ?, status = ? WHERE id_pedido = ?";
            statement = ConnectionUtil.prepararQuery(connection, query);
            // Setando os parâmetros do PreparedStatement com os valores do pedido
            statement.setInt(1, pedido.getIdClientePedido());
            statement.setInt(2, pedido.getIdVendedorPedido());
            statement.setDouble(3, pedido.getValorTotal());
            statement.setDouble(4, pedido.getDesconto());
            statement.setString(5, pedido.getFormaPagamento().toString());
            statement.setString(6, pedido.getStatusPedido().toString());
            statement.setInt(7, pedido.getIdPedido());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.fecharConexao(connection, statement);
        }
    }
    // Método para atualizar o status do pedido com base no ID do pedido e ID do vendedor
    public void atualizarStatusPedido(int idPedido, int idVendedor, StatusPedido novoStatus) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionUtil.iniciarConexao();
            String query = "UPDATE pedidos SET status = ? WHERE id_pedido = ? AND id_vendedor_pedido = ?";
            statement = ConnectionUtil.prepararQuery(connection, query);

            // Setar os parâmetros do PreparedStatement com os valores do pedido
            statement.setString(1, novoStatus.toString());
            statement.setInt(2, idPedido);
            statement.setInt(3, idVendedor);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.fecharConexao(connection, statement);
        }
    }

    // Método para cancelar um pedido do banco de dados pelo seu ID
    public void cancelarPedido(int idPedido) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionUtil.iniciarConexao();
            String query = "DELETE FROM pedidos WHERE id_pedido = ?";
            statement = ConnectionUtil.prepararQuery(connection, query);
            statement.setInt(1, idPedido);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.fecharConexao(connection, statement);
        }
    }
    //Método para buscar um pedido específico pelo seu id
    public Pedido buscarPedidoPorId(int idPedido) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Pedido pedido = null;

        try {
            connection = ConnectionUtil.iniciarConexao();
            String query = "SELECT * FROM pedidos WHERE id_pedido = ?";
            statement = ConnectionUtil.prepararQuery(connection, query);
            statement.setInt(1, idPedido);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int idClientePedido = resultSet.getInt("id_cliente_pedido");
                int idVendedorPedido = resultSet.getInt("id_vendedor_pedido");
                double valorTotal = resultSet.getDouble("valor_total");
                double desconto = resultSet.getDouble("desconto");
                FormaPagamento formaPagamento = FormaPagamento.valueOf(resultSet.getString("forma_pagamento"));
                StatusPedido status = StatusPedido.valueOf(resultSet.getString("status"));

                pedido = new Pedido(idPedido, idClientePedido, idVendedorPedido, null, valorTotal, desconto, formaPagamento, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.fecharConexao(connection, statement, resultSet);
        }

        return pedido;
    }
    // Método auxiliar para extrair um objeto Pedido de um ResultSet
    private Pedido extrairPedidoDoResultSet(ResultSet resultSet) throws SQLException {
        int idPedido = resultSet.getInt("id_pedido");
        int idClientePedido = resultSet.getInt("id_cliente_pedido");
        int idVendedorPedido = resultSet.getInt("id_vendedor_pedido");
        double valorTotal = resultSet.getDouble("valor_total");
        double desconto = resultSet.getDouble("desconto");
        FormaPagamento formaPagamento = FormaPagamento.valueOf(resultSet.getString("forma_pagamento"));
        StatusPedido status = StatusPedido.valueOf(resultSet.getString("status")
        );

        return new Pedido(idPedido, idClientePedido, idVendedorPedido, null, valorTotal, desconto, formaPagamento, status);
    }
}

