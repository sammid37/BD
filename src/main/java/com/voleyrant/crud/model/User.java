package src.main.java.com.voleyrant.crud.model;

import java.util.Date;

public class User {
  private int id;
  private String nome;
  private Date dataNasc;
  private String tel;
  private String email;
  private String senha;

  // Construtor
  public User(String nome, Date dataNasc, String tel, String email, String senha) {
    this.nome = nome;
    this.dataNasc = dataNasc;
    this.tel = tel;
    this.email = email;
    this.senha = senha;
  }

  // Getters e Setters
  public int getId() { return id; }
  public void setId(int id) { this.id = id; }  

  public String getNome() { return nome; }
  public void setNome(String nome) { this.nome = nome; }  

  public Date getDataNasc() { return dataNasc; }
  public void setDataNasc(Date dataNasc) { this.dataNasc = dataNasc; }  

  public String getTel() { return tel; }
  public void setTel(String tel) { this.tel = tel; }  

  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }

  public String getSenha() { return senha; }
  public void setSenha(String senha) { this.senha = senha; }

}