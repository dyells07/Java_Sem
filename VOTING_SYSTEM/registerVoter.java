import java.io.*;
import java.util.Scanner;
import java.util.UUID;

public class registerVoter {
    public static void main(String[] args) {
        int userIdCounter = readLastUserIdFromCSV();

        boolean continueRegistration = true;
        Scanner scanner = new Scanner(System.in);

        while (continueRegistration) {
            int userId = ++userIdCounter;
            String guid = UUID.randomUUID().toString();
            String uniqueIdentifier = guid + "_" + userId;

            System.out.println("Welcome to the Voter Registration System!");

            // Input for voter details
            System.out.print("Enter your Name: ");
            String name = scanner.nextLine();

            int age;
            do {
                System.out.print("Enter your Age: ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Age must be a positive integer.");
                    System.out.print("Enter your Age: ");
                    scanner.next();
                }
                age = scanner.nextInt();
                if (age <= 0 || age < 19) {
                    System.out.println("Invalid input. Age must be a positive integer and above 18.");
                }
            } while (age <= 0 || age < 19);

            scanner.nextLine(); // Consume the newline character

            System.out.print("Enter your District: ");
            String district = scanner.nextLine();

            System.out.print("Enter your Electorate: ");
            String electorate = scanner.nextLine();

            // Write voter details to CSV file
            String fileName = "registration.csv";
            try (FileWriter fileWriter = new FileWriter(fileName, true);
                    BufferedWriter writer = new BufferedWriter(fileWriter)) {
                writer.write(uniqueIdentifier + "," + name + "," + age + "," + district + "," + electorate + "\n");
                System.out.println("Registration successful! Your unique identifier is: " + uniqueIdentifier);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("An error occurred while saving the user registration information.");
            }

            // Prompt user to register another voter or exit
            System.out.print("Press Enter to register another voter or type 'exit' to quit: ");
            String choice = scanner.nextLine().toLowerCase().trim();
            continueRegistration = choice.isEmpty() || choice.equals("exit");
        }

        System.out.println("Thank you for using the Voter Registration System!");
        scanner.close();
    }

    private static int readLastUserIdFromCSV() {
        int lastUserId = 0;
        String fileName = "registration.csv";
        File file = new File(fileName);
        if (file.exists()) {
            try (Scanner fileScanner = new Scanner(file)) {
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    String[] parts = line.split(",");
                    String uniqueIdentifier = parts[0];
                    String[] idParts = uniqueIdentifier.split("_");
                    int userId = Integer.parseInt(idParts[1]);
                    if (userId > lastUserId) {
                        lastUserId = userId;
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return lastUserId;
    }
}
