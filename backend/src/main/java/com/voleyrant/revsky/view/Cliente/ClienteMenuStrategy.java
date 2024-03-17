package backend.src.main.java.com.voleyrant.revsky.view.Cliente;

import java.util.List;
import java.util.Scanner;

import java.text.ParseException;

import backend.src.main.java.com.voleyrant.revsky.model.Pedido;
import backend.src.main.java.com.voleyrant.revsky.view.CatalogoLoja;
import backend.src.main.java.com.voleyrant.revsky.view.MenuStrategy;
import backend.src.main.java.com.voleyrant.revsky.DAO.PedidoDAO;
import backend.src.main.java.com.voleyrant.revsky.DAO.ClienteDAO;
import backend.src.main.java.com.voleyrant.revsky.model.Cliente;
import backend.src.main.java.com.voleyrant.revsky.service.AuthService;
import backend.src.main.java.com.voleyrant.revsky.util.ValidateUtil;
import backend.src.main.java.com.voleyrant.revsky.view.Produto.ProdutoMenu;

public class ClienteMenuStrategy implements MenuStrategy {
  private CatalogoLoja catalogoLoja;
  private ProdutoMenu produtoMenu;

  private AuthService authService;



  public ClienteMenuStrategy(CatalogoLoja catalogoLoja, AuthService authService) {
    this.catalogoLoja = catalogoLoja;
    this.authService = authService;
  }

  @Override
  public void exibirMenu() {
    System.out.println("\nCLIENTE");
    System.out.println("1 - Minha Conta");
    System.out.println("2 - Meu Carrinho");
    System.out.println("3 - Ver Produtos");
    System.out.println("4 - Sair");
  }
  @Override
  public void selecionarOpcao(int opcao, Scanner input) throws ParseException {
    switch (opcao) {
      case 1:
        System.out.println("Meu Perfil");
        meuPerfil(input);
        break;
      case 2:
        System.out.println("Meus Pedidos");
        exibirPedidosCliente();
        break;
      case 3:
        System.out.println("Ver catálogo");
        int idCliente = authService.obterIdClienteLogado();
        catalogoLoja.exibirProdutos(input, idCliente);

        break;
      case 4: 
        System.out.println("Sair");
        break;
      default:
        System.out.println("Opção inválida, tente novamente.");
        break;
    }
  }
  // Método para exibir todos os pedidos de todos os cliente
  /*private void exibirPedidosCliente() {
    PedidoDAO pedidoDAO = new PedidoDAO();
    List<Pedido> pedidos = pedidoDAO.listarPedidos();

    // Verifica se existem pedidos para o cliente
    if (!((List<?>) pedidos).isEmpty()) {
      System.out.println("Lista de Pedidos do Cliente:");
      for (Pedido pedido : pedidos) {
        // Personalizar a exibição de cada pedido, (a ver se realemnte é encessário no código0
        System.out.println(pedido);
      }
      //
    } else {
      System.out.println("Nenhum pedido encontrado para este cliente.");
    }
  }*/
  private void exibirPedidosCliente() {
    PedidoDAO pedidoDAO = new PedidoDAO();

    // Obtendo o ID do cliente logado usando o authService
    int idCliente = authService.obterIdClienteLogado();
    List<Pedido> pedidos = pedidoDAO.listarPedidosPorClienteId(idCliente);

    // Verifica se existem pedidos para o cliente
    if (!pedidos.isEmpty()) {
      System.out.println("Lista de Pedidos do Cliente:");
      for (Pedido pedido : pedidos) {
        // Personalizar a exibição de cada pedido (a ver se realemnte é encessário no código)
        System.out.println(pedido.toString());
      }
      //exibição personalizada usando o método do java toString na classe model.
    } else {
      System.out.println("Nenhum pedido encontrado para este cliente.");
    }
  }

  public void meuPerfil(Scanner input) {
    int clientId = authService.obterIdClienteLogado();

    System.out.println("Meu perfil");
    System.out.println("1 - Atualizar o perfil");
    System.out.println("2 - Excluir conta");  // TODO: ver se é necessário ter essa opção
    System.out.println("3 - Voltar");

    int opcao = input.nextInt();
    switch (opcao) {
      case 1:
        atualizarPerfil(input, clientId);
        break;
      case 2:  // TODO: ver se é necessário ter essa opção
        System.out.println("Excluir conta");
        break;
      case 3:
        System.out.println("Sair");
        break;
      default:
        System.out.println("Opção inválida, tente novamente");
        break;
    }
  }

  public void atualizarPerfil(Scanner input, int clientId) {
    ClienteDAO clienteDAO = new ClienteDAO();
    Cliente cliente = clienteDAO.lerClientePorId(clientId);
    System.out.println(clientId);

    System.out.println(cliente.getNome());
    System.out.println("1 - Atualizar email");
    System.out.println("2 - Atualizar senha");
    System.out.println("3 - Atualizar telefone");
    System.out.println("4 - Atualizar cidade e estado");
    System.out.println("5 - Cancelar atualização");

    System.out.println("\nDigite a opção desejada: ");
    String opcoes = input.next();

    String[] opcoesArray = opcoes.split(",");

    // Verifique se a entrada está dentro dos limites esperados (mínimo 1, máximo 4 opções)
    if (opcoesArray.length < 1 || opcoesArray.length > 4) {
      System.out.println("Você deve escolher entre 1 e 4 opções.");
      return;
    }

    for (String opcao : opcoesArray) {
      switch (opcao.trim()) { // Remova espaços em branco extras e converta para int
        case "1":
          System.out.println("Atualizando email...");
          System.out.println("Informe o novo email: ");
          String novoEmail = input.next();
          if(ValidateUtil.validarEmail(novoEmail)) {
            clienteDAO.atualizarEmail(novoEmail, clientId);
          } else {
            System.out.println("Email inválido!");
          }
          break;
        case "2":
          System.out.println("Atualizando senha...");
          System.out.println("Informe a senha atual: ");
          String senhaAtual = input.next();

          // Verifica se a senha atual é válida
          if (senhaAtual.equals(cliente.getSenha())) {
            System.out.print("Informe a nova senha: ");
            String novaSenha = input.next();

            System.out.print("Valide a nova senha: ");
            String validarSenha = input.next();

            // Verifica se as novas senhas correspondem
            if (novaSenha.equals(validarSenha)) {
              // Atualiza a senha no banco de dados
              clienteDAO.atualizarSenha(novaSenha, clientId);
              System.out.println("Senha atualizada com sucesso!");
            } else {
              System.out.println("As novas senhas não correspondem. Tente novamente.");
            }
          } else {
            System.out.println("Senha atual incorreta. Tente novamente.");
          }
          break;
        case "3":
          System.out.println("Atualizando telefone...");
          System.out.println("Informe o novo telefone: ");
          String novoTel = input.next();
          if (ValidateUtil.validarTelefone(novoTel)) {
            clienteDAO.atualizarTelefone(novoTel, clientId);
          } else {
            System.out.println("Telefone inválido!");
          }
          break;
        case "4":
          System.out.println("Atualizando cidade e estado...");
          System.out.println("Informe a cidade: ");
          String novaCidade = input.nextLine();
          System.out.println("Informe o estado: ");
          String novoEstado = input.nextLine();
          clienteDAO.atualizarCidadeEstado(novaCidade, novoEstado, clientId);
          break;
        default:
          System.out.println("Opção inválida: " + opcao);
          break;
      }
    }
  }

  public void meusPedidos() {
    System.out.println("Meus pedidos");
  }
}