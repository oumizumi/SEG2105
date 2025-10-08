public class Adult extends Client {
    protected String employmentStatus;

    public Adult(String firstName, String lastName, String email, int phoneNumber, String address, String dateOfBirth, String holderID, String enrollmentDate, String financialStatus, String employmentStatus) {
        super(firstName, lastName, email, phoneNumber, address, dateOfBirth, holderID, enrollmentDate, financialStatus);
        this.employmentStatus = employmentStatus;
    }
}