package jobSearchAndApplicationManagementSystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SeeAvailableJobs{
    private static final int RECORDS_PER_PAGE = 10;
    private List<String> jobData = new ArrayList<>();
    private int currentPage = 0;
    private int currentRecordIndex = 0;

    public static void main(String[] args) {
        SeeAvailableJobs jobViewer = new SeeAvailableJobs();
        jobViewer.loadJobData("job_data.txt");
        jobViewer.run();
    }

    private void loadJobData(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jobData.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void run() {
    	Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayCurrentPage();

            System.out.println("Options:");
            System.out.println("1. Next Page");
            System.out.println("2. Previous Page");
            System.out.println("3. Enter a record number");
            System.out.println("4. Back");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    nextPage();
                    break;
                case 2:
                    previousPage();
                    break;
                case 3:
                    enterRecordNumber(scanner);
                    break;
                case 4:
                    menu.content();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void displayCurrentPage() {
        int start = currentRecordIndex;
        int end = Math.min(start + RECORDS_PER_PAGE, jobData.size());

        System.out.println("Job Listings (Page " + (currentPage + 1) + "):");
        for (int i = start; i < end; i++) {
            String[] record = jobData.get(i).split("_");
            int displayNumber = currentRecordIndex + i - start + 1;
            System.out.println(displayNumber + ". " + record[0] + ", " + record[1] +
                    ", " + record[2] + ", " + record[6] + ", " + record[7]);
        }
    }

    private void nextPage() {
        int totalPages = (int) Math.ceil((double) jobData.size() / RECORDS_PER_PAGE);
        if (currentPage < totalPages - 1) {
            currentPage++;
            currentRecordIndex += RECORDS_PER_PAGE;
        }
    }

    private void previousPage() {
        if (currentPage > 0) {
            currentPage--;
            currentRecordIndex -= RECORDS_PER_PAGE;
        }
    }

    private void enterRecordNumber(Scanner scanner) {
        System.out.print("Enter a record number: ");
        int recordNumber = scanner.nextInt();
        
        // Calculate the page and index for the selected record number
        int selectedPage = (recordNumber - 1) / RECORDS_PER_PAGE;
        int selectedPageIndex = (recordNumber - 1) % RECORDS_PER_PAGE;
        
        if (selectedPage == currentPage) {
            // The selected record is on the current page
            int index = currentRecordIndex + selectedPageIndex;
            if (index < jobData.size()) {
                String jobRecord = jobData.get(index);
                String[] record = jobRecord.split("_");
                
                // Display the job details
                System.out.println("Company: " + record[0]);
                System.out.println("Position: " + record[1]);
                System.out.println("Field: " + record[2]);
                System.out.println("Education: " + record[3]);
                System.out.println("Salary: " + record[4]);
                System.out.println("Address: " + record[5]);
                System.out.println("Job Type: " + record[6]);
                System.out.println("Email: " + record[7]);
                System.out.println("Contract: " + record[8]);
                System.out.println("Deadline: " + record[9]);
                
                backToSearchMenu();
            } else {
                System.out.println("Record not found.");
                backToSearchMenu();
            }
        } else {
            // The selected record is on a different page, so navigate to that page
            currentPage = selectedPage;
            currentRecordIndex = currentPage * RECORDS_PER_PAGE;
            displayCurrentPage();
        }
    }


    private void backToSearchMenu() {
    	Scanner scanner = new Scanner(System.in);
        System.out.println("1. Search again");
        System.out.println("2. Back");
        int back = scanner.nextInt();
        if(back == 1) {
        	enterRecordNumber(scanner);
        }
        else if(back == 2) {
        	run();
        }
        else {
        	System.out.println("Invalid input");
        }
    }
}

