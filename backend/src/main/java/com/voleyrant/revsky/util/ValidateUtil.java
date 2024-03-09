package backend.src.main.java.com.voleyrant.revsky.util;

public class ValidateUtil {
  public static boolean validarTelefone(String telefone) {
    String regex = "\\(\\d{2}\\)\\d{4,5}-\\d{4}";
    return telefone.matches(regex);
  }

  public static boolean validarEmail(String email) {
    String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    return email.matches(regex);
  }

  public static boolean validarEstado(String estado) {
    String regex = "[A-Z]{2}";
    return estado.matches(regex);
  }
}
