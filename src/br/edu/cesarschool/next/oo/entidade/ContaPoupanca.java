package br.edu.cesarschool.next.oo.entidade;



public class ContaPoupanca extends ContaCorrente  {

    private double percentualDeBonus;

       
    
    public ContaPoupanca() {
    }

    public ContaPoupanca(double percentualDeBonus) {
        this.percentualDeBonus = percentualDeBonus;
    }

    public ContaPoupanca(String numero, double saldo, String nomeDoCorrentista, double percentualDeBonus) {
        super(numero, saldo, nomeDoCorrentista);
        this.percentualDeBonus = percentualDeBonus;
    }

    public double getPercentualDeBonus() {
        return percentualDeBonus;
    }

    public void setPercentualDeBonus(double percentualDeBonus) {
        this.percentualDeBonus = percentualDeBonus;
    }

    @Override
    public void creditar (double valor) {

        super.creditar(super.getSaldo() + valor *(1 + percentualDeBonus / 100));

    }
    

    
}
