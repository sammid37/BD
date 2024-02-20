package backend.src.main.java.com.voleyrant.revsky.view;

import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import backend.src.main.java.com.voleyrant.revsky.DAO.ClienteDAO;
import backend.src.main.java.com.voleyrant.revsky.DAO.VendedorDAO;

import backend.src.main.java.com.voleyrant.revsky.model.Cliente;
import backend.src.main.java.com.voleyrant.revsky.model.Vendedor;

public class DefaultMenuStrategy implements MenuStrategy {

  private CatalogoLoja catalogoLoja;
  private boolean usuarioLogado; // verificar se deve ser 'false' sempre

  public DefaultMenuStrategy(CatalogoLoja catalogoLoja) {
    this.catalogoLoja = catalogoLoja;
  }

  @Override
  public void setUsuarioLogado(boolean usuarioLogado) {
    this.usuarioLogado = false;
  }

  @Override
  public void exibirMenu() {
    System.out.println("\nBEM-VINDO!");
    System.out.println("1 - Login"); // gerenciar produtos 
    System.out.println("2 - Cadastro");
    System.out.println("3 - Visualizar catálogo");
    System.out.println("4 - Informações da loja");
    System.out.println("5 - Encerrar aplicação"); 
  }

  // TODO: implementar lógica para definir o tipo de usuário (sugestão portas em que o projeto irá rodar)
  String tipoUsuario = "cliente";

  public void selecionarOpcao(int opcao, Scanner input) throws ParseException {
    switch (opcao) {
      case 1:
        System.out.println("Login");
        // login()
        break;
      case 2:
        System.out.println("Cadastro");
        if (tipoUsuario.equalsIgnoreCase("cliente")) {
          cadastroCliente(input);
        } else {
          System.out.println("Cadastro de vendedor");
          // cadastroVendedor(input);
        }
        break;
      case 3:
        System.out.println("Visualizar catálogo");
        catalogoLoja.exibirProdutos(input, usuarioLogado);
        break;
      case 4:
        System.out.println("Visualizar informações da loja");
        // info loja
        break;
      case 5:
        System.out.println("Encerrar aplicação");
        // Encerrar
        break;
      default:
        System.out.println("Opção inválida, tente novamente.");
        break;
    }
  }

public Cliente cadastroCliente(Scanner input) throws ParseException {
  ClienteDAO clienteDAO = new ClienteDAO();
  SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

  String nome, telefone, email, senha, nascimento, time, cidade, estado;
  boolean onePiece = true;

  Date dataNascimento;

  System.out.println("Cadastro");
  System.out.print("\nDigite seu nome: ");
  nome = input.next();

  System.out.print("\nDigite sua data de Nascimento: ");
  nascimento = input.next();
  dataNascimento = dateFormat.parse(nascimento);

  System.out.print("\nDigite seu telefone: ");
  telefone = input.next();

  System.out.print("\nDigite seu e-mail: ");
  email = input.next();

  System.out.print("\nDigite seu senha: ");
  senha = input.next();

  System.out.print("\nDigite seu time: ");
  time = input.next();

  System.out.print("\nDigite seu cidade: ");
  cidade = input.next();

  System.out.print("\nDigite seu estado: ");
  estado = input.next();

  Cliente cliente = new Cliente(
          nome,
          dataNascimento,
          telefone,
          email,
          senha,
          time,
          onePiece,
          cidade,
          estado
  );

  clienteDAO.criarCliente(cliente);

  return cliente;
}

  // public Vendedor cadastroVendedor() {
  //   Vendedor vendedor;
  //   VendedorDAO vendedorDAO = new VendedorDAO();

  //   System.out.println("Cadastro");
  //   System.out.print("\nDigite seu nome: ");
  //   System.out.print("\nDigite sua data de Nascimento: ");
  //   System.out.print("\nDigite seu telefone: ");
  //   System.out.print("\nDigite seu e-mail: ");
  //   System.out.print("\nDigite seu senha: ");

  //   return vendedor;
  // }
}