package LinkedList;
import java.util.Scanner;

public class StudentManager {
    private static LinkedList students = new LinkedList();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Student Manager ---");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Display Students");
            System.out.println("5. Sort Students by Age (Bubble Sort)");
            System.out.println("6. Sort Students by Marks (Insertion Sort)");
            System.out.println("7. Search Students by Name (Linear Search)");
            System.out.println("8. Search Students by Age (Binary Search)");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");

            try {
                int option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        updateStudent();
                        break;
                    case 3:
                        deleteStudent();
                        break;
                    case 4:
                        students.display();
                        break;
                    case 5:
                        students.bubbleSortByAge();
                        System.out.println("Students sorted by Age successfully.");
                        break;
                    case 6:
                        students.insertionSortByMarks();
                        System.out.println("Students sorted by Marks successfully.");
                        break;
                    case 7:
                        searchStudentsByName();
                        break;
                    case 8:
                        searchStudentsByAge();
                        break;
                    case 9:
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private static void addStudent() {
        String name;
        double marks;
        int age;

        while (true) {
            try {
                System.out.print("Enter Student Name: ");
                name = scanner.nextLine();
                if (name.isEmpty() || !name.matches("[a-zA-Z ]+")) {
                    throw new IllegalArgumentException("Name must only contain letters and cannot be empty.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An error occurred while entering the Student Name: " + e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Enter Student Marks: ");
                marks = Double.parseDouble(scanner.nextLine());
                if (marks < 0 || marks > 10) {
                    throw new IllegalArgumentException("Marks must be between 0 and 10.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for Student Marks.");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An error occurred while entering the Student Marks: " + e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Enter Student Age: ");
                age = Integer.parseInt(scanner.nextLine());
                if (age < 18 || age > 40) {
                    throw new IllegalArgumentException("Age must be between 18 and 40.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for Student Age.");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An error occurred while entering the Student Age: " + e.getMessage());
            }
        }

        students.add(name, marks, age);
        System.out.println("Student added successfully.");
    }

    private static void updateStudent() {
        int id;
        while (true) {
            try {
                System.out.print("Enter Student ID to update: ");
                id = Integer.parseInt(scanner.nextLine());
                if (!students.exists(id)) {
                    System.out.println("Student ID not found.");
                    return;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for Student ID.");
            } catch (Exception e) {
                System.out.println("An error occurred while entering the Student ID: " + e.getMessage());
            }
        }

        String name;
        double marks;
        int age;

        while (true) {
            try {
                System.out.print("Enter new name: ");
                name = scanner.nextLine();
                if (name.isEmpty() || !name.matches("[a-zA-Z ]+")) {
                    throw new IllegalArgumentException("Name must only contain letters and cannot be empty.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An error occurred while entering the new name: " + e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Enter new marks (0-10): ");
                marks = Double.parseDouble(scanner.nextLine());
                if (marks < 0 || marks > 10) {
                    throw new IllegalArgumentException("Marks must be between 0 and 10.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for Student Marks.");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An error occurred while entering the new marks: " + e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Enter new age: ");
                age = Integer.parseInt(scanner.nextLine());
                if (age < 18 || age > 40) {
                    throw new IllegalArgumentException("Age must be between 18 and 40.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for Student Age.");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An error occurred while entering the new age: " + e.getMessage());
            }
        }

        boolean updated = students.update(id, name, marks, age);
        if (updated) {
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student ID not found.");
        }
    }

    private static void deleteStudent() {
        try {
            System.out.print("Enter Student ID to delete: ");
            int id = Integer.parseInt(scanner.nextLine());

            boolean deleted = students.delete(id);
            if (deleted) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student ID not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid Student ID.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void searchStudentsByName() {
        try {
            System.out.print("Enter name to search: ");
            String name = scanner.nextLine();
            if (name.isEmpty() || !name.matches("[a-zA-Z ]+")) {
                throw new IllegalArgumentException("Name must only contain letters and cannot be empty.");
            }
            students.searchByName(name);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input. " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void searchStudentsByAge() {
        try {
            System.out.print("Enter age to search: ");
            int age = Integer.parseInt(scanner.nextLine());
            if (age < 18 || age > 40) {
                throw new IllegalArgumentException("Age must be between 18 and 40.");
            }
            Student result = students.binarySearchByAge(age);
            if (result != null) {
                System.out.println(result);
            } else {
                System.out.println("No students found with the age: " + age);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer for Age.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input. " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

}
