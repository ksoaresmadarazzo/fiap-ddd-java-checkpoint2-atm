package br.com.fiapbank.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ContaPoupanca extends Conta {

    public static final Double RENDIMENTO_MENSAL = 1.0;

    public ContaPoupanca(Cliente cliente, ContaAcesso contaAcesso, Dinheiro saldo) {
        super(cliente, contaAcesso, saldo, RENDIMENTO_MENSAL);
    }

    @Override
    protected void aplicarRegraDeTaxa() {
        BigDecimal rendimento = saldo.getValor()
                .multiply(BigDecimal.valueOf(taxa / 100))
                .setScale(2, RoundingMode.HALF_UP);
        Dinheiro valorRendimento = new Dinheiro(rendimento);
        saldo = saldo.somar(valorRendimento);
        registrarMovimentacao(valorRendimento, TipoMovimentacao.Rendimento);
    }

    public void aplicarTaxaMensal() {
        aplicarRegraDeTaxa();
    }
}