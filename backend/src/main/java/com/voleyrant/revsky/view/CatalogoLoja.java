package backend.src.main.java.com.voleyrant.revsky.view;

import backend.src.main.java.com.voleyrant.revsky.DAO.ProdutoDAO;
import backend.src.main.java.com.voleyrant.revsky.enumeracoes.FormaPagamento;
import backend.src.main.java.com.voleyrant.revsky.enumeracoes.StatusPedido;
import backend.src.main.java.com.voleyrant.revsky.enumeracoes.TipoProduto;
import backend.src.main.java.com.voleyrant.revsky.model.Produto;
import backend.src.main.java.com.voleyrant.revsky.DAO.PedidoDAO;
import backend.src.main.java.com.voleyrant.revsky.model.Pedido;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.SQLException;
import backend.src.main.java.com.voleyrant.revsky.service.AuthService;


public class CatalogoLoja {
  private List<Produto> listaCompras = new ArrayList<>();
  private ProdutoDAO produtoDAO = new ProdutoDAO();

  public void exibirProdutos(Scanner input, int idCliente) {
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
        if (idCliente != 0) {
          System.out.println("\"Prosseguindo para fazer o pedido...\"");
          // chamada para realizar compras (informado)
          fazerPedido(input, idCliente);

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

  private void fazerPedido(Scanner input, int idCliente) {
    // Adicionar produtos ao pedido
    adicionarProdutosAoPedido(input);

    // Confirmar pedido
    System.out.println("Deseja confirmar os produtos selecionados? (S ou N)");
    String resposta = input.next();
    if (resposta.equalsIgnoreCase("S")) {
      // Criar o pedido
      Pedido pedido = criarPedido(idCliente, input);
      // Todo: Reduzir o estoque dos produtos
      for (Produto produto : listaCompras) {
        int novaQuantidade = produto.getEstoque() - produto.getQuantidadeProduto();
        produto.setEstoque(novaQuantidade);
        // Atualizar o estoque no banco de dados
        produtoDAO.atualizarEstoque(produto.getIdProduto(), novaQuantidade);
      }
      PedidoDAO.criarPedido(pedido);
      System.out.println("Pedido realizado com sucesso!");
    } else {
      System.out.println("Pedido cancelado.");
      // Possibilidade de editar ou adicionar produtos ao pedido
    }
  }

  private void adicionarProdutosAoPedido(Scanner input) {
    do {
      adicionarProduto(input);
      System.out.print("Deseja adicionar mais produtos ao pedido? (S ou N): ");
    } while (input.next().equalsIgnoreCase("S"));
  }

  private void adicionarProduto(Scanner input) {
    System.out.print("Digite o código do produto desejado: ");
    int idProduto = input.nextInt();
    Produto produto = produtoDAO.lerProdutoPorId(idProduto);
    if (produto != null) {
      System.out.print("Digite a quantidade desejada: ");
      int quantidade = input.nextInt();
      if (quantidade <= produto.getEstoque()) {
        produto.setQuantidadeProduto(quantidade);
        listaCompras.add(produto);
        System.out.println("Produto adicionado ao pedido.");
      } else {
        System.out.println("Quantidade indisponível em estoque.");
      }
    } else {
      System.out.println("Produto não encontrado.");
    }
  }

  private Pedido criarPedido(int idCliente, Scanner input) {
    double valorTotal = 0;
    FormaPagamento fp = null;
    for (Produto produto : listaCompras) {
      valorTotal += produto.getPreco() * produto.getQuantidadeProduto();
    }

    // Outros cálculos e atribuições

    Pedido pedido = new Pedido();
    // Setar valores do pedido
    pedido.setItensPedido(listaCompras);
    pedido.setIdClientePedido(idCliente);
    pedido.setValorTotal(valorTotal);

    for (FormaPagamento fpdisp : FormaPagamento.values()) {
      System.out.println(fpdisp.name());
    }
    do {
      System.out.print("Digite a forma de pagamento desejada: ");
      String formaPagmento = input.next().toUpperCase();
      try {
        fp = FormaPagamento.valueOf(formaPagmento);
      } catch (IllegalArgumentException e) {
        System.out.println("Tipo de Produto inválido. Tente novamente.");
      }
    } while (fp == null);
    pedido.setFormaPagamento(fp);
    pedido.setStatusPedido(StatusPedido.AGUARDANDO_APROVACAO_PAGAMENTO);


    return pedido;
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

  public void LerProdutoPorNome(String nomeProduto) {
    ProdutoDAO produtoDAO = new ProdutoDAO();

    Produto produto = produtoDAO.lerProdutoPorNome(nomeProduto);

    if (produto != null) {
      System.out.println("\nProduto encontrado:");
      System.out.println("Código | Nome Produto | Valor | Estoque");
      System.out.println(produto.getIdProduto()+" | "+ produto.getTitulo()+" | "+produto.getPreco()+" | "+produto.getEstoque());
      System.out.println("---------------------------");
    } else {
      System.out.println("\nProduto não encontrado.");
    }
  }
}

