public class Small extends Business {
    private String ownerName;
    private String localLicenseNumber;

    public Small(String firstName, String lastName, String email, int phoneNumber, String address,
                 String dateOfBirth, String holderID, String enrollmentDate, String financialStatus,
                 String employmentStatus, String businessName, String businessType,
                 int numberOfEmployees, float annualRevenue,
                 String ownerName, String localLicenseNumber) {
        super(firstName, lastName, email, phoneNumber, address, dateOfBirth, holderID, enrollmentDate, financialStatus,
              employmentStatus, businessName, businessType, numberOfEmployees, annualRevenue);
        // call Business constructor to initialize all inherited business and client-related details
        this.ownerName = ownerName;
        this.localLicenseNumber = localLicenseNumber;
    }
}
