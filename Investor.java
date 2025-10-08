public class Investor extends Adult {
    private String riskProfile;
    private float  moneyInvested;
    
    public Investor(String firstName, String lastName, String email, int phoneNumber, String address, String dateOfBirth, String holderID, String enrollmentDate, String financialStatus, String employmentStatus, String riskProfile, float moneyInvested) {
        super(firstName, lastName, email, phoneNumber, address, dateOfBirth, holderID, enrollmentDate, financialStatus, employmentStatus); 
        // call Adult constructor to initialize inherited personal, client, and employment details
        this.riskProfile = riskProfile;
        this.moneyInvested = moneyInvested;
    }
}