package backend.src.main.java.com.voleyrant.revsky.view;

import java.util.Scanner;

import java.text.ParseException;

public interface MenuStrategy {
  void exibirMenu();
  void selecionarOpcao(int opcao, Scanner input) throws ParseException;
}