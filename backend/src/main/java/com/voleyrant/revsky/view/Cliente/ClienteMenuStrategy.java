package backend.src.main.java.com.voleyrant.revsky.view.Cliente;

import java.util.Scanner;

import java.text.ParseException;

import backend.src.main.java.com.voleyrant.revsky.DAO.ClienteDAO;
import backend.src.main.java.com.voleyrant.revsky.view.CatalogoLoja;
import backend.src.main.java.com.voleyrant.revsky.view.MenuStrategy;

public class ClienteMenuStrategy implements MenuStrategy {
  private CatalogoLoja catalogoLoja;

  public ClienteMenuStrategy(CatalogoLoja catalogoLoja) {
    this.catalogoLoja = catalogoLoja;
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
        // meusPedidos()
        break;
      case 3: 
        System.out.println("Ver catálogo");
        // Abrir catálogo
        break;
      case 4: 
        System.out.println("Sair");
        break;
      default:
        System.out.println("Opção inválida, tente novamente.");
        break;
    }
  }
  public void meuPerfil(Scanner input) {
    System.out.println("Meu perfil");
    System.out.println("1 - Atualizar o perfil");
    System.out.println("2 - Excluir conta");  // TODO: ver se é necessário ter essa opção
    System.out.println("3 - Voltar");

    int opcao = input.nextInt();
    switch (opcao) {
      case 1:
        atualizarPerfil(input);
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

  public void atualizarPerfil(Scanner input) {
    ClienteDAO clienteDAO = new ClienteDAO();
    System.out.println("1 - Atualizar email");
    System.out.println("2 - Atualizar senha");
    System.out.println("3 - Atualizar telefone");
    System.out.println("4 - Atualizar cidade e estado");

    String opcoes = input.nextLine();

    String[] opcoesArray = opcoes.split(",");

    // Verifique se a entrada está dentro dos limites esperados (mínimo 1, máximo 4 opções)
    if (opcoesArray.length < 1 || opcoesArray.length > 4) {
      System.out.println("Você deve escolher entre 1 e 4 opções.");
      return;
    }
    // TODO: inserir métodos e capturar o ID do cliente logado!
    for (String opcao : opcoesArray) {
      switch (opcao.trim()) { // Remova espaços em branco extras e converta para int
        case "1":
          System.out.println("Atualizando email...");
          System.out.println("Informe o novo email: ");

          // Lógica para atualizar o email
          break;
        case "2":
          System.out.println("Atualizando senha...");
          System.out.println("Informe a senha atual: ");

          System.out.println("Informe a nova senha: ");
          System.out.println("Valide a nova email: ");
          // Lógica para atualizar a senha
          break;
        case "3":
          System.out.println("Atualizando telefone...");
          // Lógica para atualizar o telefone
          break;
        case "4":
          System.out.println("Atualizando cidade e estado...");
          // Lógica para atualizar a cidade e o estado
          break;
        default:
          System.out.println("Opção inválida: " + opcao);
          break;
      }
    }

    input.close();
  }
}