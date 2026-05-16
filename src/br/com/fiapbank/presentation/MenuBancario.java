package br.com.fiapbank.presentation;

import br.com.fiapbank.application.LoginService;
import br.com.fiapbank.application.ContaService;
import br.com.fiapbank.model.ContaAcesso;
import br.com.fiapbank.model.Dinheiro;
import br.com.fiapbank.model.Movimentacao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class MenuBancario {

    private ContaService contaService;
    private LoginService autorizacaoService;
    private Scanner scanner;

    public MenuBancario(ContaService contaService, LoginService autorizacaoService) {
        this.contaService = contaService;
        this.autorizacaoService = autorizacaoService;
        this.scanner = new Scanner(System.in);
    }
    //loop do menu
    public void exibirMenu(String primeiroNome) {
        Boolean rodando = true;

        while (rodando) {
            System.out.println("\n--- Menu ---");
            System.out.println("[ 1 ] Consultar Saldo");
            System.out.println("[ 2 ] Fazer Deposito");
            System.out.println("[ 3 ] Fazer Saque");
            System.out.println("[ 4 ] Historico de Movimentacoes");
            System.out.println("[ 5 ] Sair");
            System.out.print("Opcao: ");

            String opcao = scanner.nextLine();

            if (opcao.equals("1")) {
                exibirSaldo();
            } else if (opcao.equals("2")) {
                realizarDeposito();
            } else if (opcao.equals("3")) {
                realizarSaque();
            } else if (opcao.equals("4")) {
                exibirHistorico();
            } else if (opcao.equals("5")) {
                rodando = false;
                System.out.println("O FIAP Bank agradece sua preferencia!");
            } else {
                System.out.println("Opcao invalida.");
            }
        }
    }
    //mostra o saldo da conta
    public void exibirSaldo() {
        System.out.println("Saldo: " + contaService.obterSaldo());
    }
    //mostra o hisotorico da conta
    public void exibirHistorico() {
        List<Movimentacao> lista = contaService.obterMovimentacoes();

        if (lista.isEmpty()) {
            System.out.println("Nenhuma movimentacao encontrada.");
            return;
        }

        System.out.println("\n--- Historico ---");
        for (Movimentacao m : lista) {
            System.out.println(m.getDataHoraFormatada() + " | " + m.getTipo() + " | " + m.getValor());
        }
    }
    // le o valor e deposita
    public void realizarDeposito() {
        System.out.print("Valor do deposito: R$ ");
        String entrada = scanner.nextLine().replace(",", ".");

        if (entrada.isEmpty()) {
            System.out.println("Digite um valor.");
            return;
        }

        try {
            Dinheiro valor = new Dinheiro(new BigDecimal(entrada));
            contaService.realizarDeposito(valor);
            System.out.println("Deposito realizado! Saldo: " + contaService.obterSaldo());
        } catch (NumberFormatException e) {
            System.out.println("Valor invalido, digite apenas numeros.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    // le o valor e faz o saque
    public void realizarSaque() {
        System.out.print("Valor do saque: R$ ");
        String entrada = scanner.nextLine().replace(",", ".");

        if (entrada.isEmpty()) {
            System.out.println("Digite um valor.");
            return;
        }

        try {
            Dinheiro valor = new Dinheiro(new BigDecimal(entrada));
            contaService.realizarSaque(valor);
            System.out.println("Saque realizado! Saldo: " + contaService.obterSaldo());
        } catch (NumberFormatException e) {
            System.out.println("Valor invalido, digite apenas numeros.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    // valida e cadastra a senha
    public String capturarSenhaForte() {
        String especiais = "!@#$%^&*()-_+=?><";
        String senha = "";
        Boolean senhaOk = false;

        System.out.println("\nA senha precisa ter:");
        System.out.println("- pelo menos 8 caracteres");
        System.out.println("- uma letra maiuscula");
        System.out.println("- um numero");
        System.out.println("- um caractere especial: " + especiais);

        while (!senhaOk) {
            System.out.print("\nCrie sua senha: ");
            senha = scanner.nextLine();

            Boolean tamanhoOk = senha.length() >= 8;
            Boolean temNumero = false;
            Boolean temMaiusc = false;
            Boolean temEspec  = false;

            for (Integer i = 0; i < senha.length(); i++) {
                Character c = senha.charAt(i);
                if (c >= '0' && c <= '9') temNumero = true;
                if (c >= 'A' && c <= 'Z') temMaiusc = true;
                if (especiais.indexOf(c) >= 0) temEspec = true;
            }

            if (tamanhoOk && temNumero && temMaiusc && temEspec) {
                senhaOk = true;
                System.out.println("Senha cadastrada!");
            } else {
                System.out.println("Senha invalida, tente novamente.");
            }
        }

        return senha;
    }
    // controla a tentativa de fazer o login
    public Boolean realizarLogin() {
        System.out.println("\n--- Login ---");
        Integer tentativas = 0;

        while (tentativas < ContaAcesso.MAXIMO_TENTATIVAS) {
            System.out.print("Digite sua senha: ");
            String senhaDigitada = scanner.nextLine();

            if (senhaDigitada.isEmpty()) {
                System.out.println("Digite sua senha.");
                continue;
            }

            if (autorizacaoService.autorizar(senhaDigitada)) return true;

            tentativas++;
            System.out.println("Senha errada. Tentativa " + tentativas + " de " + ContaAcesso.MAXIMO_TENTATIVAS + ".");
        }

        System.out.println("Acesso Bloqueado!");
        return false;
    }
}