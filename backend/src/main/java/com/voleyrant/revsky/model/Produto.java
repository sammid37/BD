package backend.src.main.java.com.voleyrant.revsky.model;

import backend.src.main.java.com.voleyrant.revsky.enumeracoes.TipoProduto;

public class Produto {
  private int idProduto;
  private TipoProduto tipo;
  private String titulo;
  private String descricao;
  private int estoque;
  private double preco;
  private int quantidadeProduto;

  // Construtor
  public Produto (
    int idProduto,
    TipoProduto tipo, 
    String titulo, 
    String descricao, 
    int estoque, 
    double preco
  ) {
    this.idProduto = idProduto;
    this.tipo = tipo;
    this.titulo = titulo;
    this.descricao = descricao;
    this.estoque = estoque;
    this.preco = preco;
  }
  public Produto (
          TipoProduto tipo,
          String titulo,
          String descricao,
          int estoque,
          double preco
  ) {
    this.tipo = tipo;
    this.titulo = titulo;
    this.descricao = descricao;
    this.estoque = estoque;
    this.preco = preco;
  }

  // Métodos Get e Set de cada atributo
  public int getIdProduto() { return idProduto; }
  public void setIdProduto(int idProduto) { this.idProduto = idProduto; }

  public String getTitulo() { return titulo; }
  public void setTitulo(String titulo) { this.titulo = titulo; }

  public String getDescricao() { return descricao; }
  public void setDescricao(String descricao) { this.descricao = descricao; }
  
  public TipoProduto getTipoProduto() { return tipo; }
  public void setTipoProduto(TipoProduto tipo) { this.tipo = tipo; }
  
  public int getEstoque() { return estoque; }
  public void setEstoque(int estoque) { this.estoque = estoque; }  

  public double getPreco() { return preco; }
  public void setPreco(double preco) { this.preco = preco; }

  public void setQuantidadeProduto(int quantidade) {
    this.quantidadeProduto = quantidade;
  }

  public int getQuantidadeProduto() {
    return quantidadeProduto;
  }
}