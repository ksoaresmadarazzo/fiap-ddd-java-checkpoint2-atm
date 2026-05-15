package br.com.fiapbank.model;

public class Cliente extends BaseEntity {

    private String nomeCompleto;

    public Cliente(String nomeCompleto) {
        super();
        this.nomeCompleto = nomeCompleto;
    }

    public String getNomeCompleto() { return nomeCompleto; }

    public String obterPrimeiroNome() {
        return nomeCompleto.trim().split(" ")[0];
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}