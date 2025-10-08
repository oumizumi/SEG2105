public class Large extends Business {
    private String taxID;
    private String corporateName;

    public Large(String firstName, String lastName, String email, int phoneNumber, String address,
                 String dateOfBirth, String holderID, String enrollmentDate, String financialStatus,
                 String employmentStatus, String businessName, String businessType,
                 int numberOfEmployees, float annualRevenue,
                 String taxID, String corporateName) {
        super(firstName, lastName, email, phoneNumber, address, dateOfBirth, holderID, enrollmentDate, financialStatus,
              employmentStatus, businessName, businessType, numberOfEmployees, annualRevenue);
        // call Business constructor to initialize inherited business structure and financial details
        this.taxID = taxID;
        this.corporateName = corporateName;
    }
}
