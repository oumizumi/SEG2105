public class Client extends Person {
    private String enrollmentDate;
    private String financialStatus;

    private static final int MAX_ACCOUNTS = 100;
    private Account[] accounts = new Account[MAX_ACCOUNTS];
    private int accountCount = 0;

    private ClientLoyaltyAccount loyalty = null;

    public Client(String firstName, String lastName, String email, int phoneNumber, String address,
                  String dateOfBirth, String holderID, String enrollmentDate, String financialStatus) {
        super(firstName, lastName, email, phoneNumber, address, dateOfBirth, holderID);
        this.enrollmentDate = enrollmentDate;
        this.financialStatus = financialStatus;
    }

    public String getEnrollmentDate() {
        return this.enrollmentDate;
    }

    public String getFinancialStatus() {
        return this.financialStatus;
    }

    public int getAccountCount() {
        return this.accountCount;
    }

    public Account[] getAccounts() {
        Account[] copy = new Account[this.accountCount];
        for (int i = 0; i < this.accountCount; i++) copy[i] = this.accounts[i];
        return copy;
    }

    public ClientLoyaltyAccount getLoyalty() {
        return this.loyalty;
    }

    public Account addChecking(String nickname) {
        if (accountCount >= MAX_ACCOUNTS) {
            System.out.println("[Error] Max accounts reached.");
            return null;
        }

        Checking a = new Checking(this, nickname);
        accounts[accountCount++] = a;
        return a;
    }

    public Account addSavings(float interestRate, String nickname) {
        if (accountCount >= MAX_ACCOUNTS) {
            System.out.println("[Error] Max accounts reached.");
            return null;
        }

        Savings a = new Savings(this, interestRate, nickname);
        accounts[accountCount++] = a;
        return a;
    }

    public Account addInvestment(int initialInvestment, float riskLevel, String nickname) {
        if (accountCount >= MAX_ACCOUNTS) {
            System.out.println("[Error] Max accounts reached.");
            return null;
        }

        Investment a = new Investment(this, initialInvestment, riskLevel, nickname);
        accounts[accountCount++] = a;
        return a;
    }

    public Account addLoan(float interestRate, String loanType) {
        if (accountCount >= MAX_ACCOUNTS) {
            System.out.println("[Error] Max accounts reached.");
            return null;
        }

        Loan a = new Loan(this, interestRate, loanType);
        accounts[accountCount++] = a;
        return a;
    }

    public void removeAccount(Account a) {
        int idx = -1;

        for (int i = 0; i < accountCount; i++) {
            if (accounts[i] == a) {
                idx = i;
                break;
            }
        }

        if (idx == -1) {
            System.out.println("[Error] Account not owned by this Client.");
            return;
        }

        a.close();

        for (int i = idx; i < accountCount - 1; i++) {
            accounts[i] = accounts[i + 1];
        }

        accounts[--accountCount] = null;
    }

    public ClientLoyaltyAccount createLoyalty(LoyaltyProgram program, String enrollDate) {
        if (loyalty != null) {
            System.out.println("[Error] Loyalty already exists (0..1).");
            return null;
        }

        if (program == null) {
            System.out.println("[Error] LoyaltyProgram required.");
            return null;
        }

        loyalty = new ClientLoyaltyAccount(this, program, enrollDate);

        program.attachLoyaltyInternal(loyalty);

        return loyalty;
    }

    public void removeLoyalty() {
        if (loyalty == null) {
            System.out.println("[Error] No loyalty to remove.");
            return;
        }

        LoyaltyProgram program = loyalty.getProgram();
        program.detachLoyaltyInternal(loyalty);
        loyalty = null;
    }

    public Object add(String kind, Object... args) {
        if (kind == null) {
            System.out.println("[Error] kind is required.");
            return null;
        }

        String k = kind.toLowerCase();

        if ("checking".equals(k)) {
            if (args.length < 1 || !(args[0] instanceof String)) {
                System.out.println("[Error] checking requires nickname:String.");
                return null;
            }
            return addChecking((String) args[0]);
        }

        if ("savings".equals(k)) {
            if (args.length < 2 || !(args[0] instanceof Float) || !(args[1] instanceof String)) {
                System.out.println("[Error] savings requires interestRate:float, nickname:String.");
                return null;
            }
            return addSavings(((Float) args[0]).floatValue(), (String) args[1]);
        }

        if ("investment".equals(k)) {
            if (args.length < 3 || !(args[0] instanceof Integer) || !(args[1] instanceof Float) || !(args[2] instanceof String)) {
                System.out.println("[Error] investment requires initialInvestment:int, riskLevel:float, nickname:String.");
                return null;
            }
            return addInvestment(((Integer) args[0]).intValue(), ((Float) args[1]).floatValue(), (String) args[2]);
        }

        if ("loan".equals(k)) {
            if (args.length < 2 || !(args[0] instanceof Float) || !(args[1] instanceof String)) {
                System.out.println("[Error] loan requires interestRate:float, loanType:String.");
                return null;
            }
            return addLoan(((Float) args[0]).floatValue(), (String) args[1]);
        }

        if ("loyalty".equals(k)) {
            if (args.length < 2 || !(args[0] instanceof LoyaltyProgram) || !(args[1] instanceof String)) {
                System.out.println("[Error] loyalty requires program:LoyaltyProgram, enrollDate:String.");
                return null;
            }
            return createLoyalty((LoyaltyProgram) args[0], (String) args[1]);
        }

        System.out.println("[Error] Unknown kind: " + kind);
        return null;
    }

    public void remove(String kind, Object targetOrKey) {
        if (kind == null) {
            System.out.println("[Error] kind is required.");
            return;
        }

        String k = kind.toLowerCase();

        if ("account".equals(k)) {
            if (!(targetOrKey instanceof Account)) {
                System.out.println("[Error] remove account requires Account instance.");
                return;
            }
            removeAccount((Account) targetOrKey);
            return;
        }

        if ("loyalty".equals(k)) {
            removeLoyalty();
            return;
        }

        System.out.println("[Error] Unknown kind: " + kind);
    }

    public String listAssociations() {
        String hasLoyalty = (this.loyalty != null ? "Yes" : "No");
        return "Accounts: " + this.accountCount + " | Loyalty: " + hasLoyalty;
    }
}
