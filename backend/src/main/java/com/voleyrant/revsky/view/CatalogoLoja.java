package backend.src.main.java.com.voleyrant.revsky.view;

import backend.src.main.java.com.voleyrant.revsky.DAO.ProdutoDAO;
import backend.src.main.java.com.voleyrant.revsky.model.Produto;

import java.util.List;
import java.util.Scanner;

public class CatalogoLoja {

  public void exibirProdutos(Scanner input, boolean usuarioLogado) {
    System.out.println("\nCatálogo Loja");
    System.out.println("Código | Nome Produto | Valor | Estoque");
    listarProdutosCatalogo();

      /* TODO:
       * -[] Exibir todos os produtos
       * Ao final da exibição:
       * -[] Verificar se o usuário está logado
       * -[] Perguntar se deseja adicionar algum produto ao carrinho (prosseguir se logado)
       * -[] Ou exbir a opção de voltar para o menu anterior
       * */
      System.out.println("Deseja realizar um pedido (S ou N)? ");
      String resposta;
      resposta = input.next();

      while (!resposta.equalsIgnoreCase("S") && !resposta.equalsIgnoreCase("N")) {
        System.out.println("Opção inválida, tente novamente.");
        System.out.print("Deseja realizar um pedido (S ou N)? ");
        resposta = input.next();
      }

      if (resposta.equalsIgnoreCase("S")) {
        if (usuarioLogado) {
          System.out.println("Prosseguir...");
          // chamada para realizar compras (informado
        } else {
          System.out.println("Você só pode realizar compras se estiver logado...");
        }
      } else {
        System.out.println("Retornando para o menu principal");
      }
    }

    public void exibirDescricaoProduto(Produto produto) {
        System.out.println("Exibir descrição do produto <Nome do produto>");
    }

  public void listarProdutosCatalogo() {
    ProdutoDAO produtoDAO = new ProdutoDAO();

    List<Produto> produtos = produtoDAO.listarProdutos();

    if (produtos != null && !produtos.isEmpty()) {
      for (Produto produto : produtos) {
        System.out.println(produto.getIdProduto()+" | "+ produto.getTitulo()+" | "+produto.getPreco()+" | "+produto.getEstoque());
        System.out.println("---------------------------");
      }
    } else {
      System.out.println("\nNenhum produto encontrado.");
    }
  }

}

