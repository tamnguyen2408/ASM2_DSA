package Array;
import java.util.Scanner;

public class StudentManager {
    private static Student[] students = new Student[0];
    private static Scanner scanner = new Scanner(System.in);
    private static int nextId = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Student Manager ---");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Display Students");
            System.out.println("5. Sort Students by Marks (Bubble Sort)");
            System.out.println("6. Sort Students by Age (Selection Sort)");
            System.out.println("7. Search Students by Name (Linear Search)");
            System.out.println("8. Search Students by Marks (Binary Search)");
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
                        displayStudents();
                        break;
                    case 5:
                        sortStudentsByMarks();
                        break;
                    case 6:
                        sortStudentsByAge();
                        break;
                    case 7:
                        searchStudentsByName();
                        break;
                    case 8:
                        searchStudentsByMarks();
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
        String name = "";
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

        int age = 0;
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

        double marks = 0.0;
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

        int id = nextId++;
        Student newStudent = new Student(id, name, age, marks);
        Student[] newStudentsArray = new Student[students.length + 1];
        for (int i = 0; i < students.length; i++) {
            newStudentsArray[i] = students[i];
        }
        newStudentsArray[students.length] = newStudent;
        students = newStudentsArray;

        System.out.println("Student added successfully.");
    }

    private static void updateStudent() {
        int id = 0;
        while (true) {
            try {
                System.out.print("Enter Student ID to update: ");
                id = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for Student ID.");
            } catch (Exception e) {
                System.out.println("An error occurred while entering the Student ID: " + e.getMessage());
            }
        }

        for (int i = 0; i < students.length; i++) {
            if (students[i].id == id) {
                String name = "";
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

                double marks = 0.0;
                while (true) {
                    try {
                        System.out.print("Enter new marks: ");
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

                int age = 0;
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

                students[i].name = name;
                students[i].marks = marks;
                students[i].age = age;

                System.out.println("Student updated successfully.");
                return;
            }
        }

        System.out.println("Student ID not found.");
    }

    private static void deleteStudent() {
        try {
            System.out.print("Enter Student ID to delete: ");
            int id = Integer.parseInt(scanner.nextLine());

            int index = -1;
            for (int i = 0; i < students.length; i++) {
                if (students[i].id == id) {
                    index = i;
                    break;
                }
            }

            if (index != -1) {
                Student[] newStudentsArray = new Student[students.length - 1];
                for (int i = 0, j = 0; i < students.length; i++) {
                    if (i != index) {
                        newStudentsArray[j++] = students[i];
                    }
                }
                students = newStudentsArray;

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

    private static void displayStudents() {
        if (students.length == 0) {
            System.out.println("No students to display.");
            return;
        }

        System.out.println("\nStudent List:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void sortStudentsByMarks() {
        long startTime = System.currentTimeMillis(); // Ghi lại thời điểm bắt đầu

        try {
            for (int i = 0; i < students.length - 1; i++) {
                for (int j = 0; j < students.length - 1 - i; j++) {
                    if (students[j].marks > students[j + 1].marks) { // Change '<' to '>'
                        Student temp = students[j];
                        students[j] = students[j + 1];
                        students[j + 1] = temp;
                    }
                }
            }
            System.out.println("Students sorted by marks successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred while sorting the students: " + e.getMessage());
        }
        long endTime = System.currentTimeMillis(); // Ghi lại thời điểm kết thúc
        System.out.println("Bubble Sort by Marks took " + (endTime - startTime) + " milliseconds.");
    }


    private static void sortStudentsByAge() {
        long startTime = System.currentTimeMillis(); // Ghi lại thời điểm bắt đầu

        try {
            for (int i = 0; i < students.length - 1; i++) {
                // Find the index of the minimum age in the unsorted part
                int minIndex = i;
                for (int j = i + 1; j < students.length; j++) {
                    if (students[j].age < students[minIndex].age) {
                        minIndex = j;
                    }
                }

                // Swap the found minimum element with the first element of the unsorted part
                Student temp = students[minIndex];
                students[minIndex] = students[i];
                students[i] = temp;
            }
            System.out.println("Students sorted by age successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred while sorting the students: " + e.getMessage());
        }
        long endTime = System.currentTimeMillis(); // Ghi lại thời điểm kết thúc
        System.out.println("Selection Sort by Age took " + (endTime - startTime) + " milliseconds.");
    }


    private static void searchStudentsByName() {
        long startTime = System.currentTimeMillis(); // Ghi lại thời điểm bắt đầu

        try {
            System.out.print("Enter name to search: ");
            String name = scanner.nextLine();
            if (name.isEmpty() || !name.matches("[a-zA-Z ]+")) {
                throw new IllegalArgumentException("Name must only contain letters and cannot be empty.");
            }

            boolean found = false;
            for (Student student : students) {
                if (student.name.equalsIgnoreCase(name)) {
                    System.out.println(student);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No students found with the name: " + name);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input. " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        long endTime = System.currentTimeMillis(); // Ghi lại thời điểm kết thúc
        System.out.println("Linear Search by Age took " + (endTime - startTime) + " milliseconds.");
    }

    private static void searchStudentsByMarks() {
        long startTime = System.currentTimeMillis(); // Ghi lại thời điểm bắt đầu
        try {
            System.out.print("Enter marks to search: ");
            double marks = Double.parseDouble(scanner.nextLine());
            if (marks < 0 || marks > 10) {  // Correct range check
                throw new IllegalArgumentException("Marks must be between 0 and 10.");
            }

            sortStudentsByMarks();  // Ensure the students array is sorted by marks

            int index = binarySearch(students, marks);
            if (index >= 0) {
                System.out.println("Student found: " + students[index]);
            } else {
                System.out.println("No students found with the marks: " + marks);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number for marks.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input. " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        long endTime = System.currentTimeMillis(); // Ghi lại thời điểm kết thúc
        System.out.println("Binary Search by Age took " + (endTime - startTime) + " milliseconds.");
    }


    private static int binarySearch(Student[] students, double marks) {
        int left = 0;
        int right = students.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (students[mid].marks == marks) {
                return mid;
            } else if (students[mid].marks < marks) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}

