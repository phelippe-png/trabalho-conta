package com.company;

public class ContaCorrente extends Conta {

    @Override
    public PessoaFisica getPessoa() {
        return super.getPessoa();
    }

    @Override
    public void setPessoa(PessoaFisica pessoa) {
        super.setPessoa(pessoa);
    }

    @Override
    public int getCodigoAgencia() {
        return super.getCodigoAgencia();
    }

    @Override
    public void setCodigoAgencia(int codigoAgencia) {
        super.setCodigoAgencia(codigoAgencia);
    }

    @Override
    public int getNumeroConta() {
        return super.getNumeroConta();
    }

    @Override
    public void setNumeroConta(int numeroConta) {
        super.setNumeroConta(numeroConta);
    }

    @Override
    public String getSenha() {
        return super.getSenha();
    }

    @Override
    public void setSenha(String senha) {
        super.setSenha(senha);
    }

    public void depositar(Double valor) {
        if (valor < 0){
            throw new RuntimeException("Você não pode depositar um valor negativo!!!");
        }

        this.saldo += valor;
    }

    public void sacar(Double valor) {
        if(valor > this.saldo){
            throw new RuntimeException("Valor de saque maior que o saldo atual!!!");
        }

        this.saldo -= valor;
    }

    public void transferir(Double valor) {
        if(valor > this.saldo){
            throw new RuntimeException("Valor de transferência maior que o saldo atual!!!");
        }

        this.saldo -= valor;
    }
}
