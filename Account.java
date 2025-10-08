public abstract class Account {
    protected int accountNumber;
    protected float balance;
    protected String accountHolderID;

    private static int NEXT_NUM = 1000;   // auto generate unique account numbers
    
    protected Client owner; // association: Account → Client (exactly one)
    private boolean closed = false; // closed flag

    protected Account(Client owner) {
        if (owner == null) {
            System.out.println("[Error] Account requires exactly one Client (owner).");
        }
        this.owner = owner;
        this.accountNumber = NEXT_NUM++; // auto-increment account number

        // store the holder id if we have an owner
        if (owner != null) {
            this.accountHolderID = owner.getHolderID();
        } else {
            this.accountHolderID = "UNKNOWN";
        }

        // start with zero balance
        this.balance = 0.0f;
    }

    // Getters
    public int getAccountNumber() {
        return this.accountNumber;
    }

    public String getAccountHolderID() {
        return this.accountHolderID;
    }

    public float getBalance() {
        return this.balance;
    }

    public Client getOwner() {
        return this.owner;
    }

    // money movement 
    public void transferMoneyOut(float amount) {
        if (!isActive()) return;

        if (amount <= 0) {
            System.out.println("[Error] Amount must be positive.");
            return;
        }
        if (amount > balance) {
            System.out.println("[Error] Insufficient balance.");
            return;
        }
        balance -= amount;
    }

    public void transferMoneyIn(float amount) {
        if (!isActive()) return;

        if (amount <= 0) {
            System.out.println("[Error] Amount must be positive.");
            return;
        }
        balance += amount;
    }

    // Called by Client when removing this account
    void close() { closed = true; }

    protected boolean isActive() {
        if (closed) {
            System.out.println("[Error] This account has been closed.");
            return false;
        }
        if (owner == null) {
            System.out.println("[Error] Account has no client assigned.");
            return false;
        }
        return true;
    }

    // assignment required methods on every class
    public Object add(String type, Object... args) {
        System.out.println("[Error] " + getClass().getSimpleName() + " has no related objects to add.");
        return null;
    }

    public void remove(String type, Object target) {
        System.out.println("[Error] " + getClass().getSimpleName() + " has no related objects to remove.");
    }

    public String listAssociations() {
        return "Client: " + (owner != null ? owner.getHolderID() : "None");
    }

    public String toString() {
        return getClass().getSimpleName() + " #" + accountNumber + " | Balance: " + balance;
    }
}
