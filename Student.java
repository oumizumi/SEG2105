public class Student extends Client {
    private int    studentID;
    private String graduationDate;

    public Student(String firstName, String lastName, String email, int phoneNumber, String address,
                   String dateOfBirth, String holderID, String enrollmentDate, String financialStatus,
                   int studentID, String graduationDate) {
        super(firstName, lastName, email, phoneNumber, address, dateOfBirth, holderID, enrollmentDate, financialStatus);
        // call Client constructor tp initizalize inherited client and personal details
        this.studentID = studentID;
        this.graduationDate = graduationDate;
    }
}
