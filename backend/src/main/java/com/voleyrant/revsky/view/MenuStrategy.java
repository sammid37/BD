package backend.src.main.java.com.voleyrant.revsky.view;

import java.util.Scanner;

import java.text.ParseException;

public interface MenuStrategy {
  /**
   * Interface para a exibição de um menu
   */
  void exibirMenu();

//  /**
//   * Permite settar o tipo de usuário logado no sistema
//   * TODO: avaliar se esse método é necessário e como deve ser tratado nas demais classes que fazem seu uso
//   * @param usuarioLogado
//   */
//  void setUsuarioLogado(boolean usuarioLogado);
//
//  /**
//   * Interface responsável por receber um valor digitado pelo usuário
//   * e que será avaliado com base nas opções disponibilizadas pelo menu de contexto provido
//   *
//   * @param opcao
//   * @param input
//   * @throws ParseException
//   */
  void selecionarOpcao(int opcao, Scanner input) throws ParseException;
}