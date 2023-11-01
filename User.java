package jobSearchAndApplicationManagementSystem;

public class User {
    private String username;
    private String password;
    private String fullName;
    private String dateOfBirth;
    private String institute;
    private String degree;
    private String cgpa;
    private String phoneNumber;
    private String email;
    private String address;

    public User(String username, String password, String fullName, String dateOfBirth, String institute, String degree, String cgpa, String phoneNumber, String email, String address) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.institute = institute;
        this.degree = degree;
        this.cgpa = cgpa;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getInstitute() {
        return institute;
    }

    public String getDegree() {
        return degree;
    }

    public String getCgpa() {
        return cgpa;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
}
