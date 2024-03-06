package backend.src.main.java.com.voleyrant.revsky.view.Produto;

import backend.src.main.java.com.voleyrant.revsky.DAO.ProdutoDAO;
import backend.src.main.java.com.voleyrant.revsky.model.Produto;
import backend.src.main.java.com.voleyrant.revsky.enumeracoes.TipoProduto;

import java.text.ParseException;
import java.util.Scanner;

import static backend.src.main.java.com.voleyrant.revsky.enumeracoes.TipoProduto.CONECTOR;

public class ProdutoMenu {
    public void exibirMenu() {
        System.out.println("\nPRODUTO");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Atualizar");
        System.out.println("3 - Listar");
        System.out.println("4 - Deletar");
        System.out.println("5 - Sair");
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
                // TODO: listar todos e listar por id (opção digitada pode ser um número ou a palavra 'todos')
                lerProduto(input);
                // Abrir catálogo
                break;
            case 4:
                System.out.println("Deletar Produto");
                excluirProduto(input);
                // sair()
                break;
            case 5:
                break;
            default:
                System.out.println("Opção inválida, tente novamente.");
                break;
        }
    }

    public Produto cadastroProduto(Scanner input) throws ParseException {
        ProdutoDAO produtoDAO = new ProdutoDAO();

        TipoProduto tipo;
        String titulo, descricao;
        int estoque;
        double preco;

        System.out.println("Cadastro de Produto");
        System.out.print("\nDigite o tipo de Produto (CONECTOR, CABO, RECEPTOR, CONTROLE, SKY, SUPORTE, SERVICO): ");
        String tipoStr = input.next();
        tipo = TipoProduto.valueOf(tipoStr.toUpperCase());

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

        TipoProduto tipo = CONECTOR;
        String titulo, descricao;
        int estoque, idProduto;
        double preco;

        System.out.println("Edição de Produto");
        System.out.print("\nQual o id do produto: ");
        idProduto = input.nextInt();

        System.out.print("\nDigite o novo tipo de Produto: ");


        System.out.print("\nDigite o novo nome do Produto: ");
            titulo = input.next();
        System.out.print("\nDigite a nova descricao: ");
            descricao = input.next();
        System.out.print("\nDigite a nova quantidade em estoque: ");
            estoque = input.nextInt();
        System.out.print("\nDigite o novo preco: ");
            preco = input.nextDouble();

        Produto produto = new Produto(
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
            System.out.println("ID: " + produto.getIdProduto());
            System.out.println("Tipo: " + produto.getTipoProduto());
            System.out.println("Título: " + produto.getTitulo());
            System.out.println("Descrição: " + produto.getDescricao());
            System.out.println("Estoque: " + produto.getEstoque());
            System.out.println("Preço: " + produto.getPreco());
        } else {
            System.out.println("\nProduto não encontrado.");
        }
    }
}


