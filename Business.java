public class Business extends Adult {
    protected String businessName;
    protected String businessType;
    protected int    numberOfEmployees;
    protected float  annualRevenue;

    public Business(String firstName, String lastName, String email, int phoneNumber, String address, String dateofBirth, String holderID, String enrollmentDate, String financialStatus, String employmentStatus, String businessName, String businessType, int numberOfEmployees, float annualRevenue) {
        super(firstName, lastName, email, phoneNumber, address, dateofBirth, holderID, enrollmentDate, financialStatus, employmentStatus);
        // call Adult constructor to set up all inherited attributes including employment and client details
        this.businessName = businessName;
        this.businessType = businessType;
        this.numberOfEmployees = numberOfEmployees;
        this.annualRevenue = annualRevenue;

    }
    
}
