package br.com.fiapbank.model;

public class ContaAcesso {

    public static final Integer MAXIMO_TENTATIVAS = 3;

    private String senha;
    private Integer tentativas;
    private Boolean bloqueado;

    public ContaAcesso(String senha) {
        this.senha = senha;
        this.tentativas = 0;
        this.bloqueado = false;
    }

    public Boolean validarSenha(String senha) {
        if (bloqueado) return false;

        if (this.senha.equals(senha)) {
            tentativas = 0;
            return true;
        }

        tentativas++;
        if (tentativas >= MAXIMO_TENTATIVAS) bloqueado = true;
        return false;
    }

    public Boolean isBloqueado() { return bloqueado; }

    public void resetarTentativas() {
        tentativas = 0;
        bloqueado = false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ContaAcesso other = (ContaAcesso) obj;
        return senha.equals(other.senha);
    }
}