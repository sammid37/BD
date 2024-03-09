package backend.src.main.java.com.voleyrant.revsky.view.Produto;

import backend.src.main.java.com.voleyrant.revsky.DAO.ClienteDAO;
import backend.src.main.java.com.voleyrant.revsky.model.Cliente;
import backend.src.main.java.com.voleyrant.revsky.DAO.ProdutoDAO;
import backend.src.main.java.com.voleyrant.revsky.model.Produto;
import backend.src.main.java.com.voleyrant.revsky.enumeracoes.TipoProduto;
import backend.src.main.java.com.voleyrant.revsky.DAO.PedidoDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import static backend.src.main.java.com.voleyrant.revsky.enumeracoes.TipoProduto.CONECTOR;

public class ProdutoMenu {
    public void exibirMenu() {
        System.out.println("\nPRODUTO");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Atualizar");
        System.out.println("3 - Ler");
        System.out.println("4 - Listar [TODOS]");
        System.out.println("5 - Deletar");
        System.out.println("6 - Sair");
    }

    public void selecionarOpcao(int opcao, Scanner input) throws ParseException {
        switch (opcao) {
            case 1:
                System.out.println("Cadastrar Produto");
                cadastroProduto(input);
                break;
            case 2:
                System.out.println("Editar Produto");
                editarProduto(input);
                // gerenciarPedidos()
                break;
            case 3:
                System.out.println("Ler Produto");
                lerProduto(input);
                // Abrir catálogo
                break;
            case 4:
                System.out.println("Listar Produto");
                listarProdutos();
                // sair()
                break;
            case 5:
                System.out.println("Deletar Produto");
                excluirProduto(input);
                // sair()
                break;
            case 6:
                break;
            default:
                System.out.println("Opção inválida, tente novamente.");
                break;
        }
    }

    public Produto cadastroProduto(Scanner input) throws ParseException {
        ProdutoDAO produtoDAO = new ProdutoDAO();

        TipoProduto tipo = null;
        String titulo, descricao;
        int estoque,idProduto;
        double preco;

        System.out.println("Cadastro de Produto");

        System.out.println("Tipos de Produto disponíveis:");
        for (TipoProduto tipoProduto : TipoProduto.values()) {
            System.out.println(tipoProduto.name());
        }

        do {
            System.out.print("Digite o tipo de Produto: ");
            String tipoEscolhido = input.next().toUpperCase();

            try {
                tipo = TipoProduto.valueOf(tipoEscolhido);
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo de Produto inválido. Tente novamente.");
            }
        } while (tipo == null);

        System.out.print("\nDigite o nome do Produto: ");
        titulo = input.next();

        System.out.print("\nDigite a descricao do "+titulo+": ");
        descricao = input.next();

        System.out.print("\nDigite a quantidade em estoque do "+titulo+": ");
        estoque = input.nextInt();

        System.out.print("\nDigite o preco do "+titulo+": ");
        preco = input.nextDouble();


        Produto produto = new Produto(
                tipo,
                titulo,
                descricao,
                estoque,
                preco
        );

        produtoDAO.criarProduto(produto);

        return produto;
    }

    public Produto editarProduto(Scanner input) throws ParseException {
        ProdutoDAO produtoDAO = new ProdutoDAO();

        TipoProduto tipo = null;
        String titulo, descricao;
        int estoque, idProduto;
        double preco;

        System.out.println("Edição de Produto");
        System.out.print("\nQual o id do produto: ");
        idProduto = input.nextInt();

        Produto produtoExistente = produtoDAO.lerProdutoPorId(idProduto);

        if (produtoExistente == null) {
            System.out.println("Produto não encontrado. Saindo da edição.");
            return null;
        }

        System.out.println("Produto encontrado:");
        mostraInfoProduto(produtoExistente);

        System.out.println("\nTipos de Produto disponíveis:");
        for (TipoProduto tipoProduto : TipoProduto.values()) {
            System.out.println(tipoProduto.name());
        }

        // Atualizar o tipo
        do {
            System.out.print("\nDigite o novo tipo de Produto (ou pressione Enter para manter o mesmo): ");
            String tipoEscolhido = input.nextLine().toUpperCase();

            try {
                tipo = (tipoEscolhido.isEmpty()) ? produtoExistente.getTipoProduto() : TipoProduto.valueOf(tipoEscolhido);
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo de Produto inválido. Tente novamente.");
            }
        } while (tipo == null);
        input.nextLine();

        // Atualizar o título
        System.out.print("Digite o novo título do Produto (ou pressione Enter para manter o mesmo): ");
        titulo = input.nextLine();
        titulo = (titulo.isEmpty()) ? produtoExistente.getTitulo() : titulo;

        // Atualizar a descrição
        System.out.print("Digite a nova descrição (ou pressione Enter para manter a mesma): ");
        descricao = input.nextLine();
        descricao = (descricao.isEmpty()) ? produtoExistente.getDescricao() : descricao;

        // Atualizar o estoque
        System.out.print("Digite a nova quantidade em estoque (ou pressione Enter para manter a mesma): ");
        String estoqueInput =input.nextLine();
        estoque = (estoqueInput.isEmpty()) ? produtoExistente.getEstoque() : Integer.parseInt(estoqueInput);

        // Atualizar o preço
        System.out.print("Digite o novo preço (ou pressione Enter para manter o mesmo): ");
        String precoInput =input.nextLine();
        preco = (precoInput.isEmpty()) ? produtoExistente.getPreco() : Double.parseDouble(precoInput);


        Produto produto = new Produto(
                idProduto,
                tipo,
                titulo,
                descricao,
                estoque,
                preco
        );

        produtoDAO.editarProdutoPorId(idProduto, produto);

        return produto;
    }

    public void excluirProduto(Scanner input) throws ParseException {
        ProdutoDAO produtoDAO = new ProdutoDAO();

        int idProduto;

        System.out.println("Edição de Produto");
        System.out.print("\nQual o id do produto: ");
        idProduto = input.nextInt();

        produtoDAO.removerProdutoPorId(idProduto);
    }

    public void lerProduto(Scanner input) throws ParseException {
        ProdutoDAO produtoDAO = new ProdutoDAO();


        int idProduto;

        System.out.println("leitura de Produto");
        System.out.print("\nQual o id do produto: ");
        idProduto = input.nextInt();

        Produto produto = produtoDAO.lerProdutoPorId(idProduto);

        if (produto != null) {
            System.out.println("\nProduto encontrado:");
            mostraInfoProduto(produto);
        } else {
            System.out.println("\nProduto não encontrado.");
        }
    }

    public void listarProdutos() {
        ProdutoDAO produtoDAO = new ProdutoDAO();

        List<Produto> produtos = produtoDAO.listarProdutos();

        if (produtos != null && !produtos.isEmpty()) {
            System.out.println("\nLista de Produtos:");

            for (Produto produto : produtos) {
                mostraInfoProduto(produto);
                System.out.println("-----------");
            }
        } else {
            System.out.println("\nNenhum produto encontrado.");
        }
    }

    public void mostraInfoProduto(Produto produto){
        System.out.println("ID: " + produto.getIdProduto());
        System.out.println("Tipo: " + produto.getTipoProduto());
        System.out.println("Título: " + produto.getTitulo());
        System.out.println("Descrição: " + produto.getDescricao());
        System.out.println("Estoque: " + produto.getEstoque());
        System.out.println("Preço: " + produto.getPreco());
    }
}