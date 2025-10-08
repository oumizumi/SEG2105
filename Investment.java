public class Investment extends Account {
    private int initialInvestment;
    private float riskLevel;
    private String nickname;

    public Investment(Client owner, int initialInvestment, float riskLevel, String nickname) {
        super(owner);  // call Account constructor to link this investment to its Client
        this.initialInvestment = initialInvestment;
        this.riskLevel = riskLevel;
        this.nickname = nickname;
        transferMoneyIn(initialInvestment);
    }
}