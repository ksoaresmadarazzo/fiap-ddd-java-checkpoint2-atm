package br.com.fiapbank;

import br.com.fiapbank.application.LoginService;
import br.com.fiapbank.application.ContaFactory;
import br.com.fiapbank.application.ContaService;
import br.com.fiapbank.model.Cliente;
import br.com.fiapbank.model.Conta;
import br.com.fiapbank.model.Dinheiro;
import br.com.fiapbank.presentation.MenuBancario;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        //pega o nome e da as boas vindas
        System.out.println("--- Bem-vindo ao FIAP Bank ---");
        System.out.print("Digite seu nome completo: ");
        String nomeCompleto = scanner.nextLine().trim();
        // nao deixa o nome ficar vazio
        while (nomeCompleto.isEmpty()) {
            System.out.println("Nome nao pode ficar vazio.");
            System.out.print("Digite seu nome completo: ");
            nomeCompleto = scanner.nextLine().trim();
        }
        // cria o cliente com o nome
        Cliente cliente = new Cliente(nomeCompleto);
        System.out.println("Ola, " + cliente.obterPrimeiroNome() + "!");
        //pega a senha
        MenuBancario terminal = new MenuBancario(null, null);
        String senha = terminal.capturarSenhaForte();
        //cria a conta com o saldo zerado
        ContaFactory factory = ContaFactory.getInstance();
        Conta conta = factory.criarContaCorrente(cliente, senha, new Dinheiro(BigDecimal.ZERO));

        ContaService contaService = new ContaService(conta);
        LoginService autorizacaoService = new LoginService(conta);
        terminal = new MenuBancario(contaService, autorizacaoService);
        // faz o login
        Boolean autenticado = terminal.realizarLogin();
        if (!autenticado) return;
        // abre o menu
        terminal.exibirMenu(cliente.obterPrimeiroNome());
    }
}