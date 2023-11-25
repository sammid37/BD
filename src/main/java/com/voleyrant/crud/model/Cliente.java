package src.main.java.com.voleyrant.crud.model;

import java.util.Date;

public class Cliente extends User {
  private int idCliente;
  private String time;
  private boolean onePiece;
  // private UF uf;
  // private Cidade cidade;

  // Construtor
  public Cliente(int id, String nome, Date data_nasc, String tel, String email, String senha,
                  int idCliente, String time, boolean onePiece /*, UF uf, Cidade cidade*/) {
    super(id, nome, data_nasc, tel, email, senha);
    this.idCliente = idCliente;
    this.time = time;
    this.onePiece = onePiece;
    // this.uf = uf;
    // this.cidade = cidade;
  }

  public int getIdCliente() { return idCliente; }
  public void setIdCliente(int idCliente) { this.idCliente = idCliente; }  

  public String getTime() { return time; }
  public void setTime(String time) { this.time = time; }  

  public boolean getFlagOP() { return onePiece; }
  public void setFlagOP(boolean onePiece) { this.onePiece = onePiece; }  

}
