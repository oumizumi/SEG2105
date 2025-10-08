public class Individual extends Adult {
    private float annualIncome;

    public Individual(String firstName, String lastName, String email, int phoneNumber, String address,
                      String dateOfBirth, String holderID, String enrollmentDate, String financialStatus,
                      String employmentStatus, float annualIncome) {
        super(firstName, lastName, email, phoneNumber, address, dateOfBirth, holderID, enrollmentDate, financialStatus, employmentStatus);
        // call Adult constructor to set inherited personal, client, and employment information
        this.annualIncome = annualIncome;
    }
}
