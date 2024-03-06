// Projeto de Banco de Dados
// Enthony Miguel, Eduarda Donato e Samantha Medeiros

import java.util.Scanner;

import backend.src.main.java.com.voleyrant.revsky.service.AuthService;
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

    String tipoUsuario = null;
    boolean usuarioLogado = false;
    int opcao = 0;

    CatalogoLoja catalogoLoja = new CatalogoLoja();
    ProdutoMenu produtoMenu = new ProdutoMenu();
    menuContext.setMenuStrategy(new DefaultMenuStrategy(catalogoLoja));

    try {
      while(true) {
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