// Projeto de Banco de Dados
// Enthony Miguel, Eduarda Donato e Samantha Medeiros

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import backend.src.main.java.com.voleyrant.revsky.DAO.ClienteDAO;
import backend.src.main.java.com.voleyrant.revsky.DAO.VendedorDAO;
import backend.src.main.java.com.voleyrant.revsky.DAO.ProdutoDAO;
import backend.src.main.java.com.voleyrant.revsky.DAO.PedidoDAO;
import backend.src.main.java.com.voleyrant.revsky.model.Cliente;
import backend.src.main.java.com.voleyrant.revsky.model.Loja;
import backend.src.main.java.com.voleyrant.revsky.model.Vendedor;

public class Main {
  public static void main(String[] args) {

    // Define padrão de datas
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    /*Loja loja = new Loja(
      101,
      "Audio Sat",
      "12345599990"
    );*/
      
    try {
      //*TODO: inserir menu de opções

      Date dataNascimento = dateFormat.parse("18/08/1998");

      //------------------------------------ CRUD Cliente
    
      // Cliente cliente = new Cliente(
      //   "bernardo",              // nome
      //   dataNascimento,          // data_nasc
      //   "123455559",         // tel
      //   "bernardo@email.com", // email
      //   "senha123",        // senha
      //   "gremio",           // time
      //   true,           // onePiece
      //   "Rio Grande do Norte",        // estado
      //   "Caicó"     // cidade
      // );
      // clienteDAO.criarCliente(cliente); // CREATE 

      Cliente cliente;
      ClienteDAO clienteDAO = new ClienteDAO();
    
      cliente = clienteDAO.lerClientePorId(5); // READ
      
      if (cliente != null) {
        System.out.println("Cliente encontrado: " + cliente);
      } else {
        System.out.println("Cliente não encontrado.");
      }
      
      // cliente.setNome("Juan"); // UPDATE
      // clienteDAO.editarClientePorId(idCliente, cliente);
      // clienteDAO.removerClientePorId(4); // DELETE

      //------------------------------------ CRUD Vendedor
      // Criar um vendedor
      /*Vendedor vendedor = new Vendedor(
        2,                       // id
        "Ciclano",             // nome
        dataNascimento,             // dataNascimento
        "987654321",            // tel
        "ciclano@email.com",  // email
        "senha456",           // senha
        2001,            // idVendedor
        loja                        // loja
      );

      // Salvar o vendedor no banco de dados
      VendedorDAO vendedorDAO = new VendedorDAO();
      vendedorDAO.salvarVendedor(vendedor);*/

    } catch (ParseException e) {
      e.printStackTrace();
    }
  }
}
