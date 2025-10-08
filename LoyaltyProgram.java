public class LoyaltyProgram {
    private final String programName;
    private final String launchDate;

    // Store links to loyalty accounts and rewards (simple arrays)
    private static final int MAX_LOYALTIES = 200;
    private static final int MAX_REWARDS   = 200;

    private ClientLoyaltyAccount[] loyaltyAccounts = new ClientLoyaltyAccount[MAX_LOYALTIES];
    private int loyaltyCount = 0;

    private RewardsAndPerks[] rewards = new RewardsAndPerks[MAX_REWARDS];
    private int rewardCount = 0;

    public LoyaltyProgram(String programName, String launchDate) {
        this.programName = programName;
        this.launchDate = launchDate;
    }

    public String getProgramName() { return programName; }

    // Link a loyalty account to this program (called from Client when a member joins)
    void attachLoyaltyInternal(ClientLoyaltyAccount cla) {
        if (cla == null) {
            System.out.println("[Error] Cannot attach a null loyalty account.");
            return;
        }
        if (loyaltyCount >= MAX_LOYALTIES) {
            System.out.println("[Error] Reached the maximum number of loyalty accounts.");
            return;
        }
        for (int i = 0; i < loyaltyCount; i++) {
            if (loyaltyAccounts[i] == cla) return; // already attached
        }
        loyaltyAccounts[loyaltyCount++] = cla;
    }

    // Detach a loyalty account from this program (called from Client when leaving)
    void detachLoyaltyInternal(ClientLoyaltyAccount cla) {
        int idx = -1;
        for (int i = 0; i < loyaltyCount; i++) {
            if (loyaltyAccounts[i] == cla) { idx = i; break; }
        }
        if (idx == -1) return;

        for (int i = idx; i < loyaltyCount - 1; i++) {
            loyaltyAccounts[i] = loyaltyAccounts[i + 1];
        }
        loyaltyAccounts[--loyaltyCount] = null;
    }

    // Create a reward under this program
    public RewardsAndPerks addReward(String rewardName, int pointsRedeemed, String redemptionDate) {
        if (rewardCount >= MAX_REWARDS) {
            System.out.println("[Error] Reached the maximum number of rewards.");
            return null;
        }
        RewardsAndPerks r = new RewardsAndPerks(this, rewardName, pointsRedeemed, redemptionDate);
        rewards[rewardCount++] = r;
        return r;
    }

    // Remove a reward from this program
    public void removeReward(RewardsAndPerks r) {
        int idx = -1;
        for (int i = 0; i < rewardCount; i++) {
            if (rewards[i] == r) { idx = i; break; }
        }
        if (idx == -1) {
            System.out.println("[Error] Reward not found.");
            return;
        }
        for (int i = idx; i < rewardCount - 1; i++) {
            rewards[i] = rewards[i + 1];
        }
        rewards[--rewardCount] = null;
    }

    // Assignment required trio ie add remove etc
    public Object add(String kind, Object... args) {
        if ("reward".equals(kind)) {
            return addReward((String) args[0], ((Number) args[1]).intValue(), (String) args[2]);
        }
        System.out.println("[Error] Unsupported add kind for LoyaltyProgram: " + kind + ". Use Client to create loyalty.");
        return null;
    }

    public void remove(String kind, Object targetOrKey) {
        if ("reward".equals(kind)) {
            removeReward((RewardsAndPerks) targetOrKey);
            return;
        }
        System.out.println("[Error] Unsupported remove kind for LoyaltyProgram: " + kind + ". Use Client to remove loyalty.");
    }

    public String listAssociations() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < loyaltyCount; i++) {
            ClientLoyaltyAccount c = loyaltyAccounts[i];
            sb.append("CLA#").append(c.getId()).append(" client=").append(c.getClient().getHolderID()).append("\n");
        }
        for (int i = 0; i < rewardCount; i++) {
            RewardsAndPerks r = rewards[i];
            sb.append("Reward:").append(r.getRewardName()).append("\n");
        }
        return sb.toString();
    }

    public String toString() {
        return "LoyaltyProgram(" + programName + ")";
    }
}
