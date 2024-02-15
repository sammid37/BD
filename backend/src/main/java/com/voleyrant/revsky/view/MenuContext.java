package backend.src.main.java.com.voleyrant.revsky.view;

public class MenuContext {
  private MenuStrategy menuStrategy;

  public void setMenuStrategy(MenuStrategy menuStrategy) {
    this.menuStrategy = menuStrategy;
  }

  public void exibirMenu() {
    menuStrategy.exibirMenu();
  }
}