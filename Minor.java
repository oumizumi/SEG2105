public class Minor extends Client {
    private String guardianID;

    public Minor(String firstName, String lastName, String email, int phoneNumber, String address,
                 String dateOfBirth, String holderID, String enrollmentDate, String financialStatus,
                 String guardianID) {
        super(firstName, lastName, email, phoneNumber, address, dateOfBirth, holderID, enrollmentDate, financialStatus); 
        // call Client constructor to set inherited client and personal details
        this.guardianID = guardianID;
    }
}
