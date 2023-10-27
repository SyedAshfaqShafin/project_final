package jobSearchAndApplicationManagementSystem;
import java.io.*;
import java.util.*;
class User {
    private String username;
    private String password;
    // Add other user attributes like fullName, dateOfBirth, etc.

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        // Initialize other attributes here.
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
