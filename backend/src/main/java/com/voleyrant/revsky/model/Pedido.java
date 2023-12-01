package backend.src.main.java.com.voleyrant.revsky.model;

import backend.src.main.java.com.voleyrant.revsky.enumeracoes.FormaPagamento;
import backend.src.main.java.com.voleyrant.revsky.enumeracoes.StatusPedido;

import java.util.List;

public class Pedido {
  private int idPedido;
  private int idClientePedido;
  private int idVendedorPedido;
  private List<Produto> produtos;
  private double valorTotal;
  private double desconto;
  private FormaPagamento formaPagamento;
  private StatusPedido status;

  // Construtor
  public Pedido(int idPedido, int idClientePedido, int idVendedorPedido, List<Produto> produtos, double valorTotal, double desconto, FormaPagamento formaPagamento, StatusPedido status) {
    this.idPedido = idPedido;
    this.idClientePedido = idClientePedido;
    this.idVendedorPedido = idVendedorPedido;
    this.produtos = produtos;
    this.valorTotal = valorTotal;
    this.desconto = desconto;
    this.formaPagamento = formaPagamento;
    this.status = status;
  }

  // Métodos Get e Set de cada atributo
  public int getIdPedido() { return idPedido; }
  public void setIdPedido(int idPedido) { this.idPedido = idPedido; }  

  public int getIdClientePedido() { return idClientePedido; }
  public void setIdClientePedido(int idClientePedido) { this.idClientePedido = idClientePedido; }  

  public int getIdVendedorPedido() { return idVendedorPedido; }
  public void setIdVendedorPedido(int idVendedorPedido) { this.idVendedorPedido = idVendedorPedido; }  

  public List<Produto> getItensPedido() { return produtos; }
  public void setItensPedido(List<Produto> produtos) { this.produtos = produtos; }  

  public double getValorTotal() { return valorTotal; }
  public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }

  public double getDesconto() { return desconto; }
  public void setDesconto(double desconto) { this.desconto = desconto; }

  public FormaPagamento getFormaPagamento() { return formaPagamento; }
  public void setFormaPagamento(FormaPagamento formaPagamento) { this.formaPagamento = formaPagamento; }

  public StatusPedido getStatusPedido() { return status; }
  public void setStatusPedido(StatusPedido status) { this.status = status; }
}
