package br.com.fiapbank.infrastructure;

import br.com.fiapbank.model.Conta;
import java.util.ArrayList;
import java.util.List;

public class ContaRepositorio {

    private List<Conta> contas = new ArrayList<>();

    public void salvar(Conta conta) {
        contas.add(conta);
    }

    public List<Conta> listarTodos() {
        return contas;
    }
}