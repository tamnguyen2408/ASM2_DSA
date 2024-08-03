package LinkedList;
public class Student {
    int id;
    String name;
    double marks;
    int age;
    String rank;
    Student next; // Thêm trường này để lưu địa chỉ của sinh viên tiếp theo trong danh sách

    public Student(int id, String name, double marks, int age) {
        this.id = id;
        this.name = name;
        this.marks = marks;
        this.age = age;
        this.rank = calculateRank(marks);
        this.next = null; // Ban đầu không có sinh viên tiếp theo
    }

    public String calculateRank(double marks) {
        if (marks >= 9.0 && marks <= 10.0) {
            return "Excellent";
        } else if (marks >= 7.5 && marks < 9.0) {
            return "Very Good";
        } else if (marks >= 6.5 && marks < 7.5) {
            return "Good";
        } else if (marks >= 5.0 && marks < 6.5) {
            return "Medium";
        } else {
            return "Fail";
        }
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Marks: " + marks + ", Age: " + age + ", Rank: " + rank;
    }

    // Getters and setters (if needed) for next field
    public Student getNext() {
        return next;
    }

    public void setNext(Student next) {
        this.next = next;
    }
}