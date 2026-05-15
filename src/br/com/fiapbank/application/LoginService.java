package br.com.fiapbank.application;

import br.com.fiapbank.model.Conta;

public class LoginService {

    private Conta conta;

    public LoginService(Conta conta) {
        this.conta = conta;
    }

    public Boolean autorizar(String senha) {
        return conta.getContaAcesso().validarSenha(senha);
    }
}