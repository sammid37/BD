package backend.src.main.java.com.voleyrant.revsky.view.Vendedor;

import java.util.Scanner;

import backend.src.main.java.com.voleyrant.revsky.view.MenuStrategy;

public class VendedorMenuStrategy implements MenuStrategy {
  private boolean usuarioLogado;

  @Override
  public void setUsuarioLogado(boolean usuarioLogado) {
    this.usuarioLogado = usuarioLogado;
  }
  @Override
  public void exibirMenu() {
    System.out.println("\nVENDEDOR");
    System.out.println("1 - Gerenciar Produtos"); // gerenciar produtos 
    System.out.println("2 - Gerenciar Pedidos");
    System.out.println("3 - Gerar relatório");
    System.out.println("4 - Sair"); 
  }

  @Override
  public void selecionarOpcao(int opcao, Scanner input) {
    switch (opcao) {
      case 1:
        System.out.println("Gerenciar Produtos");
        // gerenciarProdutos()
        break;
      case 2:
        System.out.println("Gerenciar Pedidos");
        // gerenciarPedidos()
        break;
      case 3: 
        System.out.println("Gerar Relatório");
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