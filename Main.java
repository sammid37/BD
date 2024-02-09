// Projeto de Banco de Dados
// Enthony Miguel, Eduarda Donato e Samantha Medeiros

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import backend.src.main.java.com.voleyrant.revsky.DAO.ClienteDAO;
import backend.src.main.java.com.voleyrant.revsky.DAO.VendedorDAO;
import backend.src.main.java.com.voleyrant.revsky.model.Cliente;
import backend.src.main.java.com.voleyrant.revsky.model.Loja;
import backend.src.main.java.com.voleyrant.revsky.model.Vendedor;

public class Main {
  public static void main(String[] args) {
    System.out.println("Hello World.\n");

    // Define padrão de datas
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    /*Loja loja = new Loja(
      101,
      "Audio Sat",
      "12345599990"
    );*/
      
    try {
      // inserir menu de opções

      Date dataNascimento = dateFormat.parse("18/08/1998");

      // Criar um cliente
      Cliente cliente = new Cliente(
        "bernardo",              // nome
        dataNascimento,          // data_nasc
        "123455559",         // tel
        "bernardo@email.com", // email
        "senha123",        // senha
        "gremio",           // time
        true,           // onePiece
        "Rio Grande do Norte",        // estado
        "Caicó"     // cidade
      );

      
      // Salvar o cliente no banco de dados
      ClienteDAO clienteDAO = new ClienteDAO();
      clienteDAO.criarCliente(cliente);

      // cliente.setNome("Juan");

      clienteDAO.removerClientePorId(4);
      
      // clienteDAO.editarClientePorId(idCliente, cliente);

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
