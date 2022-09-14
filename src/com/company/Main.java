package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();

        Hashtable<String, PessoaFisica> hashtablePessoa = new Hashtable<String, PessoaFisica>();
        Hashtable<String, ContaCorrente> hashtableContaCorrente = new Hashtable<String, ContaCorrente>();
        Hashtable<String, ContaPoupanca> hashtableContaPoupanca = new Hashtable<String, ContaPoupanca>();

        int opcao = 1;

        while (opcao != 0) {
            PessoaFisica pessoa = new PessoaFisica();
            ContaCorrente contaCorrente = new ContaCorrente();
            ContaPoupanca contaPoupanca = new ContaPoupanca();
            String nome;
            String cpf;
            int agencia;
            Double valor;
            String senha;
            int opcaoConta;
            int opcaoConfirmacao;

            System.out.println("| MENU SISTEMA BANCÁRIO |");
            System.out.println("(1) - Cadastrar Pessoa Física.");
            System.out.println("(2) - Cadastrar Conta Corrente.");
            System.out.println("(3) - Cadastrar Conta Poupança.");
            System.out.println("(4) - Efetuar Deposito.");
            System.out.println("(5) - Efetuar Saque.");
            System.out.println("(6) - Efetuar Transferência.");
            System.out.println("(0) - Sair do sistema.");
            opcao = scan.nextInt();

            switch (opcao) {
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                case 1:
                    System.out.println("");
                    System.out.println("- Opção selecionada: Cadastrar Pessoa Física -");

                    System.out.println("Informe o nome da pessoa: ");
                    nome = scan.next();

                    System.out.println("Informe o CPF da pessoa: ");
                    cpf = scan.next();

                    if (hashtablePessoa.containsKey(cpf)) {
                        System.out.println("A pessoa com esse CPF já está cadastrado!");
                        break;
                    }

                    pessoa.setNome(nome);
                    pessoa.setCPF(cpf);

                    hashtablePessoa.put(pessoa.getCPF(), pessoa);

                    System.out.println("");
                    System.out.println("Pessoa cadastrada com sucesso.");
                    break;
                case 2:
                    System.out.println("");
                    System.out.println("- Opção selecionada: Cadastrar Conta Corrente -");

                    System.out.println("Informe o CPF: ");
                    cpf = scan.next();

                    if (!hashtablePessoa.containsKey(cpf)) {
                        System.out.println("Esse CPF não existe!");
                        break;
                    }

                    if (hashtableContaCorrente.containsKey(cpf)) {
                        System.out.println("A pessoa com esse CPF já está vinculado a uma conta corrente!");
                        break;
                    }

                    contaCorrente.setPessoa(hashtablePessoa.get(cpf));
                    contaCorrente.setNumeroConta(random.nextInt(1000));
                    contaCorrente.setCodigoAgencia(123);

                    System.out.println("Informe a senha para abertura da conta: ");
                    contaCorrente.setSenha(scan.next());

                    hashtableContaCorrente.put(cpf, contaCorrente);
                    System.out.println("Conta cadastrada com sucesso. Número da conta: " + contaCorrente.getNumeroConta());

                    break;
                case 3:
                    System.out.println("");
                    System.out.println("- Opção selecionada: Cadastrar Conta Poupança -");

                    System.out.println("Informe o CPF da Pessoa: ");
                    cpf = scan.next();

                    if (!hashtablePessoa.containsKey(cpf)) {
                        System.out.println("Esse CPF não existe!");
                        break;
                    }

                    if (hashtableContaPoupanca.containsKey(cpf)) {
                        System.out.println("A pessoa com esse CPF já está vinculado a uma conta poupança!");
                        break;
                    }

                    contaPoupanca.setPessoa(hashtablePessoa.get(cpf));
                    contaPoupanca.setNumeroConta(random.nextInt(1000));
                    contaPoupanca.setCodigoAgencia(123);

                    System.out.println("Informe a senha para abertura da conta: ");
                    contaPoupanca.setSenha(scan.next());

                    hashtableContaPoupanca.put(cpf, contaPoupanca);
                    System.out.println("Conta cadastrada com sucesso. Número da conta: " + contaPoupanca.getNumeroConta());

                    break;
                case 4:
                    try {
                        System.out.println("Informe seu CPF: ");
                        cpf = scan.next();

                        if (!hashtablePessoa.containsKey(cpf)) {
                            System.out.println("Esse CPF não existe!");
                            break;
                        }

                        pessoa = hashtablePessoa.get(cpf);

                        do {
                            System.out.println("Informe novamente a agência de origem: ");
                            agencia = scan.nextInt();

                            if (agencia != 123) {
                                System.out.println("Agência inválida!");
                            }
                        } while (agencia != 123);

                        System.out.println("Deseja realizar o deposito em qual tipo de conta?");
                        System.out.println("(1) - Conta Corrente.");
                        System.out.println("(2) - Conta Poupança.");
                        opcaoConta = scan.nextInt();

                        switch (opcaoConta) {
                            case 1: //deposito conta corrente
                                if (!hashtableContaCorrente.containsKey(pessoa.getCPF())) {
                                    System.out.println("Esse CPF não está vinculado a essa conta!");
                                    break;
                                }

                                contaCorrente = hashtableContaCorrente.get(pessoa.getCPF());

                                System.out.println("Informe o valor de deposito: ");
                                valor = scan.nextDouble();

                                System.out.println("Informe a senha da conta: ");
                                senha = scan.next();

                                if (!senha.equals(contaCorrente.getSenha())) {
                                    System.out.println("Senha incorreta! Desconectado do sistema por motivos de segurança...");
                                    break;
                                }

                                System.out.println("Deseja confirmar o deposito de R$" + valor + "?");
                                System.out.println("(1) - Sim.");
                                System.out.println("(0) - Não.");
                                opcaoConfirmacao = scan.nextInt();

                                switch (opcaoConfirmacao) {
                                    case 1:
                                        contaCorrente.depositar(valor);
                                        System.out.println("Deposito realizado com sucesso.");
                                        break;
                                    case 0:
                                        System.out.println("Operação cancelada!");
                                        break;
                                }

                                break;
                            case 2: //deposito conta poupanca
                                if (!hashtableContaPoupanca.containsKey(pessoa.getCPF())) {
                                    System.out.println("Esse CPF não está vinculado a essa conta!");
                                    break;
                                }

                                contaPoupanca = hashtableContaPoupanca.get(pessoa.getCPF());

                                System.out.println("Informe o valor de deposito: ");
                                valor = scan.nextDouble();

                                System.out.println("Informe a senha da conta: ");
                                senha = scan.next();

                                if (!senha.equals(contaPoupanca.getSenha())) {
                                    System.out.println("Senha incorreta! Desconectado do sistema por motivos de segurança...");
                                    break;
                                }

                                System.out.println("Deseja confirmar o deposito de R$" + valor + "?");
                                System.out.println("(1) - Sim.");
                                System.out.println("(0) - Não.");
                                opcaoConfirmacao = scan.nextInt();

                                switch (opcaoConfirmacao) {
                                    case 1:
                                        contaPoupanca.depositar(valor);
                                        System.out.println("Deposito realizado com sucesso.");
                                        break;
                                    case 0:
                                        System.out.println("Operação cancelada!");
                                        break;
                                }
                        }
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 5:
                    try {
                        System.out.println("Informe seu CPF: ");
                        cpf = scan.next();

                        if (!hashtablePessoa.containsKey(cpf)) {
                            System.out.println("Esse CPF não existe!");
                            break;
                        }

                        pessoa = hashtablePessoa.get(cpf);

                        do {
                            System.out.println("Informe novamente a agência de origem: ");
                            agencia = scan.nextInt();

                            if (agencia != 123) {
                                System.out.println("Agência inválida!");
                            }
                        } while (agencia != 123);

                        System.out.println("Deseja realizar o saque em qual tipo de conta?");
                        System.out.println("(1) - Conta Corrente.");
                        System.out.println("(2) - Conta Poupança.");
                        opcaoConta = scan.nextInt();

                        switch (opcaoConta) {
                            case 1: //saque conta corrente
                                if (!hashtableContaCorrente.containsKey(pessoa.getCPF())) {
                                    System.out.println("Esse CPF não está vinculado a essa conta!");
                                    break;
                                }

                                contaCorrente = hashtableContaCorrente.get(pessoa.getCPF());

                                System.out.println("Informe o valor do saque: ");
                                valor = scan.nextDouble();

                                System.out.println("Informe a senha da conta: ");
                                senha = scan.next();

                                if (!senha.equals(contaCorrente.getSenha())) {
                                    System.out.println("Senha incorreta! Desconectado do sistema por motivos de segurança...");
                                    break;
                                }

                                System.out.println("Deseja confirmar o saque de R$" + valor + "?");
                                System.out.println("(1) - Sim.");
                                System.out.println("(0) - Não.");
                                opcaoConfirmacao = scan.nextInt();

                                switch (opcaoConfirmacao) {
                                    case 1:
                                        contaCorrente.sacar(valor);
                                        System.out.println("Saque realizado com sucesso.");
                                        break;
                                    case 0:
                                        System.out.println("Operação cancelada!");
                                        break;
                                }

                                break;
                            case 2: //saque conta poupanca
                                if (!hashtableContaPoupanca.containsKey(pessoa.getCPF())) {
                                    System.out.println("Esse CPF não está vinculado a essa conta!");
                                    break;
                                }

                                contaPoupanca = hashtableContaPoupanca.get(pessoa.getCPF());

                                System.out.println("Informe o valor do saque: ");
                                valor = scan.nextDouble();

                                System.out.println("Informe a senha da conta: ");
                                senha = scan.next();

                                if (!senha.equals(contaPoupanca.getSenha())) {
                                    System.out.println("Senha incorreta! Desconectado do sistema por motivos de segurança...");
                                    break;
                                }

                                System.out.println("Deseja confirmar o saque de R$" + valor + "?");
                                System.out.println("(1) - Sim.");
                                System.out.println("(0) - Não.");
                                opcaoConfirmacao = scan.nextInt();

                                switch (opcaoConfirmacao) {
                                    case 1:
                                        contaPoupanca.sacar(valor);
                                        System.out.println("Saque realizado com sucesso.");
                                        break;
                                    case 0:
                                        System.out.println("Operação cancelada!");
                                        break;
                                }

                                break;
                        }
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 6:
                    PessoaFisica pessoaDestino = new PessoaFisica();
                    PessoaFisica pessoaOrigem = new PessoaFisica();
                    ContaCorrente contaCorrenteOrigem = new ContaCorrente();
                    ContaCorrente contaCorrenteDestino = new ContaCorrente();
                    ContaPoupanca contaPoupancaOrigem = new ContaPoupanca();
                    ContaPoupanca contaPoupancaDestino = new ContaPoupanca();
                    String cpfOrigem;
                    String cpfDestino;

                    try {
                        System.out.println("Informe seu CPF: ");
                        cpfOrigem = scan.next();

                        if (!hashtablePessoa.containsKey(cpfOrigem)) {
                            System.out.println("Esse CPF não existe!");
                            break;
                        }

                        pessoaOrigem = hashtablePessoa.get(cpfOrigem);

                        do {
                            System.out.println("Informe a agência de origem: ");
                            agencia = scan.nextInt();

                            if (agencia != 123) {
                                System.out.println("Agência inválida!");
                            }
                        } while (agencia != 123);

                        System.out.println("Deseja realizar a transferência em qual tipo de conta?");
                        System.out.println("(1) - Conta Corrente.");
                        System.out.println("(2) - Conta Poupança.");
                        opcaoConta = scan.nextInt();

                        switch (opcaoConta) {
                            case 1: //transferencia conta corrente
                                if (!hashtableContaCorrente.containsKey(pessoaOrigem.getCPF())) {
                                    System.out.println("Esse CPF não está vinculado a esse tipo de conta!");
                                    break;
                                }

                                contaCorrenteOrigem = hashtableContaCorrente.get(pessoaOrigem.getCPF());

                                System.out.println("Informe a senha da sua conta: ");
                                senha = scan.next();

                                if (!senha.equals(contaCorrenteOrigem.getSenha())) {
                                    System.out.println("Senha incorreta! Desconectado do sistema por motivos de segurança...");
                                    break;
                                }

                                System.out.println("Informe o CPF da conta de destino: ");
                                cpfDestino = scan.next();

                                if (!hashtablePessoa.containsKey(cpfDestino)) {
                                    System.out.println("Esse CPF não existe!");
                                    break;
                                }

                                if (cpfDestino.equals(cpfOrigem)) {
                                    System.out.println("CPF de destino é igual ao CPF de origem!");
                                    break;
                                }

                                pessoaDestino = hashtablePessoa.get(cpfDestino);

                                do {
                                    System.out.println("Informe a agência da conta de destino: ");
                                    agencia = scan.nextInt();

                                    if (agencia != 123) {
                                        System.out.println("Agência inválida!");
                                    }
                                } while (agencia != 123);

                                System.out.println("Selecione o tipo da conta de destino?");
                                System.out.println("(1) - Conta Corrente.");
                                System.out.println("(2) - Conta Poupança.");
                                opcaoConta = scan.nextInt();

                                switch (opcaoConta) {
                                    case 1:
                                        if (!hashtableContaCorrente.containsKey(pessoaDestino.getCPF())) {
                                            System.out.println("O CPF da conta de destino não está vinculado a esse tipo de conta!");
                                            break;
                                        }

                                        contaCorrenteDestino = hashtableContaCorrente.get(pessoaDestino.getCPF());

                                        System.out.println("Informe o valor da transferência: ");
                                        valor = scan.nextDouble();

                                        System.out.println("Deseja confirmar a transferência de R$" + valor + "?");
                                        System.out.println("(1) - Sim.");
                                        System.out.println("(0) - Não.");
                                        opcaoConfirmacao = scan.nextInt();

                                        switch (opcaoConfirmacao) {
                                            case 1:
                                                contaCorrenteOrigem.transferir(valor);
                                                contaCorrenteDestino.depositar(valor);
                                                System.out.println("Transferência realizada com sucesso.");
                                                break;
                                            case 0:
                                                System.out.println("Operação cancelada!");
                                                break;
                                        }

                                        break;
                                    case 2:
                                        if (!hashtableContaPoupanca.containsKey(pessoaDestino.getCPF())) {
                                            System.out.println("O CPF da conta de destino não está vinculado a esse tipo de conta!");
                                            break;
                                        }

                                        contaPoupancaDestino = hashtableContaPoupanca.get(pessoaDestino.getCPF());

                                        System.out.println("Informe o valor da transferência: ");
                                        valor = scan.nextDouble();

                                        System.out.println("Deseja confirmar a transferência de R$" + valor + "?");
                                        System.out.println("(1) - Sim.");
                                        System.out.println("(0) - Não.");
                                        opcaoConfirmacao = scan.nextInt();

                                        switch (opcaoConfirmacao) {
                                            case 1:
                                                contaCorrenteOrigem.transferir(valor);
                                                contaPoupancaDestino.depositar(valor);
                                                System.out.println("Transferência realizada com sucesso.");
                                                break;
                                            case 0:
                                                System.out.println("Operação cancelada!");
                                                break;
                                        }

                                        break;
                                }

                                break;

                            case 2: //transferencia conta poupanca
                                if (!hashtableContaPoupanca.containsKey(pessoaOrigem.getCPF())) {
                                    System.out.println("Esse CPF não está vinculado a esse tipo de conta!");
                                    break;
                                }

                                contaPoupancaOrigem = hashtableContaPoupanca.get(pessoaOrigem.getCPF());

                                System.out.println("Informe a senha da sua conta: ");
                                senha = scan.next();

                                if (!senha.equals(contaPoupancaOrigem.getSenha())) {
                                    System.out.println("Senha incorreta! Desconectado do sistema por motivos de segurança...");
                                    break;
                                }

                                System.out.println("Informe o CPF da conta de destino: ");
                                cpfDestino = scan.next();

                                if (!hashtablePessoa.containsKey(cpfDestino)) {
                                    System.out.println("Esse CPF não existe!");
                                    break;
                                }

                                if (cpfDestino.equals(cpfOrigem)) {
                                    System.out.println("CPF de destino é igual ao CPF de origem!");
                                    break;
                                }

                                pessoaDestino = hashtablePessoa.get(cpfDestino);

                                do {
                                    System.out.println("Informe a agência da conta de destino: ");
                                    agencia = scan.nextInt();

                                    if (agencia != 123) {
                                        System.out.println("Agência inválida!");
                                    }
                                } while (agencia != 123);

                                System.out.println("Selecione o tipo da conta de destino?");
                                System.out.println("(1) - Conta Corrente.");
                                System.out.println("(2) - Conta Poupança.");
                                opcaoConta = scan.nextInt();

                                switch (opcaoConta) {
                                    case 1:
                                        if (!hashtableContaCorrente.containsKey(pessoaDestino.getCPF())) {
                                            System.out.println("O CPF da conta de destino não está vinculado a esse tipo de conta!");
                                            break;
                                        }

                                        contaCorrenteDestino = hashtableContaCorrente.get(pessoaDestino.getCPF());

                                        System.out.println("Informe o valor da transferência: ");
                                        valor = scan.nextDouble();

                                        System.out.println("Deseja confirmar a transferência de R$" + valor + "?");
                                        System.out.println("(1) - Sim.");
                                        System.out.println("(0) - Não.");
                                        opcaoConfirmacao = scan.nextInt();

                                        switch (opcaoConfirmacao) {
                                            case 1:
                                                contaPoupancaOrigem.transferir(valor);
                                                contaCorrenteDestino.depositar(valor);
                                                System.out.println("Transferência realizada com sucesso.");
                                                break;
                                            case 0:
                                                System.out.println("Operação cancelada!");
                                                break;
                                        }

                                        break;
                                    case 2:
                                        if (!hashtableContaPoupanca.containsKey(pessoaDestino.getCPF())) {
                                            System.out.println("O CPF da conta de destino não está vinculado a esse tipo de conta!");
                                            break;
                                        }

                                        contaPoupancaDestino = hashtableContaPoupanca.get(pessoaDestino.getCPF());

                                        System.out.println("Informe o valor da transferência: ");
                                        valor = scan.nextDouble();

                                        System.out.println("Deseja confirmar a transferência de R$" + valor + "?");
                                        System.out.println("(1) - Sim.");
                                        System.out.println("(0) - Não.");
                                        opcaoConfirmacao = scan.nextInt();

                                        switch (opcaoConfirmacao) {
                                            case 1:
                                                contaPoupancaOrigem.transferir(valor);
                                                contaPoupancaDestino.depositar(valor);
                                                System.out.println("Transferência realizada com sucesso.");
                                                break;
                                            case 0:
                                                System.out.println("Operação cancelada!");
                                                break;
                                        }

                                        break;
                                }

                                break;
                        }
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
            }
        }
    }
}
