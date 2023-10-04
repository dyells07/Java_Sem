import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.UUID;

public class registerVoter {

    public static void main(String[] args) {
        int userIdCounter = readLastUserIdFromCSV();
        Scanner scanner = new Scanner(System.in);

        boolean continueRegistration = true;

        while (continueRegistration) {
            System.out.println("Options:");
            System.out.println("1. Register a voter");
            System.out.println("2. Edit voter information");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = getValidChoice(scanner, 1, 3);

            switch (choice) {
                case 1:
                    userIdCounter = registerVoter(userIdCounter, scanner);
                    break;
                case 2:
                    editVoterInfo("registration.csv", userIdCounter, scanner);
                    break;
                case 3:
                    continueRegistration = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        System.out.println("Thank you for using the Voter Registration System!");
        scanner.close();
    }

    private static int getValidChoice(Scanner scanner, int minChoice, int maxChoice) {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= minChoice && choice <= maxChoice) {
                    return choice;
                } else {
                    System.out.println("Invalid choice. Please enter a valid option (" + minChoice + "-" + maxChoice + ").");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number (" + minChoice + "-" + maxChoice + ").");
            }
        }
    }

    private static int registerVoter(int userIdCounter, Scanner scanner) {
        int userId = ++userIdCounter;
        String guid = UUID.randomUUID().toString();
        String uniqueIdentifier = guid + "_" + userId;

        System.out.println("Welcome to the Voter Registration System!");

        System.out.print("Enter Voter's Name: ");
        String name = scanner.nextLine();

        int age;
        do {
            System.out.print("Enter Voter's Age: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Age must be a positive integer.");
                System.out.print("Enter Voter's Age: ");
                scanner.next();
            }
            age = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            if (age <= 0 || age < 19) {
                System.out.println("Invalid input. Age must be a positive integer and above 18.");
            }
        } while (age <= 0 || age < 19);

        System.out.print("Enter Voter's District: ");
        String district = scanner.nextLine();

        System.out.print("Enter Voter's Electorate: ");
        String electorate = scanner.nextLine();

        LocalDateTime registrationDateTime = LocalDateTime.now();
        String registrationTimestamp = registrationDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String fileName = "registration.csv";

        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            writer.write(uniqueIdentifier + "," + name + "," + age + "," + district + "," + electorate + ","
                    + registrationTimestamp + "\n");
            System.out.println("Registration successful! Voter's unique identifier is: " + uniqueIdentifier);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("An error occurred while saving the user registration information.");
        }

        return userIdCounter;
    }

    private static void editVoterInfo(String fileName, int userIdCounter, Scanner scanner) {
        System.out.print("Enter the userId of the voter you want to edit: ");
        int userIdToEdit = getValidUserId(scanner);

        String tempFileName = "temp.csv";

        try (FileWriter fileWriter = new FileWriter(tempFileName);
             BufferedWriter writer = new BufferedWriter(fileWriter);
             BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line;
            boolean voterFound = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String uniqueIdentifier = parts[0];

                String[] idParts = uniqueIdentifier.split("_");
                String guid = idParts[0];
                int userId = Integer.parseInt(idParts[1]);

                if (userId == userIdToEdit) {
                    voterFound = true;
                    System.out.println("Current Voter Information:");
                    System.out.println("Name: " + parts[1]);
                    System.out.println("Age: " + parts[2]);
                    System.out.println("District: " + parts[3]);
                    System.out.println("Electorate: " + parts[4]);

                    System.out.println("Options:");
                    System.out.println("1. View Information");
                    System.out.println("2. Edit Information");
                    System.out.println("3. Cancel");

                    int editChoice = getValidChoice(scanner, 1, 3);

                    if (editChoice == 1) {
                       
                    } else if (editChoice == 2) {
                       
                      
String existingName = parts[1];
int existingAge = Integer.parseInt(parts[2]);
String existingDistrict = parts[3];
String existingElectorate = parts[4];

System.out.print("Enter new name (or press Enter to keep existing): ");
String newNameInput = scanner.nextLine();
String newName = newNameInput.isEmpty() ? existingName : newNameInput;

if (newNameInput.isEmpty()) {
    System.out.println("Existing Name: " + existingName);
} else {
    System.out.println("New Name: " + newName);
}

System.out.print("Enter new age (or press Enter to keep existing): ");
String newAgeInput = scanner.nextLine();
int newAge;
if (newAgeInput.isEmpty()) {
    newAge = existingAge; // Keep existing age
    System.out.println("Existing Age: " + existingAge);
} else {
    newAge = Integer.parseInt(newAgeInput);
    System.out.println("New Age: " + newAge);
}

System.out.print("Enter new district (or press Enter to keep existing): ");
String newDistrictInput = scanner.nextLine();
String newDistrict = newDistrictInput.isEmpty() ? existingDistrict : newDistrictInput;

if (newDistrictInput.isEmpty()) {
    System.out.println("Existing District: " + existingDistrict);
} else {
    System.out.println("New District: " + newDistrict);
}

System.out.print("Enter new electorate (or press Enter to keep existing): ");
String newElectorateInput = scanner.nextLine();
String newElectorate = newElectorateInput.isEmpty() ? existingElectorate : newElectorateInput;

if (newElectorateInput.isEmpty()) {
    System.out.println("Existing Electorate: " + existingElectorate);
} else {
    System.out.println("New Electorate: " + newElectorate);
}




                        LocalDateTime editDateTime = LocalDateTime.now();
                        String editTimestamp = editDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                        line = guid + "_" + userId + "," + newName + "," + newAge + "," + newDistrict + "," + newElectorate + ","
                                + editTimestamp;
                        System.out.println("Voter information updated successfully.");
                    } else {
                       
                    }
                }
                writer.write(line + "\n");
            }

            if (!voterFound) {
                System.out.println("Voter with the specified userId not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("An error occurred while editing the voter information.");
        }

        File tempFile = new File(tempFileName);
        File originalFile = new File(fileName);
        if (tempFile.renameTo(originalFile)) {
            System.out.println("File updated successfully.");
        } else {
            System.err.println("Could not rename the file.");
        }

        if (originalFile.delete() && tempFile.renameTo(originalFile)) {
                    System.out.println("File updated successfully.");
            } else {
             System.err.println("Could not rename the file.");
            }
    }

    private static int getValidUserId(Scanner scanner) {
        while (true) {
            try {
                int userId = Integer.parseInt(scanner.nextLine());
                if (userId > 0) {
                    return userId;
                } else {
                    System.out.println("Invalid userId. Please enter a positive integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid userId.");
            }
        }
    }

    private static int getValidAge(String ageInput) {
        while (true) {
            try {
                int age = Integer.parseInt(ageInput);
                if (age > 0 && age >= 19) {
                    return age;
                } else {
                    System.out.println("Invalid age. Age must be a positive integer and above 18.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid age.");
            }
        }
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
