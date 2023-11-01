package jobSearchAndApplicationManagementSystem;

//Author: Nahian Syed Ahanaf
//Date: 01/11/2023

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class JobPost {
 public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in);

     System.out.println("*********************POST A JOB************************");
     System.out.print("Company Name: ");
     String companyName = scanner.nextLine();

     System.out.print("Position: ");
     String position = scanner.nextLine();

     int subjectIndex = -1;
     // Some subjects are here. I ignored those shitty publican's subject
     List<String> subjects = Arrays.asList(
             "Architecture",
             "Biochemistry and Microbiology",
             "Biotechnology",
             "Biochemistry and Biotechnology",
             "Business Administration",
             "Computer Science & Engineering (CSE)",
             "Development Studies",
             "Economics",
             "Electrical & Electronic Engineering (EEE)",
             "Electronics & Telecommunications Engineering (ETE)",
             "Environmental Science",
             "Law",
             "Media and Film",
             "Pharmacy",
             "Public Health",
             "Public Policy and Governance",
             "Others"  // Shikkhito Level (Others means Oshikkhito)
     );

     while (subjectIndex < 1 || subjectIndex > subjects.size()) {
         System.out.println("Subject Field:");
         for (int i = 0; i < subjects.size(); i++) {
             System.out.println(i + 1 + ". " + subjects.get(i));
         }
         try {
             System.out.print("Select a subject (Enter the corresponding number): ");
             subjectIndex = scanner.nextInt();
             if (subjectIndex < 1 || subjectIndex > subjects.size()) {
                 System.out.println("Enter a valid number for the subject.");
             }
         } catch (InputMismatchException e) {
             System.out.println("Enter a valid number for the subject.");
             scanner.nextLine();
         }
     }

     String subjectField = subjects.get(subjectIndex - 1);
     scanner.nextLine();

     System.out.println("Education Level:");
     List<String> educationLevels = Arrays.asList("SSC", "HSC", "B.Sc", "BA", "BBA", "M.Sc", "MA", "MBA", "B.Arch", "B.Pharm", "Others");
     for (int i = 0; i < educationLevels.size(); i++) {
         System.out.println(i + 1 + ". " + educationLevels.get(i));
     }
     System.out.print("Select an education level (Enter the corresponding number): ");
     int educationIndex = scanner.nextInt();
     String educationLevel = educationLevels.get(educationIndex - 1);

     float cgpa = 0;
     boolean validCGPA = false;
     while (!validCGPA) {
         System.out.print("Required CGPA (4.00 Scale): ");
         if (scanner.hasNextFloat()) {
             cgpa = scanner.nextFloat();
             if (cgpa > 4.00) {
                 System.out.println("Enter a valid CGPA.");
             } else {
                 validCGPA = true;
             }
         } else {
             System.out.println("Invalid CGPA. Please enter a valid CGPA.");
             scanner.next();
         }
     }

     System.out.print("Job Description: ");   //aur batao is job k bare mein
     scanner.nextLine();
     String jobDescription = scanner.nextLine();

     System.out.print("Salary: "); // Paisa hi Paisa hoga
     String salary = scanner.nextLine();

     System.out.print("Location: ");      //Location bol
     String location = scanner.nextLine();

     int jobTypeIndex = -1;
     List<String> jobTypes = Arrays.asList("Remote", "Hybrid", "Part Time", "Full Time");

     while (jobTypeIndex < 1 || jobTypeIndex > jobTypes.size()) {
         System.out.println("Job Type:");
         for (int i = 0; i < jobTypes.size(); i++) {
             System.out.println(i + 1 + ". " + jobTypes.get(i));
         }
         try {
             System.out.print("Select a job type (Enter the corresponding number): ");
             jobTypeIndex = scanner.nextInt();
             if (jobTypeIndex < 1 || jobTypeIndex > jobTypes.size()) {
                 System.out.println("Enter a valid number for the job type.");
             }
         } catch (InputMismatchException e) {
             System.out.println("Enter a valid number for the job type.");
             scanner.nextLine();
         }
     }

     String jobType = jobTypes.get(jobTypeIndex - 1);

     String deadline = "";
     boolean validDate = false;

     while (!validDate) {
         System.out.print("Deadline (YYYY-MM-DD): ");
         deadline = scanner.next();
         if (isValidDateFormat(deadline)) {
             validDate = true;
         } else {
             System.out.println("Enter a valid date in the format YYYY-MM-DD.");
         }
     }

     //file part
     try (PrintWriter writer = new PrintWriter(new FileWriter("job_data.txt", true))) {
         writer.println(
                 companyName + "_" + position + "_" + subjectField + "_" + educationLevel + "_" + cgpa + "_" + jobDescription + "_" + salary + "_" + location + "_" + jobType + "_" + deadline
         );
     } catch (IOException e) {
         System.out.println("Error: Unable to save job information to the file.");
     }

     System.out.println("Job posted successfully!");
     backToMenu();
 }

 private static boolean isValidDateFormat(String date) {
     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     dateFormat.setLenient(false);

     try {
         dateFormat.parse(date);
         return true;
     } catch (ParseException e) {
         return false;
     }
 }
 
 private static void backToMenu() {
	 Menu menu = new Menu();
 	Scanner scanner = new Scanner(System.in);
     System.out.println("1. Apply For This Job");
     System.out.println("2. Back");
     int back = scanner.nextInt();
     if(back == 1) {
     	// Comming soon
     }
     else if(back == 2) {
    	menu.content();
     }
     else {
     	System.out.println("Invalid input");
     }
 }
}
