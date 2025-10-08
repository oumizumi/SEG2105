public abstract class Person {
    protected String firstName;
    protected String lastName;
    protected String email;
    protected int    phoneNumber;
    protected String address;
    protected String dateOfBirth;
    protected String holderID;

    protected Person(String firstName, String lastName, String email, int phoneNumber,
                     String address, String dateOfBirth, String holderID) {
        this.firstName   = firstName;
        this.lastName    = lastName;
        this.email       = email;
        this.phoneNumber = phoneNumber;
        this.address     = address;
        this.dateOfBirth = dateOfBirth;
        this.holderID    = holderID;
    }

    //getterssssss
    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public int getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getAddress() {
        return this.address;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String getHolderID() {
        return this.holderID;
    }

    //association methods(add, remove, etc.)
    public Object add(String kind, Object... args) {
        System.out.println("[Error] " + getClass().getSimpleName() +
                           " has no associations to add for '" + kind + "'.");
        return null;
    }

    public void remove(String kind, Object targetOrKey) {
        System.out.println("[Error] " + getClass().getSimpleName() +
                           " has no associations to remove for '" + kind + "'.");
    }

    public String listAssociations() {
        return "";
    }
}
