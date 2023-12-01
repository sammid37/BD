package backend.src.main.java.com.voleyrant.revsky.model;

public class Loja {
  private int idLoja;
  private String nomeLoja;
  private String cnpj;

  // Construtor
  public Loja(int idLoja, String nomeLoja, String cnpj) {
    this.idLoja = idLoja;
    this.nomeLoja = nomeLoja;
    this.cnpj = cnpj;
  }

  public int getIdLoja() { return idLoja; }
  public void setIdLoja(int idLoja) { this.idLoja = idLoja; }  

  public String getNomeLoja() { return nomeLoja; }
  public void setNomeLoja(String nomeLoja) { this.nomeLoja = nomeLoja; } 

  public String getCNPJ() { return cnpj; }
  public void setCNPJ(String cnpj) { this.cnpj = cnpj; }
}
