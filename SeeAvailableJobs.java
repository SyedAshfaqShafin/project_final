package jobSearchAndApplicationManagementSystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SeeAvailableJobs {
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
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayCurrentPage();

            System.out.println("Options:");
            System.out.println("1. Next Page");
            System.out.println("2. Previous Page");
            System.out.println("3. Enter a record number");
            System.out.println("4. Exit");

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
                    running = false;
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
            String[] record = jobData.get(i).split(",");
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
        if (recordNumber >= currentRecordIndex + 1 && recordNumber <= currentRecordIndex + RECORDS_PER_PAGE) {
            int index = recordNumber - currentRecordIndex - 1;
            if (index < jobData.size()) {
                System.out.println("Record: " + jobData.get(index));
                backToSearchMenu();
            } else {
                System.out.println("Record not found.");
                backToSearchMenu();
            }
        } else {
        	System.out.println("Invalid record number.");
        	backToSearchMenu();
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

