package br.com.fiapbank.model;

import java.math.BigDecimal;

public class ContaCorrente extends Conta {

    public static final Double TAXA_MANUTENCAO = 10.00;

    public ContaCorrente(Cliente cliente, ContaAcesso contaAcesso, Dinheiro saldo) {
        super(cliente, contaAcesso, saldo, TAXA_MANUTENCAO);
    }

    @Override
    protected void aplicarRegraDeTaxa() {
        Dinheiro taxaSaque = new Dinheiro(BigDecimal.valueOf(taxa));
        if (!taxaSaque.maiorQue(saldo)) {
            saldo = saldo.subtrair(taxaSaque);
            registrarMovimentacao(taxaSaque, TipoMovimentacao.Taxa);
        }
    }

    public void aplicarTaxaMensal() {
        aplicarRegraDeTaxa();
    }
}