public class RewardsAndPerks {
    // Every reward belongs to exactly one loyalty program
    private final LoyaltyProgram program;

    private String rewardName;
    private int pointsRedeemed;
    private String redemptionDate;

    RewardsAndPerks(LoyaltyProgram program, String rewardName, int pointsRedeemed, String redemptionDate) {
        if (program == null) {
            System.out.println("[Error] Program required for RewardsAndPerks.");
        }
        this.program = program;
        this.rewardName = rewardName;
        this.pointsRedeemed = pointsRedeemed;
        this.redemptionDate = redemptionDate;
    }

    public String getRewardName() { return rewardName; }

    // Assignmentnrequired trio (this class doesn’t manage outward associations)
    public Object add(String kind, Object... args) {
        System.out.println("[Error] RewardsAndPerks has no associations to add.");
        return null;
    }

    public void remove(String kind, Object targetOrKey) {
        System.out.println("[Error] RewardsAndPerks has no associations to remove.");
    }

    public String listAssociations() {
        return "program=" + program.getProgramName();
    }

    public String toString() {
        return "Reward(" + rewardName + ", points=" + pointsRedeemed + ")";
    }
}
