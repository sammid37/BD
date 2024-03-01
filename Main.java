// Projeto de Banco de Dados
// Enthony Miguel, Eduarda Donato e Samantha Medeiros

import java.util.Scanner;

import backend.src.main.java.com.voleyrant.revsky.view.CatalogoLoja;
import backend.src.main.java.com.voleyrant.revsky.view.MenuContext;
import backend.src.main.java.com.voleyrant.revsky.view.DefaultMenuStrategy;
import backend.src.main.java.com.voleyrant.revsky.view.Cliente.ClienteMenuStrategy;
import backend.src.main.java.com.voleyrant.revsky.view.Vendedor.VendedorMenuStrategy;
import backend.src.main.java.com.voleyrant.revsky.view.Produto.ProdutoMenu;

public class Main {
  public static void main(String[] args) {
    MenuContext menuContext = new MenuContext();

    Scanner input = new Scanner(System.in);

    // TODO: Fazer a autenticação e Lógica para obter o tipo de usuário
    String tipoUsuario = "vendedor";

    boolean usuarioLogado = true;
    CatalogoLoja catalogoLoja = new CatalogoLoja();
    ProdutoMenu produtoMenu = new ProdutoMenu();


    int opcao = 0;

    try {
      while(true) {
        /*if (!usuarioLogado) {
           TODO: Fazer a autenticação
           usuarioLogado = autenticarUsuario(input);
           TODO: Lógica para obter o tipo de usuário
           if (usuarioLogado) {
             tipoUsuario = "vendedor";
           }
        }*/

        // Exibe o menu com base no contexto: usuário usuarioLogado (Cliente ou Vendedor) ou !usuarioLogado
        if (usuarioLogado) {
          if (tipoUsuario.equalsIgnoreCase("cliente")) {
            menuContext.setMenuStrategy(new ClienteMenuStrategy());
          } else if (tipoUsuario.equalsIgnoreCase("vendedor")) {
            menuContext.setMenuStrategy(new VendedorMenuStrategy());
          } else {
            System.out.println("Tipo de usuário inválido");
          }
        } else {
          menuContext.setMenuStrategy(new DefaultMenuStrategy(catalogoLoja));
        }

        //menuContext.menuUsuario();
        menuContext.exibirMenu();
        System.out.print("Digite uma opção: ");
        opcao = input.nextInt();

        menuContext.selecionarOpcao(opcao, input);

        // Condição de saída do loop
        if ((usuarioLogado && opcao == 4) || (!usuarioLogado && opcao == 5)) {
          break;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      input.close();
    }
  }
}