// Projeto de Banco de Dados
// Enthony Miguel e Samantha Medeiros

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

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    /*Loja loja = new Loja(
      101,
      "Audio Sat",
      "12345599990"
    );*/
      
    try {
      Date dataNascimento = dateFormat.parse("15/06/1999");
      // Criar um cliente
      Cliente cliente = new Cliente(
        "Roger",      // nome
        dataNascimento,      // data_nasc
        "123455559",         // tel
        "roger@email.com",  // email
        "senha123",          // senha
        "Flamengo",          // time
        true                 // onePiece
      );

      // Salvar o cliente no banco de dados
      ClienteDAO clienteDAO = new ClienteDAO();
      clienteDAO.salvarCliente(cliente);

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
