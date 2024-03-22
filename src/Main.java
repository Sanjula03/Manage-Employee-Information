import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final String HR_MANAGER_FILE = "hr_manager.txt";
    private static final String EMPLOYEE_FILE = "employees.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creating instances of Admin and HRManager
        Admin admin = new Admin();
        HRManager hrManager = new HRManager();

        // Load data from files
        loadHRManagerData(admin);
        loadHRManagerData(hrManager);

        // Main menu
        boolean running = true;
        while (running) {
            try {
                System.out.println("\nMain Menu:");
                System.out.println("1. Admin ");
                System.out.println("2. HR Manager ");
                System.out.println("3. Exit");
                System.out.println("Enter your choice:");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1 -> adminTasks(admin, scanner);
                    case 2 -> hrManagerTasks(hrManager, scanner);
                    case 3 -> {
                        saveHRManagerData(admin);
                        saveHRManagerData(hrManager);
                        running = false;
                    }
                    default -> System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume invalid input
            }
        }

        scanner.close();
    }

    private static void adminTasks(Admin admin, Scanner scanner) {
        // Admin tasks
        boolean running = true;
        while (running) {
            try {
                System.out.println("\nAdmin Tasks:");
                System.out.println("1. Create a new HR Manager account");
                System.out.println("2. Create a new HR Assistant account");
                System.out.println("3. Back to Main Menu");
                System.out.println("Enter your choice:");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1 -> {
                        System.out.println("Creating a new HR Manager account:");
                        System.out.println("Enter HR Manager username:");
                        String hrManagerUsername = scanner.nextLine();
                        System.out.println("Enter HR Manager password:");
                        String hrManagerPassword = scanner.nextLine();
                        admin.createHRManagerAccount();
                        System.out.println("HR Manager account created successfully.");
                    }
                    case 2 -> {
                        System.out.println("Creating a new HR Assistant account:");
                        System.out.println("Enter HR Assistant username:");
                        String hrAssistantUsername = scanner.nextLine();
                        System.out.println("Enter HR Assistant password:");
                        String hrAssistantPassword = scanner.nextLine();
                        admin.createHRAssistantAccount(hrAssistantUsername, hrAssistantPassword);
                        System.out.println("HR Assistant account created successfully.");
                    }
                    case 3 -> running = false;
                    default -> System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume invalid input
            }
        }
    }

    private static void hrManagerTasks(HRManager hrManager, Scanner scanner) {
        // HR Manager tasks
        boolean running = true;
        while (running) {
            try {
                System.out.println("\nHR Manager Tasks:");
                System.out.println("1. Add new department and designation");
                System.out.println("2. Add new employee");
                System.out.println("3. Search employee");
                System.out.println("4. Back to Main Menu");
                System.out.println("Enter your choice:");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1 -> {
                        System.out.println("Enter department name:");
                        String departmentName = scanner.nextLine();
                        hrManager.addDepartment(departmentName);
                        System.out.println("Enter designation:");
                        String designation = scanner.nextLine();
                        hrManager.addDesignation(departmentName, designation);
                        System.out.println("Department and designation added successfully.");
                    }
                    case 2 -> {
                        System.out.println("Enter employee name:");
                        String employeeName = scanner.nextLine();
                        System.out.println("Enter employee department:");
                        String employeeDepartment = scanner.nextLine();
                        System.out.println("Enter employee designation:");
                        String employeeDesignation = scanner.nextLine();
                        System.out.println("Enter employee EPF number:");
                        String employeeEPFNumber = scanner.nextLine();
                        Employee newEmployee = new Employee(employeeName, employeeDepartment, employeeDesignation, employeeEPFNumber);
                        hrManager.addEmployee();
                        System.out.println("Employee added successfully.");
                    }
                    case 3 -> {
                        System.out.println("Search Employee:");
                        System.out.println("1. Search by name");
                        System.out.println("2. Search by department");
                        System.out.println("3. Search by designation");
                        System.out.println("4. Search by EPF number");
                        System.out.println("5. Back");
                        System.out.println("Enter your choice:");
                        int searchChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character
                        System.out.println("Enter search criteria:");
                        String criteria = scanner.nextLine();
                        Employee foundEmployee = null;
                        switch (searchChoice) {
                            case 1:
                                foundEmployee = hrManager.searchEmployee("name", criteria);
                                break;
                            case 2:
                                foundEmployee = hrManager.searchEmployee("department", criteria);
                                break;
                            case 3:
                                foundEmployee = hrManager.searchEmployee("designation", criteria);
                                break;
                            case 4:
                                foundEmployee = hrManager.searchEmployee("epfnumber", criteria);
                                break;
                            case 5:
                                break;
                            default:
                                System.out.println("Invalid choice.");
                        }
                        if (foundEmployee != null) {
                            System.out.println("Employee found: " + foundEmployee.getName());
                        } else {
                            System.out.println("Employee not found.");
                        }
                    }
                    case 4 -> running = false;
                    default -> System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume invalid input
            }
        }
    }

    private static void saveHRManagerData(Object obj) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(obj instanceof Admin ? HR_MANAGER_FILE : EMPLOYEE_FILE))) {
            outputStream.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadHRManagerData(Object obj) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(obj instanceof Admin ? HR_MANAGER_FILE : EMPLOYEE_FILE))) {
            Object loadedData = inputStream.readObject();
            if (obj instanceof Admin admin && loadedData instanceof Admin) {
                admin.setHrManagers(((Admin) loadedData).getHrManagers());
                admin.setHrAssistants(((Admin) loadedData).getHrAssistants());
            } else if (obj instanceof HRManager hrManager && loadedData instanceof HRManager) {
                hrManager.setDepartments(((HRManager) loadedData).getDepartments());
            }
        } catch (IOException | ClassNotFoundException e) {
            // File not found or cannot read, ignore and proceed with empty data
        }
    }


}
