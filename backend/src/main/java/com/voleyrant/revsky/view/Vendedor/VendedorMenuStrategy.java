package backend.src.main.java.com.voleyrant.revsky.view.Vendedor;

import backend.src.main.java.com.voleyrant.revsky.view.MenuStrategy;
import backend.src.main.java.com.voleyrant.revsky.view.Produto.ProdutoMenu;

import java.text.ParseException;
import java.util.Scanner;

public class VendedorMenuStrategy implements MenuStrategy {
  ProdutoMenu produtoMenu = new ProdutoMenu();
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
  public void selecionarOpcao(int opcao, Scanner input) throws ParseException {
    switch (opcao) {
      case 1:
        System.out.println("Gerenciar Produtos");
        produtoMenu.exibirMenu();
        System.out.println("Digite uma opção: ");
        opcao = input.nextInt();
        produtoMenu.selecionarOpcao(opcao, input);
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