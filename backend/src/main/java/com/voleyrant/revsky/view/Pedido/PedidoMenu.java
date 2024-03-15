package backend.src.main.java.com.voleyrant.revsky.view.Pedido;

import backend.src.main.java.com.voleyrant.revsky.DAO.PedidoDAO;
import backend.src.main.java.com.voleyrant.revsky.model.Pedido;

import java.text.ParseException;
import java.util.Scanner;

public class PedidoMenu {
    public void exibirMenu() {
        System.out.println("\nPEDIDO");
        //System.out.println("1 - Fazer Pedido"); CatálogoLoja// exibirProdutod
        //System.out.println("2 - Listar Pedidos");
        System.out.println("3 - Atualizar Pedido");
        System.out.println("4 - Cancelar Pedido");
        System.out.println("5 - Voltar");
    }

    public void selecionarOpcao(int opcao, Scanner input) throws ParseException {
        switch (opcao) {

            case 1:
                System.out.println("Atualizar Pedido");
                // Método para atualizar um pedido existente
                // pedidoDAO.atualizarPedido();
                break;
            case 2:
                System.out.println("Cancelar Pedido");
                // Método para cancelar um pedido existente
                // pedidoDAO.cancelarPedido();
                break;
            case 3:
                System.out.println("Voltar");
                break;
            default:
                System.out.println("Opção inválida, tente novamente.");
                break;
        }

    }

}

