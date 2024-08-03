package Array;

public class Student {
    int id;
    String name;
    int age;
    double marks;
    String rank;


    public Student(int id, String name,int age, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
        this.rank = calculateRank(marks);

    }

    public String calculateRank(double marks) {
        if (marks < 5.0) {
            return "Fail";
        } else if (marks < 6.5) {
            return "Medium";
        } else if (marks < 7.5) {
            return "Good";
        } else if (marks < 9.0) {
            return "Very Good";
        } else {
            return "Excellent";
        }
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Marks: " + marks + ", Rank: " + rank;
    }
}