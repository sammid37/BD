package backend.src.main.java.com.voleyrant.revsky.view.Cliente;

import java.util.Scanner;

import java.text.ParseException;

import backend.src.main.java.com.voleyrant.revsky.view.CatalogoLoja;
import backend.src.main.java.com.voleyrant.revsky.view.MenuStrategy;

public class ClienteMenuStrategy implements MenuStrategy {
//  private CatalogoLoja catalogoLoja;
  private boolean usuarioLogado;
//
//  public ClienteMenuStrategy(CatalogoLoja catalogoLoja) {
//    this.catalogoLoja = catalogoLoja;
//  }
  @Override
  public void setUsuarioLogado(boolean usuarioLogado) {
    this.usuarioLogado = usuarioLogado;
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
        // meuPerfil()
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
        // sair()
        break;
      default:
        System.out.println("Opção inválida, tente novamente.");
        break;
    }
  }
}