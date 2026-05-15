package br.com.fiapbank.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Movimentacao {

    private LocalDateTime dataHora;
    private TipoMovimentacao tipo;
    private Dinheiro valor;

    public Movimentacao(LocalDateTime dataHora, Dinheiro valor, TipoMovimentacao tipo) {
        this.dataHora = dataHora;
        this.tipo = tipo;
        this.valor = valor;
    }

    public LocalDateTime getDataHora() { return dataHora; }
    public TipoMovimentacao getTipo() { return tipo; }
    public Dinheiro getValor() { return valor; }

    public String getDataHoraFormatada() {
        return dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Movimentacao other = (Movimentacao) obj;
        return dataHora.equals(other.dataHora) && tipo == other.tipo;
    }
}