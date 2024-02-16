package backend.src.main.java.com.voleyrant.revsky.view;

import java.util.Scanner;

import java.text.ParseException;

public class MenuContext {
  private MenuStrategy menuStrategy;

  public void setMenuStrategy(MenuStrategy menuStrategy) {
    this.menuStrategy = menuStrategy;
  }

  public void exibirMenu() {
    menuStrategy.exibirMenu();
  }

  public void selecionarOpcao(int opcao, Scanner input) throws ParseException {
    menuStrategy.selecionarOpcao(opcao, input);
  }
}