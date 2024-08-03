package LinkedList;
import java.util.*;

public class LinkedList {
    private Student head;
    private int nextId;
    private Map<Integer, List<Student>> ageMap;

    public LinkedList() {
        this.head = null;
        this.nextId = 1; // Starting ID from 1
        this.ageMap = new HashMap<>();
    }

    public void add(String name, double marks, int age) {
        Student student = new Student(nextId++, name, marks, age);

        // Add student to the linked list
        if (head == null) {
            head = student;
        } else {
            Student current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = student;
        }

        // Add student to the ageMap
        ageMap.computeIfAbsent(age, k -> new ArrayList<>()).add(student);
    }

    public boolean exists(int id) {
        Student current = head;
        while (current != null) {
            if (current.id == id) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean update(int id, String name, double marks, int age) {
        Student current = head;
        while (current != null) {
            if (current.id == id) {
                // Remove student from the old ageMap list
                ageMap.get(current.age).remove(current);
                if (ageMap.get(current.age).isEmpty()) {
                    ageMap.remove(current.age);
                }

                // Update student details
                current.name = name;
                current.marks = marks;
                current.rank = current.calculateRank(marks);
                current.age = age;

                // Add student to the new ageMap list
                ageMap.computeIfAbsent(age, k -> new ArrayList<>()).add(current);
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean delete(int id) {
        if (head == null) {
            return false;
        }

        if (head.id == id) {
            // Remove student from the ageMap list
            ageMap.get(head.age).remove(head);
            if (ageMap.get(head.age).isEmpty()) {
                ageMap.remove(head.age);
            }
            head = head.next;
            return true;
        }

        Student current = head;
        while (current.next != null) {
            if (current.next.id == id) {
                // Remove student from the ageMap list
                ageMap.get(current.next.age).remove(current.next);
                if (ageMap.get(current.next.age).isEmpty()) {
                    ageMap.remove(current.next.age);
                }
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void display() {
        if (head == null) {
            System.out.println("No students to display.");
            return;
        }

        Student current = head;
        while (current != null) {
            System.out.println(current);
            current = current.next;
        }
    }

    public void bubbleSortByAge() {
        long startTime = System.currentTimeMillis(); // Ghi lại thời điểm bắt đầu
        if (head == null) {
            return;
        }

        boolean swapped;
        Student ptr1;
        Student lptr = null;

        do {
            swapped = false;
            ptr1 = head;

            while (ptr1.next != lptr) {
                if (ptr1.age > ptr1.next.age) {
                    int tempId = ptr1.id;
                    String tempName = ptr1.name;
                    double tempMarks = ptr1.marks;
                    int tempAge = ptr1.age;
                    String tempRank = ptr1.rank;

                    ptr1.id = ptr1.next.id;
                    ptr1.name = ptr1.next.name;
                    ptr1.marks = ptr1.next.marks;
                    ptr1.age = ptr1.next.age;
                    ptr1.rank = ptr1.next.rank;

                    ptr1.next.id = tempId;
                    ptr1.next.name = tempName;
                    ptr1.next.marks = tempMarks;
                    ptr1.next.age = tempAge;
                    ptr1.next.rank = tempRank;

                    swapped = true;
                }
                ptr1 = ptr1.next;
            }
            lptr = ptr1;
        } while (swapped);
        long endTime = System.currentTimeMillis(); // Ghi lại thời điểm kết thúc
        System.out.println("Bubble Sort by Age took " + (endTime - startTime) + " milliseconds.");
    }

    public void insertionSortByMarks() {
        long startTime = System.currentTimeMillis(); // Ghi lại thời điểm bắt đầu

        if (head == null || head.next == null) {
            return;
        }

        Student sorted = null;
        Student current = head;

        while (current != null) {
            Student next = current.next;

            if (sorted == null || sorted.marks >= current.marks) {
                current.next = sorted;
                sorted = current;
            } else {
                Student temp = sorted;
                while (temp.next != null && temp.next.marks < current.marks) {
                    temp = temp.next;
                }
                current.next = temp.next;
                temp.next = current;
            }
            current = next;
        }
        head = sorted;
        long endTime = System.currentTimeMillis(); // Ghi lại thời điểm kết thúc
        System.out.println("Insertion Sort by Marks took " + (endTime - startTime) + " milliseconds.");
    }

    public void searchByName(String name) {
        long startTime = System.currentTimeMillis();// Ghi lại thời điểm bắt đầu
        Student current = head;
        boolean found = false;
        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) {
                System.out.println(current);
                found = true;
            }
            current = current.next;
        }

        if (!found) {
            System.out.println("No students found with the name: " + name);
        }
        long endTime = System.currentTimeMillis(); // Ghi lại thời điểm kết thúc
        System.out.println("Search by Name took " + (endTime - startTime) + " milliseconds.");
    }
    private Student[] toArray() {
        int size = 0;
        Student current = head;
        while (current != null) {
            size++;
            current = current.next;
        }

        Student[] array = new Student[size];
        current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current;
            current = current.next;
        }
        return array;
    }
    public Student binarySearchByAge(int age) {
//    Convert the linked list to an array
        long startTime = System.currentTimeMillis();// Ghi lại thời điểm bắt đầu
        Student[] array = toArray();

        // Sort the array by age to ensure binary search can be applied
        Arrays.sort(array, Comparator.comparingInt(s -> s.age));

        // Perform binary search
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midAge = array[mid].age;

            if (midAge == age) {
                return array[mid];
            } else if (midAge < age) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        long endTime = System.currentTimeMillis(); // Ghi lại thời điểm kết thúc
        System.out.println("Search by Age took " + (endTime - startTime) + " milliseconds.");
        return null; // Age not found

    }


}
