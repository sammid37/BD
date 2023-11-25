package src.main.java.com.voleyrant.crud.model;

import java.util.Date;

public class Vendedor extends User {
  private int idVendedor;
  private Loja loja;

  // Construtor
  public Vendedor(int id, String nome, Date dataNascimento, String tel, String email, String senha,
                  int idVendedor, Loja loja) {
    super(id, nome, dataNascimento, tel, email, senha);
    this.idVendedor = idVendedor;
    this.loja = loja;
  }

  // get e set

  public int getIdVendedor() { return idVendedor; }
  public void setIdVendedor(int idVendedor) { this.idVendedor = idVendedor; }  

  public Loja getVinculoLoja() { return loja; }
  public void setVinculoLoja(Loja loja) { this.loja = loja; }  
}
