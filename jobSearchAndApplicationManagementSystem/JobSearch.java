package jobSearchAndApplicationManagementSystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JobSearch{
    public static void main(String args[]){
        String filePath = "job_records.txt";
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();

        List<String>records = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = reader.readLine())!=null){
                records.add(line);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        while(true){
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            if(choice==5)
            {
                menu.content();
            }
            
            else if(choice==2)
            {
                displayCategoryMenu();
                int categoryChoice = scanner.nextInt();
                scanner.nextLine();

                String category = getCategoryByChoice(categoryChoice);
                
                if(category!=null)
                {
                    searchAndDisplayResultsByCategory(records, category);
                }
                
                else
                {
                    System.out.println("Invalid category choice.");
                }
            }
            
            else
            {
                System.out.print("Enter your search term: ");
                String searchTerm = scanner.nextLine().trim();
                searchAndDisplayResults(records, choice, searchTerm);
            }
        }
    }

    public static void displayMenu(){
        System.out.println("\nSelect a search option:");
        System.out.println("1. Job Company");
        System.out.println("2. Job Category");
        System.out.println("3. Salary");
        System.out.println("4. Location");
        System.out.println("5. Back");
        System.out.print("Enter your choice: ");
    }

    public static void displayCategoryMenu(){
        System.out.println("\nSelect a job category:");
        System.out.println("1. Software Engineer");
        System.out.println("2. Data Analyst");
        System.out.println("3. Marketing Manager");
        System.out.print("Enter your choice: ");
    }

    public static String getCategoryByChoice(int choice){
        switch(choice)
        {
            case 1:
                return "Software Engineer";
            case 2:
                return "Data Analyst";
            case 3:
                return "Marketing Manager";
            default:
                return null;
        }
    }

    public static void searchAndDisplayResults(List<String>records,int choice,String searchTerm){
        boolean recordFound = false;

        for(String record:records){
            String[] parts = record.split("_");
            if(parts.length==12)
            {
                if((choice==1 && parts[0].equalsIgnoreCase(searchTerm)) ||
                        (choice==2 && parts[1].equalsIgnoreCase(searchTerm)) ||
                        (choice==3 && Double.parseDouble(parts[6]) >= Double.parseDouble(searchTerm)) ||
                        (choice==4 && parts[8].toLowerCase().contains(searchTerm.toLowerCase())))
                {
                    
                    System.out.println(record);
                    recordFound = true;
                }
            }
        }
        System.out.println("Job record found.");

        if(!recordFound) 
        {
            System.out.println("No matching job records found.");
        }
    }

    public static void searchAndDisplayResultsByCategory(List<String>records,String category){
        boolean recordFound = false;

        for(String record:records){
            String[] parts = record.split("_");
            if(parts.length == 12 && parts[1].equalsIgnoreCase(category)) 
            {
                
                System.out.println(record);
                recordFound = true;
            }
        }
        System.out.println("Job record found.");

        if(!recordFound)
        {
            System.out.println("No matching job records found in the " + category + " category.");
        }
    }
}

