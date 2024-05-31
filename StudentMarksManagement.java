import java.util.Scanner;

// Class Declaration
public class StudentMarksManagement {

    // Declaration of arrays and variables to store student data and Marks
    static String[] id = new String[50]; // Array to store student IDs (Maximum 50 Students)
    static String[] name = new String[50]; // Array to store student Names (Maximum 50 Students)
    static int[] pfMarks = new int[50];// Array to store student Programming Fundamental Marks (Maximum 50 Students)
    static int[] dbmsMarks = new int[50];// Array to store student Database Management System Marks (Maximum 50 Students)
    // Variables to keep track
    static int available = 0; // keeps track of the number of students currently entered (Initially 0)
    static int deleted = 0; // keeps track of the number of student entries deleted (Initially 0)

    // Main Method: program execution starts here
    public static void main(String[] args) {
        Marks(); // Method call to initialize marks arrays
        homePage(); // Method call to display the Home Page

    }

    // Method to display the home page
    public static void homePage() {
        clearConsole(); // Method to call Clear Console Screen
        Scanner in = new Scanner(System.in);
        System.out.println(
                "---------------------------------------------------------------------------------------------------");
        System.out.println("|  \t\t\t\tWELCOME TO GDSE MARKS MANAGEMENT SYSTEM\t\t\t\t  |");
        System.out.println(
                "---------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println();

        // Menu Options
        System.out.println("[1] Add New Student\t\t\t\t[2]  Add New Student With Marks");
        System.out.println("[3] Add Marks\t\t\t\t\t[4]  Update Student Details");
        System.out.println("[5] Update Marks\t\t\t\t[6]  Delete Student");
        System.out.println("[7] Print Student Details\t\t\t[8]  Print Student Ranks");
        System.out.println("[9] Best in Programming Fundamentals\t\t[10] Best in Database Management System");

        System.out.println();
        System.out.print("Enter an option continue >  "); // Prompt user for input

        int option = in.nextInt(); // Reading User input for their chosen Option
        System.out.println();
        // Display the Welcome message and options

        // Switch statements to handle user input and perform corresponding actions
        switch (option) {
            // Case 1: Add new Student
            case 1:
                addNewStudent(); // Method call to add a new student
                break;

            // Case 2: Add new Student with Marks
            case 2:
                addNewStudentWithMarks(); // Method call to add a new student with marks
                break;

            // Case 3: Add Marks
            case 3:
                addMarks(); // Method call to add marks
                break;

            // Case 4: Update Student Details
            case 4:
                updateStudentDetails(); // Method call to Update Student Details
                break;

            // Case 5: Update Marks
            case 5:
                updateMarks(); // Method call to Update Marks
                break;

            // Case 6: Delete Student
            case 6:
                deleteStudent(); // Method Call to Delete a Student
                break;

            // Case 7: Print Student Details
            case 7:
                printStudentDetails();
                // Method call to Print Student Details
                break;

            // Case 8: Print Student Ranks
            case 8:
                printStudentRanks(); // Method call to print Student Ranks
                break;

            // Case 9: Find the best in Programming Fundamentals
            case 9:
                bestInPF(); // Method call to find the best student in Programming Fundamentals
                break;

            // Case: Find best in Database Management System
            case 10:
                bestInDBMS(); // Method call to find the best student in Database Management System
                break;

            // Default case for Invalid Input
            default:
                System.out.println("Invalid option"); // Error Message for Invalid Option
                System.out.println();

        }
    }

    // Method to search for a student by their ID
    private static int searchStudent(String key) {
        for (int i = 0; i < available; i++) { // Loop through all available students (up to 'available' index)
            if (key.equals(id[i])) { // Check if the current student's ID (stored in id[i]) matches the search key
                return i; // If found, return the index of the student in the array
            }
        }
        return -1; // If no match is found after iterating through all students, Return -1 to
                   // indicate a student not found
    }

    // Method to clear the Console Screen
    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();
            // Handle any exceptions.
        }
    }

    // Method to sort an array of integers in descending order
    private static int[] sort(int[] total) {
        int[] indexArray = new int[50];
        for (int i = 0; i < indexArray.length; i++) { // Fill indexArray with -1 to indicate empty slots initially.
            indexArray[i] = -1;
        }
        for (int j = 0; j < available; j++) {
            int maxIndex = 0;
            int max = 0;
            for (int i = 0; i < available; i++) { // Loop through the available students
                if (total[i] > max) { // Compare the current value with the maximum value
                    max = total[i]; // Store the maximum value
                    maxIndex = i; // Store the index of the maximum value
                }
            }
            total[maxIndex] = -1; // Set the maximum value to -1 to avoid re-selection
            indexArray[j] = maxIndex; // Store the index of the maximum value in the indexArray
        }
        return indexArray;
    }

    // Method to sort an array of doubles in descending order
    private static int[] sortAvg(double[] average) {

        // This method takes an array of doubles representing averages (likely student
        // scores) and returns an array of indices representing the order in which the
        // students scored, with the highest average at index 0 and so on.

        int[] tempArray = new int[100];

        for (int i = 0; i < tempArray.length; i++) {
            tempArray[i] = -1;
        }
        for (int j = 0; j < available; j++) {
            int maxIndex = 0;
            double max = Double.MIN_VALUE;
            for (int i = 0; i < available; i++) {
                if (average[i] > max) {
                    max = average[i];
                    maxIndex = i;
                }
            }
            average[maxIndex] = -1;
            tempArray[j] = maxIndex;
        }
        return tempArray;
    }

    // Method to initialize student Marks Array
    private static void Marks() {
        // Loop through the entire length of the pfMarks array
        for (int i = 0; i < pfMarks.length; i++) {
            pfMarks[i] = -1; // Set each element of pfMarks to -1, indicating uninitialized or default state
            dbmsMarks[i] = -1; // Set each element of dbmsMarks to -1, indicating uninitialized or default
                               // state
        }
    }

    // Method to determine the index to start sorting
    private static int Index3(int[] indexArray) {
        int x = 0; // Initialize a counter-variable to track the number of zeros encountered
        // Loop through the first 'available' elements in the indexArray
        for (int i = 0; i < available; i++) {
            // If the current element is zero, increment the counter x
            if (indexArray[i] == 0) {
                x++; // Increment the counter if a zero is found
            }
        }

        int count = x; // Store the value of x in count
        // If no zeros were found, return 0, otherwise return the value of count
        if (count == 0) {
            return 0;
        } else {
            // If zeros were found, decrement the count by 1 and return it
            count--;
            return count;
        }
    }

    // Method to add a new Student
    public static void addNewStudent() {
        clearConsole();
        Scanner in = new Scanner(System.in); // Scanner object to read user input
        // Display a banner for "ADD NEW STUDENT"
        System.out.println(
                "---------------------------------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\t\tADD NEW STUDENT\t\t\t\t\t\t\t|");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------");
        System.out.println();

        // Loop to input student details and check for Duplicate IDs
        while (true) {

            // Prompt for student ID
            System.out.print("Enter Student ID\t: ");
            String nowId = in.next(); // Read user input for student ID

            int index = searchStudent(nowId); // Search for an existing student with the entered ID
            // Check if student ID already exists
            if (index == -1) { // Student didn't find (index -1 indicates not found)
                id[available] = nowId; // Add student ID to the ID array at 'available' index

                // Prompt for student name
                System.out.print("Enter Student Name\t: ");
                name[available] = in.next(); // Add student name to the name array at 'available' index

                available++; // Increment 'available' to indicate a new student slot is used
                // Exit the loop after successful student addition
                break;
            } else {
                // Display an error message if student ID already exists
                System.out.println();
                System.out.println("This student already exists!");
                System.out.println();
            }
        }

        // Prompt to add another Student or return to the main menu
        System.out.println();
        System.out.print("Student has been added successfully! \n Do you want to add another Student? (Y/N):  ");
        String command = in.next(); // Read user input for choice

        // Handle user choice (add another student or return)
        switch (command) {
            case "Y":
            case "y":
                addNewStudent(); // Call addNewStudent() again to add another student
                break;

            case "N":
            case "n":
                homePage(); // Call homePage() to return to the main menu

            default:
                System.out.println();
                System.out.println("Invalid entry"); // Display an error message for invalid input (Y/N expected)
                System.out.println();
        }
    }

    // Method to add a new student with marks
    public static void addNewStudentWithMarks() {
        // Clear the Console
        clearConsole();
        Scanner in = new Scanner(System.in); // Scanner object for user input

        // Display prompt for adding a new student with marks
        System.out.println(
                "---------------------------------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tADD NEW STUDENT WITH MARK\t\t\t\t\t\t|");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------");
        System.out.println();

        // Loop to input Student Details and duplicate checking
        while (true) {
            // Prompt for Student ID
            System.out.print("Enter student ID\t: ");
            String nowId = in.next();

            // Check if Student ID already exists
            int index = searchStudent(nowId);
            if (index == -1) {
                id[available] = nowId; // Store Student ID

                // Prompt for Student name
                System.out.print("Enter Student Name\t: ");
                name[available] = in.next();
                System.out.println();

                // Loop to input marks for Programming Fundamentals
                while (true) {
                    System.out.print("Enter programming Fundamentals Marks\t: ");
                    int temppfMarks = in.nextInt();
                    // Validate marks (0 to 100)
                    if (temppfMarks > 100 || temppfMarks < 0) {
                        System.out.println();
                        System.out.println("Invalid Marks, Please Enter Valid Marks");
                        System.out.println();
                    } else {
                        pfMarks[available] = temppfMarks; // Store valid marks in pfMarks array
                        break;
                    }
                }

                // Loop to input marks for Database Management System
                while (true) {
                    System.out.print("Enter Database Management System Marks\t: ");
                    int tempdbmsMarks = in.nextInt();
                    in.nextLine(); // Consume extra newline character (optional, depends on input handling)
                    // Validate marks (0 to 100)
                    if (tempdbmsMarks > 100 || tempdbmsMarks < 0) {
                        System.out.println();
                        System.out.println("Invalid Marks, Please Enter Valid Marks");
                        System.out.println();
                    } else {
                        dbmsMarks[available] = tempdbmsMarks; // Store valid marks in dbmsMarks array
                        break;
                    }
                }

                // Increment 'available' to indicate a new student slot is used
                available++;
                break;
            } else {
                // Display a error message for existing student ID
                System.out.println();
                System.out.println("This student ID already exists!");
                System.out.println();
            }
        }

        // Prompt for adding another Student or return to the main menu
        System.out.println();
        System.out.print("Student has been added successfully! \n Do you want to add another Student? (Y/N):  ");
        String command = in.next();

        // Switch statement to handle user choice
        switch (command) {
            case "Y":
            case "y":
                addNewStudentWithMarks(); // Call addNewStudentWithMarks() again to add another student
                break;

            case "N":
            case "n":
                homePage(); // Call homePage() to return to the main menu
                break;

            default:
                System.out.println();
                System.out.println("Invalid entry"); // Display an error message for invalid input (Y/N expected)
                System.out.println();
        }
    }

    // Method to add marks for an existing Student
    public static void addMarks() {
        // Clear the Console
        clearConsole();
        Scanner in = new Scanner(System.in); // Scanner object to user input
        // Display prompt for adding marks
        System.out.println(
                "---------------------------------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\t\t\tADD MARKS\t\t\t\t\t\t|");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------");
        System.out.println();

        // Prompt for Student ID
        System.out.print("Enter student ID\t: ");
        String tempId = in.nextLine(); // Read user input for Student ID
        int index = searchStudent(tempId); // This method searches for the student ID in the student data

        // Check if Student exists
        while (true) {
            if (index == -1) { // Student didn't find (index -1 indicates not found)
                // Prompt for searching again or going back to home page
                System.out.println();
                System.out.print("Invalid Student ID! \n Do you want to Search again? (Y/N):  ");
                String command = in.nextLine(); // Read user input for choice

                // Switch statement to handle user choice
                if (command.equals("Y") || command.equals("y")) {
                    System.out.println();
                    System.out.print("Enter student ID\t: ");
                    tempId = in.nextLine(); // Read user input for Student ID
                    index = searchStudent(tempId); // Search for the student ID in the student data
                } else if (command.equals("N") || command.equals("n")) {
                    homePage();
                } else {
                    System.out.println();
                    System.out.println("Invalid entry");
                    System.out.println();
                    break;
                }
            } else {
                // Display Student Details
                System.out.println("Student Name\t\t: " + name[index]); // Student found (index points to the student's
                                                                        // data)

                // Check if marks have already been added
                if (pfMarks[index] != -1 & dbmsMarks[index] != -1) {
                    System.out.println(
                            "This Student's Marks have already been added! \n If you want to update the marks, please  use [4] to Update Marks");
                    System.out.println();
                    System.out.print("Do you want to add marks for another Student? (Y/N):  ");
                    String command = in.nextLine(); // Read user input for choice

                    // Switch statement to handle user choice
                    switch (command) {
                        case "Y":
                        case "y":
                            addMarks(); // Call this method again to add marks for another student
                            break;

                        case "N":
                        case "n":
                            homePage(); // Return to the main menu
                            break;

                        default:
                            System.out.println();
                            System.out.println("Invalid entry"); // Display an error message for invalid input (Y/N expected)
                    }
                    break;

                }

                // Loop to input marks for Programming fundamentals
                while (true) { // Loop to validate marks (0 to 100)
                    System.out.println();
                    System.out.print("Enter programming Fundamentals Marks\t: ");
                    int temppfMarks = in.nextInt();
                    if (temppfMarks > 100 || temppfMarks < 0) {
                        System.out.println();
                        System.out.println("Invalid Marks, Please Enter Valid Marks");
                    } else {
                        pfMarks[index] = temppfMarks;
                        break;
                    }
                }

                // Loop to input marks for Database Management System
                while (true) {
                    System.out.print("Enter Database Management System Marks\t: ");
                    int tempdbmsMarks = in.nextInt();
                    in.nextLine();
                    if (tempdbmsMarks > 100 || tempdbmsMarks < 0) {
                        System.out.println();
                        System.out.println("Invalid Marks, Please Enter Valid Marks");
                        System.out.println();
                    } else {
                        dbmsMarks[index] = tempdbmsMarks;
                        break;

                    }
                }

                // Prompt for adding marks for another Student
                System.out.println();
                System.out
                        .print("Marks have been added successfully! \n Do you want to add another Student? (Y/N) :  ");
                String command = in.nextLine(); // Read user input

                // Switch statement to handle user choice
                switch (command) {
                    case "Y":
                    case "y":
                        addMarks(); // Call this method again to add marks for another student
                        break;

                    case "N":
                    case "n":
                        homePage(); // Return to the main menu
                        break;

                    default:
                        System.out.println();
                        System.out.println("Invalid entry"); // Display an error message for invalid input (Y/N expected)
                        System.out.println();

                }
                break;
            }
        }
    }

    // Method to update Student Details
    public static void updateStudentDetails() {
        // Clear the Console
        clearConsole();
        Scanner in = new Scanner(System.in); // Scanner object to user input
        // Display prompt for updating Student Details
        System.out.println(
                "---------------------------------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\t\t\tUPDATE DETAILS\t\t\t\t\t\t|");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------");
        System.out.println();

        // Prompt for Student ID
        System.out.print("Enter student ID\t: ");
        String tempId = in.nextLine(); // Read user input for Student ID
        int index = searchStudent(tempId); // This method searches for the student ID in the student data

        // Check if Student exists
        while (true) {
            if (index == -1) {
                // Prompt for searching again or going back to home page
                System.out.println();
                System.out.print("Invalid Student ID! \n Do you want to Search again? (Y/N):  ");

                String command = in.nextLine(); // Read user input for choice

                // Handle user choice (search again or return)
                if (command.equals("Y") || command.equals("y")) {
                    System.out.println();
                    System.out.print("Enter student ID\t: ");
                    tempId = in.nextLine();
                    index = searchStudent(tempId);
                } else if (command.equals("N") || command.equals("n")) {
                    homePage();
                } else {
                    System.out.println();
                    System.out.println("Invalid entry");
                    System.out.println();
                    break;
                }

                // Display Student Details
            } else {
                System.out.println("Student Name\t\t: " + name[index]); // Student found (index points to the student's data)
                // Prompt for the new student name
                System.out.println();
                System.out.print("Enter the New Student Name\t: ");
                String newName = in.nextLine(); // Read user input for the new student name
                name[index] = newName; // Update the student name in the data array

                // Inform user about successful update and prompt to update another student
                System.out.println();
                System.out.print(
                        "Student details has been updated successfully!\n  Do you want to update another student Details ? (Y/N) :  ");

                String command = in.next();

                // Handle user choice (update another or return)
                switch (command) {
                    case "Y":
                    case "y":
                        updateStudentDetails(); // Call this method again to update another student
                        break;

                    case "N":
                    case "n":
                        homePage(); // Call method to return to the main menu

                    default:
                        System.out.println();
                        System.out.println("Invalid entry");
                        System.out.println();
                }
            }
        }
    }

    // Method to update Marks for an existing Student
    public static void updateMarks() {
        clearConsole();
        Scanner in = new Scanner(System.in); // Scanner object to user input
        System.out.println(
                "---------------------------------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\t\t\tUPDATE MARKS\t\t\t\t\t\t|");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------");
        System.out.println();

        // Prompt for student ID to update marks
        System.out.print("Enter Student ID  : ");
        String tempId = in.nextLine(); // Read user input for Student ID
        int index = searchStudent(tempId); // This method searches for the student ID in the student data
        // Check if student ID is valid
        if (index != -1) {
            System.out.println("Student Name\t: " + name[index]); // Student found (index points to the student's data)
            System.out.println();

            // Check if marks are already added
            if (pfMarks[index] == -1 && dbmsMarks[index] == -1) {
                // Marks not added yet
                System.out.print(
                        "This Student's marks yet to be added ! \n Do you want to update another student Marks ? (Y/N) :  ");
                String command = in.nextLine();
                // Handle user choice
                switch (command) {
                    case "Y":
                    case "y":
                        updateMarks(); // Call this method again to update another student

                    case "N":
                    case "n":
                        homePage(); // Call method to return to the main menu

                    default:
                }
            } else { // Marks already added, display current marks
                System.out.println("Programming Fundamental Marks    : " + pfMarks[index]);
                System.out.println("Database Management System Marks : " + dbmsMarks[index]);
                System.out.println();

                while (true) { // Update Programming Fundamentals marks
                    System.out.print("Enter new Programming Fundamental Marks : ");
                    int newPfMarks = in.nextInt(); // Read user input for new marks

                    if (newPfMarks > 100 || newPfMarks < 0) {
                        System.out.println("Invalid Marks, Please Enter Valid Marks");
                    } else {
                        pfMarks[index] = newPfMarks;
                        break; // Exit the loop after valid marks are entered
                    }
                }

                // Update Database Management System marks
                while (true) {
                    System.out.print("Enter new Database Management System Marks : ");
                    int newDbmsMarks = in.nextInt(); // Read user input for new marks

                    if (newDbmsMarks > 100 || newDbmsMarks < 0) {
                        System.out.println("Invalid Marks, Please Enter Valid Marks");
                    } else {
                        dbmsMarks[index] = newDbmsMarks;
                        break; // Exit the loop after valid marks are entered
                    }
                }
                in.nextLine(); // Consume leftover newline character from in.nextInt()
                // Inform user about successful update and prompt to update another student
                System.out.println();
                System.out.print(
                        "Marks have been Updated Successfully.. \n Do you want to update another student Details ? (Y/N) :  ");
                String command = in.nextLine();
                // Handle user choice (update another or return)
                switch (command) {
                    case "Y":
                    case "y":
                        updateStudentDetails(); // Call method to update student details

                    case "N":
                    case "n":
                        homePage(); // Call method to return to the main menu

                    default:
                        System.out.println("Invalid entry");
                        System.out.println();
                }
            }
        } else {
            System.out.println();
            System.out.print("Invalid Student ID! \n Do you want to Search again? (Y/N) :  ");
            String command = in.nextLine(); // Read user input for choice

            switch (command) { // Handle user choice
                case "Y":
                case "y":
                    updateStudentDetails(); // Call method to update student details

                case "N":
                case "n":
                    homePage(); // Call method to return to the main menu

                default:
                    System.out.println();
                    System.out.println("Invalid entry");
                    System.out.println();
            }
        }
    }

    // Method to delete a Student
    public static void deleteStudent() {
        clearConsole();
        Scanner in = new Scanner(System.in); // Scanner object to user input
        System.out.println(
                "---------------------------------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\t\t\tDELETE STUDENT\t\t\t\t\t\t|");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------");
        System.out.println();

        while (true) {
            System.out.print("Enter Student ID  :");
            String tempId = in.nextLine(); // Read user input for Student ID
            int index = searchStudent(tempId); // Search for the student ID in the student data
            if (index != -1) { // Check if student ID is valid
                id[index] = null; // Set the student ID to null
                name[index] = null; // Set the student name to null
                pfMarks[index] = -1; // Set the Programming Fundamentals marks to -1
                dbmsMarks[index] = -1; // Set the Database Management System marks to -1
                deleted++; // Increment the deleted counter

                System.out.println(
                        "Student has been Deleted Successfully.. \n  Do you want to delete another student? (Y/N) :  ");
                String command = in.nextLine(); // Read user input for choice

                switch (command) { // Handle user choice
                    case "Y":
                    case "y":
                        deleteStudent(); // Call this method again to delete another student

                    case "N":
                    case "n":
                        homePage(); // Call method to return to the main menu

                    default:
                        System.out.println("Invalid entry");
                        System.out.println();
                }
            } else {
                System.out.println("Invalid Student ID! \n Do you want to Search again? (Y/N) :  ");
                String command = in.nextLine(); // Read user input for choice

                switch (command) { // Handle user choice
                    case "Y":
                    case "y":
                        continue; // Continue the loop to search for another student

                    case "N":
                    case "n":
                        homePage(); // Call method to return to the main menu

                    default:
                        System.out.println();
                        System.out.println("Invalid entry");
                        System.out.println();
                }
            }
        }
    }

    // Method to print Student Details
    public static void printStudentDetails() {
        clearConsole();
        Scanner in = new Scanner(System.in); // Scanner object to user input
        System.out.println(
                "---------------------------------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\t\t\tPRINT STUDENT DETAILS\t\t\t\t\t|");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------");
        System.out.println();
        while (true) { // Loop to search for student details
            System.out.print("Enter Student ID  : "); // Prompt for Student ID
            String tempId = in.nextLine(); // Read user input for Student ID
            int index = searchStudent(tempId); // Search for the student ID in the student data

            if (index != -1) { // Check if student ID is valid
                System.out.println("Student Name : " + name[index]); // Display Student Name
                System.out.println(); // Print a blank line

                if (pfMarks[index] == -1 && dbmsMarks[index] == -1) { // Check if marks are added
                    System.out.println(
                            "Marks yet to be added ! \n Do you want to update another student Details ? (Y/N) :  ");
                    String command = in.nextLine(); // Read user input for choice

                    switch (command) { // Handle user choice
                        case "Y":
                        case "y":
                            printStudentDetails(); // Call this method again to print student details
                            break;

                        case "N":
                        case "n":
                            homePage(); // Call method to return to the main menu

                        default:
                            System.out.println("Invalid entry"); // Display an error message for invalid input (Y/N expected)
                            System.out.println();
                    }
                } else { // Marks are added, display student marks
                    int[] total = new int[100]; // Create an array to store total marks
                    for (int i = 0; i < available; i++) { // Loop through all available students
                        total[i] = (pfMarks[i] + dbmsMarks[i]); // Calculate total marks and store in the total array
                    }
                    int[] newIndex = sort(total); // Sort the total marks array
                    int rank = 0; // Get the rank

                    for (int i = 0; i < available - deleted; i++) { // Loop through the available students
                        if (newIndex[i] == index) { // Check if the current student index matches the search index
                            rank = i + 1; // Get the rank
                        }
                    }

                    System.out.println("+-----------------------------------+---------------+");
                    System.out.printf("|%-35s|%15d|\n", "Programming Fundamentals Marks", pfMarks[index]);
                    System.out.printf("|%-35s|%15d|\n", "Database Management System Marks", dbmsMarks[index]);
                    System.out.printf("|%-35s|%15d|\n", "Total Marks", pfMarks[index] + dbmsMarks[index]);
                    System.out.printf("|%-35s|%15.2f|\n", "Avg. Marks", (pfMarks[index] + dbmsMarks[index]) / 2.0);
                    System.out.printf("|%-35s|%15s|\n", "Rank", rank);
                    System.out.println("+-----------------------------------+---------------+");
                    System.out.println();

                    System.out.print("Do You want to search another student details ? (Y/N) :");
                    String command = in.nextLine(); // Read user input for choice

                    switch (command) { // Handle user choice
                        case "Y":
                        case "y":
                            printStudentDetails(); // Call this method again to print student details

                        case "N":
                        case "n":
                            homePage(); // Call method to return to the main menu

                        default:
                            System.out.println("Invalid entry"); // Display an error message for invalid input (Y/N expected)
                            System.out.println();
                    }
                }
            } else {
                System.out.println();
                System.out.print("Invalid student ID ... \n Do you want to search again ? (Y/N) :  ");
                String command = in.nextLine(); // Read user input for choice

                switch (command) { // Handle user choice
                    case "Y":
                    case "y":
                        continue; // Continue the loop to search for another student

                    case "N":
                    case "n":
                        homePage(); // Call method to return to the main menu

                    default:
                        System.out.println();
                        System.out.println("Invalid entry");
                        System.out.println();
                }
            }
        }
    }

    // Method to print Student Ranks
    public static void printStudentRanks() {
        clearConsole();
        Scanner in = new Scanner(System.in); // Scanner for user input
        System.out.println(
                "---------------------------------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\t\t\tPRINT STUDENT RANKS\t\t\t\t\t|");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------");

        System.out.println();
        int[] total = new int[100]; // Create an array to store total marks
        for (int i = 0; i < available; i++) { // Loop through all available students
            total[i] = (pfMarks[i] + dbmsMarks[i]); // Calculate total marks and store in the total array
        }
        int[] Index2 = sort(total); // Sort the total marks array

        double[] average = new double[100]; // Calculate average marks
        for (int i = 0; i < available; i++) { // Loop through all available students
            average[i] = (pfMarks[i] + dbmsMarks[i]) / 2.0; // Calculate average marks
        }

        double[] avg = new double[100]; // Calculate average marks
        for (int i = 0; i < available; i++) { // Loop through all available students
            avg[i] = average[i]; // Calculate average marks
        }
        int[] indexArray = sortAvg(average); // Sort the average array
        int numbers = Index3(indexArray); // Get the number of zeros in the indexArray

        System.out.println("+-------+-------+---------------+--------------+-----------+");
        System.out.format("|%-7s|%-7s|%-15s|%-14s|%-11s|\n", "Rank", "ID", "Name", "Total Marks", "Avg. Marks");
        System.out.println("+-------+-------+---------------+--------------+-----------+");

        for (int i = 0; i < available - numbers; i++) {
            if (pfMarks[indexArray[i]] != -1 | dbmsMarks[indexArray[i]] != -1) { // Check if marks are added
                System.out.format("|%-7d|%-7s|%-15s|%14d|%11.2f|\n", (i + 1), id[indexArray[i]], name[indexArray[i]],
                        (pfMarks[indexArray[i]] + dbmsMarks[indexArray[i]]), avg[indexArray[i]]); // Print student
                                                                                                  // details
            }
        }
        System.out.println("+-------+-------+---------------+--------------+-----------+");

        System.out.println();
        System.out.print("Do you want to go back to the Main Menu ? (Y/N) :  ");
        String command = in.nextLine(); // Read user input for choice

        switch (command) {
            case "Y":
            case "y":
                homePage(); // Return to the main menu

            case "N":
            case "n":
                printStudentDetails(); // print the student details

            default:
                System.out.println();
                System.out.println("Invalid entry");
                System.out.println();
        }
    }

    // Method to find the best student in Programming Fundamentals
    public static void bestInPF() {
        clearConsole();
        Scanner in = new Scanner(System.in); // Scanner object to read user input
        System.out.println(
                "---------------------------------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\t\tBEST IN PROGRAMMING FUNDAMENTALS\t\t\t\t|");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------");
        System.out.println();

        // Create a temporary array to hold Programming Fundamentals (PF) marks (to
        // avoid modifying original data)
        int[] pf = new int[100];
        for (int i = 0; i < available; i++) {

            // Copy PF marks from the original array to the temporary array
            pf[i] = pfMarks[i];
        }

        int[] arrayIndex = sort(pf); // Sort the temporary array

        System.out.println("+-------+---------------+-----------+-----------+");
        System.out.format("|%-7s|%-15s|%-11s|%-11s|\n", "ID", "Name", "PF Marks", "DBMS Marks");
        System.out.println("+-------+---------------+-----------+-----------+");

        // Print student details in descending order of PF marks
        for (int i = 0; i < available - deleted; i++) { // Loop through all available students
            System.out.format("|%-7s|%-15s|%-11d|%-11s|\n", id[arrayIndex[i]], name[arrayIndex[i]],
                    pfMarks[arrayIndex[i]], dbmsMarks[arrayIndex[i]]); // Print student details
        }
        System.out.println("+-------+---------------+-----------+-----------+");

        System.out.println();
        System.out.print("Do you want to go back to the Main Menu ? (Y/N) :  ");
        String command = in.nextLine();

        // Handle user choice (main menu or print all students)
        switch (command) {
            case "Y":
            case "y":
                homePage(); // Call method to return home page
            case "N":
            case "n":
                printStudentDetails(); // Call method to print all student details
            default:
                System.out.println();
                System.out.println("Invalid entry");
                System.out.println();
        }
    }

    // Method to find the best student in Database Management System
    public static void bestInDBMS() {
        clearConsole();
        Scanner in = new Scanner(System.in); // Scanner object to read user input
        System.out.println(
                "---------------------------------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\t\tBEST IN DATABASE MANAGEMENT SYSTEM\t\t\t\t|");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------");
        System.out.println();
        // Create a temporary array to hold DBMS marks (to avoid modifying original
        // data)
        int[] dbms = new int[100];
        for (int i = 0; i < available; i++) {
            dbms[i] = dbmsMarks[i]; // Copy DBMS marks from the original array to the temporary array
        }
        int[] arrayIndex = sort(dbms); // Sort the temporary array

        System.out.println("+-------+---------------+-----------+-----------+");
        System.out.format("|%-7s|%-15s|%-11s|%-11s|\n", "ID", "Name", "DBMS Marks", "PF Marks");
        System.out.println("+-------+---------------+-----------+-----------+");
        // Print student details in descending order of DBMS marks
        for (int i = 0; i < available - deleted; i++) {
            System.out.format("|%-7s|%-15s|%-11d|%-11s|\n", id[arrayIndex[i]], name[arrayIndex[i]],
                    dbmsMarks[arrayIndex[i]], pfMarks[arrayIndex[i]]); // Print student details
        }
        System.out.println("+-------+---------------+-----------+-----------+");

        // Prompt user to return to the main menu or print all student details
        System.out.println();
        System.out.print("Do you want to go back to the Main Menu ? (Y/N) :  ");
        String command = in.next(); // Read user input for choice

        switch (command) { // Handle user choice
            case "Y":
            case "y":
                homePage(); // Return to the main menu
            case "N":
            case "n":
                printStudentDetails(); // Call method to print all student details
            default:
                System.out.println();
                System.out.println("Invalid entry");
                System.out.println();
        }
    }

}