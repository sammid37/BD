package backend.src.main.java.com.voleyrant.revsky.view.Vendedor;

import backend.src.main.java.com.voleyrant.revsky.DAO.RelatorioDAO;
import backend.src.main.java.com.voleyrant.revsky.view.MenuStrategy;
import backend.src.main.java.com.voleyrant.revsky.view.Produto.ProdutoMenu;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class VendedorMenuStrategy implements MenuStrategy {
  ProdutoMenu produtoMenu = new ProdutoMenu();

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
    RelatorioDAO relatorioDAO = new RelatorioDAO();
    switch (opcao) {
      case 1:
        System.out.println("Gerenciar Produtos");
        produtoMenu.exibirMenu();
        System.out.println("Digite uma opção: ");
        opcao = input.nextInt();
        produtoMenu.selecionarOpcao(opcao, input);
        break;
      case 2:
        System.out.println("Gerenciar Pedidos");
        // gerenciarPedidos()
        break;
      case 3: 
        System.out.println("Gerar Relatório");
        List<String> pedidosDoMes = relatorioDAO.obterPedidosDoMes();

        for (String pedido : pedidosDoMes) {
          System.out.println(pedido);
        }

        // relatorioDAO.gerarRelatorio();
        break;
      case 4: 
        System.out.println("Sair");
        break;
      default:
        System.out.println("Opção inválida, tente novamente.");
        break;
    }
  }
}