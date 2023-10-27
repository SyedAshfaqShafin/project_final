package jobSearchAndApplicationManagementSystem;
import java.io.*;
import java.util.*;
public class UserAuthenticationSystem {
    private static List<User> users = new ArrayList<>();
    private static String userFilePath = "users.txt";
    public static void main(String[] args) {
        loginMenu();
    }
    
    private static void loginMenu() {
    	loadUsers();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("1. Login");
        System.out.println("2. Signup");
        System.out.println("3. Exit");
        System.out.print("Please select an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character.

        switch (choice) {
            case 1:
                login(scanner);
                break;
            case 2:
                signup(scanner);
                break;
            case 3:
                saveUsers();
                System.out.println("Exiting the program. Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid option. Please try again.");
                loginMenu();
        }
    }
    
    private static void login(Scanner scanner) {
    	Menu menu = new Menu();
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (authenticateUser(username, password)) {
            System.out.println("Login successful. Welcome!");
            menu.content();
            // Add your menu for the authenticated user here.
        } else {
            System.out.println("Invalid username or password. Please try again.");
            System.out.print("Do you want to try again? (Y/N): ");
            String tryAgain = scanner.nextLine();
            if(tryAgain.equalsIgnoreCase("Y")) {
            	login(scanner);
            }
            if (tryAgain.equalsIgnoreCase("N")) {
            	loginMenu();
            }
        }
    }

    private static boolean authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private static void signup(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        users.add(new User(username, password));
        saveUsers();
        System.out.println("Signup successful. Welcome!");
        // Add your menu for the newly signed up user here.
    }

    private static void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(userFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String username = parts[0];
                    String password = parts[1];
                    users.add(new User(username, password));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading user data: " + e.getMessage());
        }
    }

    private static void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFilePath))) {
            for (User user : users) {
                writer.write(user.getUsername() + "," + user.getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving user data: " + e.getMessage());
        }
    }
}
