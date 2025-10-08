public class Loan extends Account {
    private float  interestRate;
    private String loanType;

    public Loan(Client owner, float interestRate, String loanType) {
        super(owner); // call Account constructor to set up the owner and base account details
        this.interestRate = interestRate;
        this.loanType = loanType;
    }

    public void applyInterest() {
        float add = getBalance() * interestRate;
        transferMoneyIn(add);
    }
}
