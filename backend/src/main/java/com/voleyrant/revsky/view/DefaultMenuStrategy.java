package backend.src.main.java.com.voleyrant.revsky.view;

import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import backend.src.main.java.com.voleyrant.revsky.DAO.ClienteDAO;
import backend.src.main.java.com.voleyrant.revsky.DAO.VendedorDAO;

import backend.src.main.java.com.voleyrant.revsky.model.Cliente;
import backend.src.main.java.com.voleyrant.revsky.model.Vendedor;
import backend.src.main.java.com.voleyrant.revsky.service.AuthService;
import backend.src.main.java.com.voleyrant.revsky.view.Cliente.ClienteMenuStrategy;
import backend.src.main.java.com.voleyrant.revsky.view.Vendedor.VendedorMenuStrategy;

public class DefaultMenuStrategy implements MenuStrategy {
  private String tipoUsuario;
  private String tipoUsuarioCadastro;
  private CatalogoLoja catalogoLoja;
  private AuthService authService = new AuthService();
  private boolean usuarioLogado; // verificar se deve ser 'false' sempre

  public DefaultMenuStrategy(CatalogoLoja catalogoLoja) {
    this.catalogoLoja = catalogoLoja;
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

  public void selecionarOpcao(int opcao, Scanner input) throws ParseException {
    switch (opcao) {
      case 1:
        System.out.println("Login");
        realizarLogin(input);
        break;
      case 2:
        System.out.println("Cadastro");
        System.out.print("Informe o tipo de cadastro (cliente ou vendedor): ");
        tipoUsuarioCadastro = input.next();
        if (tipoUsuarioCadastro.equalsIgnoreCase("cliente")) {
          cadastroCliente(input);
        } else if (tipoUsuarioCadastro.equalsIgnoreCase("vendedor")) {
          System.out.println("Cadastro de vendedor");
          cadastroVendedor(input);
        } else {
          System.out.println("Opção inválida!");
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

  /**
   * Realiza autenticação do usuário verificando se o email e senha digitados
   * estão cadastrados na tabela de clientes ou vendedores.
   * Com base no tipo de usuário logado, exibe o menu correspondente.
   * @param input
   * @throws ParseException
   */
public void realizarLogin(Scanner input) throws ParseException {
  int opcaoMenu;
  System.out.print("Email: ");
  String email = input.next();
  System.out.print("Senha: ");
  String senha = input.next();
  MenuContext menuContext = new MenuContext();
  CatalogoLoja catalogoLoja = new CatalogoLoja();

  tipoUsuario = authService.realizarLogin(email, senha);
  usuarioLogado = tipoUsuario != null;

  if (tipoUsuario != null) {
    menuContext.setMenuStrategy(
            tipoUsuario.equals("cliente") ? new ClienteMenuStrategy(catalogoLoja) : new VendedorMenuStrategy()
    );
    try {
      while(true) {
        menuContext.exibirMenu();
        opcaoMenu = input.nextInt();
        menuContext.selecionarOpcao(opcaoMenu, input);
        if(opcaoMenu == 4) {
          break;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  } else {
    System.out.println("Login falhou. Tente novamente.");
  }
}

public void cadastroCliente(Scanner input) throws ParseException {
  ClienteDAO clienteDAO = new ClienteDAO();
  SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

  String nome, telefone, email, senha, nascimento, time, cidade, estado;
  boolean onePiece = true;

  Date dataNascimento;

  System.out.println("Cadastro");
  System.out.print("\nDigite seu nome: ");
  nome = input.nextLine();

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
  time = input.nextLine();

  System.out.print("\nDigite seu cidade: ");
  cidade = input.nextLine();

  System.out.print("\nDigite seu estado (Sigla): ");
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
}

  public void cadastroVendedor(Scanner input) throws ParseException {
    VendedorDAO vendedorDAO = new VendedorDAO();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    String nome, telefone, email, senha, nascimento;
    Date dataNascimento;

    System.out.println("Cadastro");
    System.out.print("\nDigite seu nome: ");
    nome = input.next();

    System.out.print("\nDigite sua data de Nascimento: ");
    nascimento = input.next();
    dataNascimento = dateFormat.parse(nascimento);;

    System.out.print("\nDigite seu telefone: ");
    telefone = input.next();

    System.out.print("\nDigite seu e-mail: ");
    email = input.next();

    System.out.print("\nDigite seu senha: ");
    senha = input.next();
    Vendedor vendedor = new Vendedor(nome, dataNascimento, telefone, email, senha);

    vendedorDAO.criarVendedor(vendedor);
  }
}