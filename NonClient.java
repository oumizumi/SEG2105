public class NonClient extends Person {
    private String bankName;

    public NonClient(String firstName, String lastName, String email, int phoneNumber, String address,
                     String dateOfBirth, String holderID, String bankName) {
        super(firstName, lastName, email, phoneNumber, address, dateOfBirth, holderID);   // call Person constructor to set shared personal info
        this.bankName = bankName;
        this.bankName = bankName;
    }
}
