package br.com.fiapbank.model;

public class Cliente extends BaseEntity {

    private String nomeCompleto;

    public Cliente(String nomeCompleto) {
        super();
        this.nomeCompleto = nomeCompleto;
    }

    public String getNomeCompleto() { return nomeCompleto; }

    public String obterPrimeiroNome() {
        String nome = nomeCompleto.trim().split(" ")[0];
        return nome.substring(0, 1).toUpperCase() + nome.substring(1).toLowerCase();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}