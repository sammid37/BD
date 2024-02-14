package backend.src.main.java.com.voleyrant.revsky.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import backend.src.main.java.com.voleyrant.revsky.model.Produto;
import backend.src.main.java.com.voleyrant.revsky.util.ConnectionUtil;

public class ProdutoDAO {
    //CREATE
    public void criarProduto(Produto produto){
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionUtil.iniciarConexao();
            String query =  "INSERT INTO produtos " +
                            "(tipo, titulo, descricao, estoque, preco)"+
                            "VALUES " +
                            "(?, ?, ?, ?, ?)";
            statement = ConnectionUtil.prepararQuery(connection, query);
            
            statement.setString(1, produto.getTipoProduto().name()); //
            statement.setString(2, produto.getTitulo());
            statement.setString(3, produto.getDescricao());
            statement.setInt(4, produto.getEstoque());
            statement.setDouble(5, produto.getPreco());

            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();;

        } finally {
            ConnectionUtil.fecharConexao(connection, statement);
        }
    }

    public Produto lerProdutoPorId(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Produto produto = null;

        try{
            connection = ConnectionUtil.iniciarConexao();
            String query = "SELECT * FROM produtos WHERE id_produto = ?";

            statement = ConnectionUtil.prepararQuery(connection, query);

            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if(resultSet.next()){
                produto = extrairProdutoDoResultSet(resultSet);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionUtil.fecharConexao(connection, statement);
        }
        return produto;
    }

    public void editarProdutoPorId(int id, Produto produto){
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionUtil.iniciarConexao();
            String query = "UPDATE produtos SET idProduto = ?, tipoProduto = ?, titulo = ?, descricao = ?, estoque = ?, preco = ? WHERE id_produto = ?";
            
            statement = ConnectionUtil.prepararQuery(connection, query);

            statement.setInt(1, produto.getIdProduto());
            statement.setString(2, produto.getTipoProduto().name()); //
            statement.setString(3, produto.getTitulo());
            statement.setString(4, produto.getDescricao());
            statement.setInt(5, produto.getEstoque());
            statement.setDouble(6, produto.getPreco());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.fecharConexao(connection, statement);
        }
    }

    public void removerProdutoPorId(int id){
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionUtil.iniciarConexao();
            String query = "DELETE FROM produtos WHERE id_produto = ?";

            statement = ConnectionUtil.prepararQuery(connection, query);
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.fecharConexao(connection, statement);
        }
    }

    private Produto extrairProdutoDoResultSet(ResultSet resultSet) throws SQLException{
        return new Produto(
            resultSet.getInt("id_produto"), 
            null, 
            resultSet.getString("titulo"), 
            resultSet.getString("descricao"), 
            resultSet.getInt("estoque"), 
            resultSet.getDouble("preco")
        );
    }
}