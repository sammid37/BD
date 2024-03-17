package backend.src.main.java.com.voleyrant.revsky.view.Vendedor;

import backend.src.main.java.com.voleyrant.revsky.DAO.ClienteDAO;
import backend.src.main.java.com.voleyrant.revsky.DAO.ProdutoDAO;
import backend.src.main.java.com.voleyrant.revsky.DAO.RelatorioDAO;
import backend.src.main.java.com.voleyrant.revsky.model.Produto;
import backend.src.main.java.com.voleyrant.revsky.view.MenuStrategy;
import backend.src.main.java.com.voleyrant.revsky.view.Produto.ProdutoMenu;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.text.ParseException;

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
    ClienteDAO clienteDAO = new ClienteDAO();
    ProdutoDAO produtoDAO = new ProdutoDAO();
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
        String resumoRelatorio = relatorioDAO.obterResumo();
        List<String> pedidosDoMes = relatorioDAO.obterPedidosDoMes();
        List<String> clientesCadastrados = clienteDAO.lerTodosClientes();
        List<Produto> produtosCadastrados = produtoDAO.listarProdutos();

        gerarRelatorioTXT(resumoRelatorio, pedidosDoMes, clientesCadastrados, produtosCadastrados);
        break;
      case 4: 
        System.out.println("Sair");
        break;
      default:
        System.out.println("Opção inválida, tente novamente.");
        break;
    }
  }

  /**
   * Gera um arquivo .txt contendo um resumo de: produtos cadastrados,
   * produtos em estoque, pedidos finalizados e lista de clientes.
   * @param resumoRelatorio
   * @param relPedidos
   * @param relClientes
   * @param relProdutos
   */
  public void gerarRelatorioTXT(String resumoRelatorio,
                                List<String> relPedidos,
                                List<String> relClientes,
                                List<Produto> relProdutos) {
    System.out.println("Gerando relatório...");
    String nomeArquivo = "relatorio.txt";

    try {
      File arquivo = new File(nomeArquivo);
      boolean arquivoExiste = arquivo.exists();

      FileWriter fileWriter;
      if (arquivoExiste) {
        fileWriter = new FileWriter(nomeArquivo, true); // Abrir o arquivo para adicionar conteúdo
      } else {
        fileWriter = new FileWriter(nomeArquivo); // Criar um novo arquivo
      }

      try (PrintWriter writer = new PrintWriter(fileWriter)) {
        writer.println("-----------------------------------------------------");
        writer.println(resumoRelatorio);
        writer.println("-----------------------------------------------------");
        writer.println();
        writer.println("Relatório de Pedidos:");
        for (String pedido : relPedidos) {
          writer.println(pedido);
        }
        writer.println();
        writer.println("Relatório de Clientes:");
        for (String cliente : relClientes) {
          writer.println(cliente);
        }
        writer.println();
        writer.println("Relatório de Produtos:");
        for (Produto produto : relProdutos) {
          writer.println(formatarProduto(produto));
        }
        writer.println();

        System.out.println("Relatório gerado com sucesso. Arquivo salvo como " + nomeArquivo);
      }
    } catch (IOException e) {
      System.err.println("Erro ao gerar o relatório: " + e.getMessage());
    }
  }

  private String formatarProduto(Produto produto) {
    return String.format("ID: %d, Tipo: %s, Título: %s, Descrição: %s, Estoque: %d, Preço: %.2f",
            produto.getIdProduto(),
            produto.getTipoProduto(),
            produto.getTitulo(),
            produto.getDescricao(),
            produto.getEstoque(),
            produto.getPreco());
  }
}