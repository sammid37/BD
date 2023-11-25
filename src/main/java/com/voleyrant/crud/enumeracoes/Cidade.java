package src.main.java.com.voleyrant.crud.enumeracoes;

public enum Cidade {
  SAO_PAULO("São Paulo", UF.SP),
  RIO_DE_JANEIRO("Rio de Janeiro", UF.RJ),
  BELO_HORIZONTE("Belo Horizonte", UF.MG),
  SALVADOR("Salvador", UF.BA),
  CURITIBA("Curitiba", UF.PR),
  RECIFE("Recife", UF.PE),
  MANAUS("Manaus", UF.AM);

  private final String nome;
  private final UF uf;

  Cidade(String nome, UF uf) {
    this.nome = nome;
    this.uf = uf;
  }

  public String getNome() {
    return nome;
  }

  public UF getUf() {
    return uf;
  }
}
