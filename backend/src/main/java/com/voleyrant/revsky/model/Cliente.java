package backend.src.main.java.com.voleyrant.revsky.model;

import java.util.Date;

public class Cliente extends User {
  private int idCliente;
  private String time;
  private boolean onePiece;
  private String cidade;
  private String estado;

  // Construtor
  public Cliente( int idCliente,
                  String nome,
                  Date data_nasc, 
                  String tel, 
                  String email, 
                  String senha,
                  String time, 
                  boolean onePiece, 
                  String estado, 
                  String cidade) {
    super(nome, data_nasc, tel, email, senha);
    this.idCliente = idCliente;
    this.time = time;
    this.onePiece = onePiece;
    this.cidade = cidade;
    this.estado = estado;
  }

  public Cliente( String nome,
                  Date data_nasc,
                  String tel,
                  String email,
                  String senha,
                  String time,
                  boolean onePiece,
                  String estado,
                  String cidade) {
    super(nome, data_nasc, tel, email, senha);
    this.time = time;
    this.onePiece = onePiece;
    this.cidade = cidade;
    this.estado = estado;
  }

  public int getIdCliente() { return idCliente; }
  public void setIdCliente(int idCliente) { this.idCliente = idCliente; }  

  public String getTime() { return time; }
  public void setTime(String time) { this.time = time; }  

  public boolean getFlagOP() { return onePiece; }
  public void setFlagOP(boolean onePiece) { this.onePiece = onePiece; } 
  
  public String getCidade() { return cidade; }
  public void setCidade(String cidade) { this.cidade = cidade; } 

  public String getEstado() { return estado; }
  public void setEstado(String estado) { this.estado = estado; } 

  // Método para exibir informações do cliente
  @Override
  public String toString() {
    return "Cliente {" +
      " id_cliente='" + getIdCliente() + '\'' +
      " nome='" + getNome() + '\'' +
      ", dataNasc='" + getDataNasc() + "'\'" +
      ", telefone='" + getTel() + "'\'" +
      ", email='" + getEmail() + "'\'" +
      ", time='" + time + "'\'" +
      ", onePice='" + onePiece + "'\'" +
      ", endereço='" + cidade + "-" + estado + '\'' +
    '}';
  }

}
