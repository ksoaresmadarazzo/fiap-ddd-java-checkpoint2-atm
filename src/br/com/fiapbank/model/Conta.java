package br.com.fiapbank.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Conta extends BaseEntity {

    protected Cliente cliente;
    protected Dinheiro saldo;
    protected Double taxa;
    protected StatusConta status;
    protected LocalDate dataAbertura;
    protected ContaAcesso contaAcesso;
    protected List<Movimentacao> movimentacoes;

    public Conta(Cliente cliente, ContaAcesso contaAcesso, Dinheiro saldo, Double taxa) {
        super();
        this.cliente = cliente;
        this.contaAcesso = contaAcesso;
        this.saldo = saldo;
        this.taxa = taxa;
        this.status = StatusConta.Ativa ;
        this.dataAbertura = LocalDate.now();
        this.movimentacoes = new ArrayList<>();
    }

    public void realizarSaque(Dinheiro valor) {
        if (valor.getValor().compareTo(java.math.BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Valor de saque deve ser maior que zero.");
        if (valor.maiorQue(saldo))
            throw new IllegalArgumentException("Saldo insuficiente.");
        saldo = saldo.subtrair(valor);
        registrarMovimentacao(valor, TipoMovimentacao.Saque);
        aplicarRegraDeTaxa();
    }

    public void realizarDeposito(Dinheiro valor) {
        if (valor.getValor().compareTo(java.math.BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Valor de deposito de deve ser maior que zero.");
        saldo = saldo.somar(valor);
        registrarMovimentacao(valor, TipoMovimentacao.Deposito);
    }
    protected abstract void aplicarRegraDeTaxa();

    protected void registrarMovimentacao(Dinheiro valor, TipoMovimentacao tipo) {
        movimentacoes.add(new Movimentacao(LocalDateTime.now(), valor, tipo));
    }

    public Dinheiro getSaldo() { return saldo; }
    public Cliente getCliente() { return cliente; }
    public ContaAcesso getContaAcesso() { return contaAcesso; }
    public LocalDate getDataAbertura() { return dataAbertura; }
    public StatusConta getStatus() { return status; }
    public List<Movimentacao> getMovimentacoes() { return movimentacoes; }
}