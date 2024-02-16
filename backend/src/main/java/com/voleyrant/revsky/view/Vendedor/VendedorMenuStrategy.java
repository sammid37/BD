package backend.src.main.java.com.voleyrant.revsky.view.Vendedor;

import backend.src.main.java.com.voleyrant.revsky.view.MenuStrategy;

public class VendedorMenuStrategy implements MenuStrategy {
  @Override
  public void exibirMenu() {
    System.out.println("VENDEDOR\n");
    System.out.println("1 - Painel\n"); // gerenciar produtos 
    System.out.println("2 - Gerenciar Pedidos\n");
    System.out.println("3 - Gerar relatório\n");
    System.out.println("4 - Sair\n"); 
  }
}