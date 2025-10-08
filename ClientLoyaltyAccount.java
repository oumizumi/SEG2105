public class ClientLoyaltyAccount {
    private static int NEXT_ID = 1;

    private final int id;
    private int pointsBalance;
    private String enrollmentDate;

    private final Client client;
    private final LoyaltyProgram program;

    ClientLoyaltyAccount(Client client, LoyaltyProgram program, String enrollmentDate) {
        this.id = NEXT_ID++;
        this.client = client;
        this.program = program;
        this.enrollmentDate = enrollmentDate;
        this.pointsBalance = 0;
    }

    // Getters
    public int getId() {
        return this.id;
    }

    public Client getClient() {
        return this.client;
    }

    public LoyaltyProgram getProgram() {
        return this.program;
    }

    public int getPointsBalance() {
        return this.pointsBalance;
    }

    public String getEnrollmentDate() {
        return this.enrollmentDate;
    }

    public void addPoints(int points) {
        if (points < 0) {
            System.out.println("[Error] Cannot add negative points.");
            return;
        }
        pointsBalance += points;
    }

    public Object add(String kind, Object... args) {
        System.out.println("[Error] ClientLoyaltyAccount has no associations to add.");
        return null;
    }

    public void remove(String kind, Object targetOrKey) {
        System.out.println("[Error] ClientLoyaltyAccount cannot be detached directly. Use Client.remove(\"loyalty\", ...).");
    }

    public String listAssociations() {
        return "client=" + client.getHolderID() + "\nprogram=" + program.getProgramName();
    }

    public String toString() {
        return "CLA#" + id + " points=" + pointsBalance;
    }
}
