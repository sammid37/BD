package backend.src.main.java.com.voleyrant.revsky.model;

import backend.src.main.java.com.voleyrant.revsky.enumeracoes.FormaPagamento;
import backend.src.main.java.com.voleyrant.revsky.enumeracoes.StatusPedido;

import java.util.List;

public class Pedido {
  private int idPedido;
  private int idClientePedido;
  private int idVendedorPedido;
  private List<Produto> produtos;
  private double valorTotal; // após o desconto
  private double desconto;
  private FormaPagamento formaPagamento;
  private StatusPedido status;
  private double valorPedido; // sem desconto
  private String nomeCliente;

  public Pedido() {}

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
//construtor sem argumentos
  public Pedido() {

  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("ID Pedido: ").append(getIdPedido()).append("\n");
    sb.append("ID Cliente: ").append(getIdClientePedido()).append("\n");
    sb.append("ID Vendedor: ").append(getIdVendedorPedido()).append("\n");
    sb.append("Produtos: ").append(getItensPedido()).append("\n");
    sb.append("Valor Total: ").append(getValorTotal()).append("\n");
    sb.append("Desconto: ").append(getDesconto()).append("\n");
    sb.append("Forma de Pagamento: ").append(getFormaPagamento()).append("\n");
    sb.append("Status do Pedido: ").append(getStatusPedido()).append("\n");
    return sb.toString();
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

  public double getValorPedido() {
    return valorPedido;
  }

  public void setValorPedido(double valorPedido) {
    this.valorPedido = valorPedido;
  }

  public String getNomeCliente() {
    return nomeCliente;
  }

  public void setNomeCliente(String nomeCliente) {
    this.nomeCliente = nomeCliente;
  }
}
