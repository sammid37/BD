package backend.src.main.java.com.voleyrant.revsky.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import backend.src.main.java.com.voleyrant.revsky.model.Pedido;
import backend.src.main.java.com.voleyrant.revsky.util.ConnectionUtil;

public class PedidoDAO {

    public void fazerPedido(Pedido pedido){
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionUtil.iniciarConexao();
            String query =  "INSERT INTO pedidos "+
                            "(id_cliente, id_vendedor, produtos, valor_total, desconto, forma_pagamento, status)"+
                            "VALUES " +
                            "(?, ?, ?, ?, ?, ?, ?)";

            statement = ConnectionUtil.prepararQuery(connection, query);

            statement.setInt(1, pedido.getIdClientePedido());
            statement.setInt(2, pedido.getIdVendedorPedido());
            statement.setString(3, pedido.getItensPedido().toString()); //
            statement.setDouble(4, pedido.getValorTotal());
            statement.setDouble(5, pedido.getDesconto());
            statement.setString(6, pedido.getFormaPagamento().name());
            statement.setString(7, pedido.getStatusPedido().name());

            statement.execute();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.fecharConexao(connection, statement);
        }
    }
    public Pedido lerPedidoPorId(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Pedido pedido = null;

        try {
            connection = ConnectionUtil.iniciarConexao();

            String query = "SELECT * FROM pedidos WHERE id_pedido = ?";

            statement = ConnectionUtil.prepararQuery(connection, query);

            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if(resultSet.next()){
                pedido = extrairPedidoDoResultSet(resultSet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.fecharConexao(connection, statement);
        }
        return pedido;
    }
    
    public void editarPedidoPorId(int id, Pedido pedido){
        Connection connection = null;
        PreparedStatement statement = null; 

        try {
            connection = ConnectionUtil.iniciarConexao();
            String query = "UPDATE pedidos SET id_pedido = ?, id_cliente = ?, id_vendedor = ?, produtos = ?, valor_total = ?, desconto = ?, forma_pagamento = ?, status = ? WHERE id_pedido = ?";
            
            statement = ConnectionUtil.prepararQuery(connection, query);

            statement.setInt(1, pedido.getIdPedido());
            statement.setInt(2, pedido.getIdClientePedido());
            statement.setInt(2, pedido.getIdVendedorPedido());
            statement.setString(4, pedido.getItensPedido().toString()); //
            statement.setDouble(5, pedido.getValorTotal());
            statement.setDouble(6, pedido.getDesconto());
            statement.setString(7, pedido.getFormaPagamento().name());
            statement.setString(8, pedido.getStatusPedido().name());


            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.fecharConexao(connection, statement);
        }
    }
    public void removerPedidoPorID(int id){
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionUtil.iniciarConexao();
            String query = "DELETE FROM pedidos WHERE id_pedido = ?";

            statement = ConnectionUtil.prepararQuery(connection, query);
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.fecharConexao(connection, statement);
        }
    }
    private Pedido extrairPedidoDoResultSet(ResultSet resultSet) throws SQLException{
        return new Pedido(resultSet.getInt("id_pedido"), 
        resultSet.getInt("id_cliente"), 
        resultSet.getInt("id_vendedor"), 
        null, 
        resultSet.getDouble("valor_total"), 
        resultSet.getDouble("desconto"), 
        null, 
        null);
    }
}
