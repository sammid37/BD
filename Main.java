// Projeto de Banco de Dados
// Enthony Miguel, Eduarda Donato e Samantha Medeiros

import java.util.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import backend.src.main.java.com.voleyrant.revsky.model.Loja;
import backend.src.main.java.com.voleyrant.revsky.model.Cliente;
import backend.src.main.java.com.voleyrant.revsky.model.Vendedor;

import backend.src.main.java.com.voleyrant.revsky.DAO.ClienteDAO;
import backend.src.main.java.com.voleyrant.revsky.DAO.VendedorDAO;

import backend.src.main.java.com.voleyrant.revsky.view.MenuContext;
import backend.src.main.java.com.voleyrant.revsky.view.DefaultMenuStrategy;
import backend.src.main.java.com.voleyrant.revsky.view.Cliente.ClienteMenuStrategy;
import backend.src.main.java.com.voleyrant.revsky.view.Vendedor.VendedorMenuStrategy;

public class Main {
  public static void main(String[] args) {
    // Variáveis globais
    MenuContext menuContext = new MenuContext();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String tipoUsuario = "vendedor"; // Altere para "cliente" para testar o menu do cliente

    int opcao = 0;
    boolean logado = false;

    
    // Define padrão de datas
    
    try {
      // Padrão de Projeto Strategy para exibir o Menu
      if (logado) {
        if (tipoUsuario.equals("cliente")) {
          menuContext.setMenuStrategy(new ClienteMenuStrategy());
        } else if (tipoUsuario.equals("vendedor")) {
          menuContext.setMenuStrategy(new VendedorMenuStrategy());
        } else {
          System.out.println("Tipo de usuário inválido");
          return;
        }
      } else {
        menuContext.setMenuStrategy(new DefaultMenuStrategy());
      }
  
      menuContext.exibirMenu();      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
