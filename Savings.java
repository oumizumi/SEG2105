public class Savings extends Account {
    private float  interestRate;
    private String nickname;

    public Savings(Client owner, float interestRate, String nickname) {
        super(owner);    // call Account constructor to connect this savings account with its Client
        this.interestRate = interestRate;
        this.nickname = nickname;
    }

    public void applyInterest() {
        transferMoneyIn(getBalance() * interestRate);
    }
}
