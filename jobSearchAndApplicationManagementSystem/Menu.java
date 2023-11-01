package jobSearchAndApplicationManagementSystem;
import java.io.*;
import java.util.*;
public class Menu {
	JobSearch jobsearch = new JobSearch();
	SeeAvailableJobs seeAvailableJobs = new SeeAvailableJobs();
	JobPost jobpost = new JobPost();
	Scanner scanner = new Scanner(System.in);
	void content(){
        System.out.println("1. Profile");
        System.out.println("2. See Available Jobs");
        System.out.println("3. Search Jobs");
        System.out.println("4. Search a Profile");
        System.out.println("5. Post a Job");
        System.out.println("6. Notifications");
        System.out.println("7. Logout");
        
        System.out.print("Please select an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice) {
        case 1:
            // Profile
            //profile(currentUsername);
            break;
        case 2:
            // See Available Jobs
            SeeAvailableJobs.main(null);
            break;
        case 3:
            // Search Jobs
            jobsearch.main(null);
            break;
        case 4:
            // Search a Profile
            //searchProfile();
            break;
        case 5:
            // Post a Job
            jobpost.main(null);
            break;
        case 6:
            // Notifications
            //showNotifications(currentUsername);
            break;
        case 7:
            // Logout
            System.out.println("Logged out successfully.");
            System.exit(0);
            break;
        default:
            System.out.println("Invalid option. Please try again.");
    }
	}
}
