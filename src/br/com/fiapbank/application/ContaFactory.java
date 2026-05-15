package br.com.fiapbank.application;

import br.com.fiapbank.model.*;

public class ContaFactory {

    private static ContaFactory instance;

    private ContaFactory() {}

    public static ContaFactory getInstance() {
        if (instance == null) instance = new ContaFactory();
        return instance;
    }

    public Conta criarContaCorrente(Cliente cliente, String senha, Dinheiro saldo) {
        return new ContaCorrente(cliente, new ContaAcesso(senha), saldo);
    }

    public Conta criarContaPoupanca(Cliente cliente, String senha, Dinheiro saldo) {
        return new ContaPoupanca(cliente, new ContaAcesso(senha), saldo);
    }
}