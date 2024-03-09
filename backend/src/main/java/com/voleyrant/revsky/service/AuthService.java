package backend.src.main.java.com.voleyrant.revsky.service;

import backend.src.main.java.com.voleyrant.revsky.DAO.ClienteDAO;
import backend.src.main.java.com.voleyrant.revsky.DAO.VendedorDAO;
import backend.src.main.java.com.voleyrant.revsky.model.Cliente;
import backend.src.main.java.com.voleyrant.revsky.model.Vendedor;

public class AuthService {
  private final ClienteDAO clienteDAO;
  private final VendedorDAO vendedorDAO;

  private Cliente clienteLogado;
  private Vendedor vendedorLogado;

  public AuthService() {
    this.clienteDAO = new ClienteDAO();
    this.vendedorDAO = new VendedorDAO();
  }
  public String realizarLogin(String email, String senha) {
    Cliente cliente = clienteDAO.buscarPorEmail(email);

    if (cliente != null && cliente.getSenha().equals(senha)) {
      System.out.println("oii");
      this.clienteLogado = cliente;
      return "cliente";
    }

    Vendedor vendedor = vendedorDAO.buscarPorEmail(email);
    if (vendedor != null && vendedor.getSenha().equals(senha)) {
      this.vendedorLogado = vendedor;
      return "vendedor";
    }

    return null; // login falhou
  }

  public int obterIdClienteLogado() {
    if (clienteLogado != null) {
      return clienteLogado.getIdCliente();
    } else {
      System.out.println("Não foi possível recuperar o ID do cliente logado.");
      return -1;
    }
  }

  public int obterIdVendedorLogado() {
    if (clienteLogado != null) {
      return vendedorLogado.getIdVendedor();
    } else {
      System.out.println("Não foi possível recuperar o ID do vendedor logado.");
      return -1;
    }
  }
}
