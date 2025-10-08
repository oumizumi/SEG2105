public class Main {
    public static void main(String[] args) {
        // Create a loyalty program
        LoyaltyProgram program = new LoyaltyProgram("Student Saver", "2025-01-01");
        System.out.println("Created Loyalty Program: " + program.getProgramName());

        // Create two clients
        Client ines = new Client("Ines", "Yassmine", "ines@example.com", 613111111, "Ottawa", "2003-04-15", "C001", "2025-02-01", "Active");
        Client oumizumi = new Client("Oumizumi", "Gharad", "oumizumi@example.com", 613222222, "Kanata", "2002-10-10", "C002", "2025-02-01", "Active");

        // Add accounts for both clients
        ines.addChecking("InesChecking");
        ines.addSavings(0.03f, "InesSavings");
        oumizumi.addInvestment(2000, 0.10f, "OumizumiInvest");
        oumizumi.addLoan(0.05f, "Education Loan");

        // Show accounts before loyalty
        System.out.println("\n=== Client Accounts Before Loyalty ===");
        System.out.println("Ines:\n" + ines.listAssociations());
        System.out.println("Oumizumi:\n" + oumizumi.listAssociations());

        // Create loyalty accounts for both
        ines.createLoyalty(program, "2025-02-10");
        oumizumi.createLoyalty(program, "2025-02-10");

        // Add some points
        ines.getLoyalty().addPoints(200);
        oumizumi.getLoyalty().addPoints(350);

        // Add rewards to the program
        program.addReward("Free Coffee", 100, "2025-02-15");
        program.addReward("Movie Ticket", 200, "2025-02-16");

        // Print loyalty details
        System.out.println("\n=== Loyalty Program Details ===");
        System.out.println(program.listAssociations());

        // Show loyalty points
        System.out.println("Ines Loyalty Points: " + ines.getLoyalty().getPointsBalance());
        System.out.println("Oumizumi Loyalty Points: " + oumizumi.getLoyalty().getPointsBalance());

        // Remove one reward
        System.out.println("\nRemoving 'Free Coffee' reward...");
        RewardsAndPerks rewardToRemove = program.addReward("Temp Reward", 10, "2025-02-20"); // just to test remove
        program.removeReward(rewardToRemove);

        // Final output
        System.out.println("\n=== Final Loyalty State ===");
        System.out.println(program.listAssociations());
    }
}
