package backend.src.main.java.com.voleyrant.revsky.view.Cliente;

import backend.src.main.java.com.voleyrant.revsky.view.MenuStrategy;

public class ClienteMenuStrategy implements MenuStrategy {
  @Override
  public void exibirMenu() {
    System.out.println("CLIENTE\n");
    System.out.println("1 - Minha Conta\n");
    System.out.println("2 - Meu Carrinho\n");
    System.out.println("3 - Ver Produtos\n");
    System.out.println("4 - Sair\n");
  }
}