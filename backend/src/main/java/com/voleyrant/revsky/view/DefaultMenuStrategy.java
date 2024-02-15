package backend.src.main.java.com.voleyrant.revsky.view;

public class DefaultMenuStrategy implements MenuStrategy {
  @Override
  public void exibirMenu() {
    System.out.println("BEM-VINDO!\n");
    System.out.println("1 - Login\n"); // gerenciar produtos 
    System.out.println("2 - Cadastro\n");
    System.out.println("3 - Visualizar catálogo\n");
    System.out.println("4 - Informações para contato\n");
    System.out.println("5 - Encerrar aplicação\n"); 
  }
}
