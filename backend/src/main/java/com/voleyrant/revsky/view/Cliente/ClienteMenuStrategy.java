package backend.src.main.java.com.voleyrant.revsky.view.Cliente;

import java.util.List;
import java.util.Scanner;

import java.text.ParseException;

import backend.src.main.java.com.voleyrant.revsky.model.Pedido;
import backend.src.main.java.com.voleyrant.revsky.view.CatalogoLoja;
import backend.src.main.java.com.voleyrant.revsky.view.MenuStrategy;
import backend.src.main.java.com.voleyrant.revsky.DAO.PedidoDAO;

public class ClienteMenuStrategy implements MenuStrategy {
//  private CatalogoLoja catalogoLoja;
  private boolean usuarioLogado;
//
//  public ClienteMenuStrategy(CatalogoLoja catalogoLoja) {
//    this.catalogoLoja = catalogoLoja;
//  }
  @Override
  public void setUsuarioLogado(boolean usuarioLogado) {
    this.usuarioLogado = usuarioLogado;
  }
  @Override
  public void exibirMenu() {
    System.out.println("\nCLIENTE");
    System.out.println("1 - Minha Conta");
    System.out.println("2 - Meu Carrinho");
    System.out.println("3 - Ver Produtos");
    System.out.println("4 - Sair");
  }
  @Override
  public void selecionarOpcao(int opcao, Scanner input) throws ParseException {
    switch (opcao) {
      case 1:
        System.out.println("Meu Perfil");
        // meuPerfil()
        break;
      case 2:
        System.out.println("Meus Pedidos");
        exibirPedidosCliente();

        break;
      case 3: 
        System.out.println("Ver catálogo");
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
  // Método para exibir os pedidos do cliente
  private void exibirPedidosCliente() {
    PedidoDAO pedidoDAO = new PedidoDAO();
    List<Pedido> pedidos = pedidoDAO.listarPedidos();

    // Verifica se existem pedidos para o cliente
    if (!((List<?>) pedidos).isEmpty()) {
      System.out.println("Lista de Pedidos do Cliente:");
      for (Pedido pedido : pedidos) {
        // Personalizar a exibição de cada pedido, (a ver se realemnte é encessário no código0
        System.out.println(pedido);
      }
    } else {
      System.out.println("Nenhum pedido encontrado para este cliente.");
    }
  }
}