import java.io.*;
import java.util.*;

// ===================== STUDENT CLASS =====================
class Student implements Serializable {
    private String name;
    private String rollNumber;
    private String grade;
    private int age;

    public Student(String name, String rollNumber, String grade, int age) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.age = age;
    }

    // Getters & Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRollNumber() { return rollNumber; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "Name: " + name +
               ", Roll No: " + rollNumber +
               ", Grade: " + grade +
               ", Age: " + age;
    }
}

// ===================== STUDENT MANAGEMENT SYSTEM =====================
class StudentManagementSystem {
    private List<Student> students;
    private final String FILE_NAME = "students.dat";

    public StudentManagementSystem() {
        students = loadFromFile();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveToFile();
    }

    public boolean removeStudent(String rollNumber) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (s.getRollNumber().equalsIgnoreCase(rollNumber)) {
                iterator.remove();
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public Student searchStudent(String rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber().equalsIgnoreCase(rollNumber)) {
                return s;
            }
        }
        return null;
    }

    public boolean editStudent(String rollNumber, String newName, String newGrade, int newAge) {
        Student s = searchStudent(rollNumber);
        if (s != null) {
            s.setName(newName);
            s.setGrade(newGrade);
            s.setAge(newAge);
            saveToFile();
            return true;
        }
        return false;
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private List<Student> loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Student>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}

// ===================== MAIN CLASS (UI) =====================
public class Task_5 {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        int choice;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Search Student");
            System.out.println("4. Remove Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = getIntInput();

            switch (choice) {
                case 1 -> addStudentUI(sms);
                case 2 -> editStudentUI(sms);
                case 3 -> searchStudentUI(sms);
                case 4 -> removeStudentUI(sms);
                case 5 -> sms.displayAllStudents();
                case 6 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 6);
    }

    // UI Methods
    private static void addStudentUI(StudentManagementSystem sms) {
        System.out.print("Enter Name: ");
        String name = getStringInput();
        System.out.print("Enter Roll Number: ");
        String roll = getStringInput();
        System.out.print("Enter Grade: ");
        String grade = getStringInput();
        System.out.print("Enter Age: ");
        int age = getIntInput();

        sms.addStudent(new Student(name, roll, grade, age));
        System.out.println("✅ Student added successfully.");
    }

    private static void editStudentUI(StudentManagementSystem sms) {
        System.out.print("Enter Roll Number to edit: ");
        String roll = getStringInput();
        System.out.print("Enter New Name: ");
        String name = getStringInput();
        System.out.print("Enter New Grade: ");
        String grade = getStringInput();
        System.out.print("Enter New Age: ");
        int age = getIntInput();

        if (sms.editStudent(roll, name, grade, age)) {
            System.out.println("✅ Student updated successfully.");
        } else {
            System.out.println("❌ Student not found.");
        }
    }

    private static void searchStudentUI(StudentManagementSystem sms) {
        System.out.print("Enter Roll Number to search: ");
        String roll = getStringInput();
        Student s = sms.searchStudent(roll);
        if (s != null) {
            System.out.println(s);
        } else {
            System.out.println("❌ Student not found.");
        }
    }

    private static void removeStudentUI(StudentManagementSystem sms) {
        System.out.print("Enter Roll Number to remove: ");
        String roll = getStringInput();
        if (sms.removeStudent(roll)) {
            System.out.println("✅ Student removed successfully.");
        } else {
            System.out.println("❌ Student not found.");
        }
    }

    // Input Validation
    private static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Try again: ");
            }
        }
    }

    private static String getStringInput() {
        while (true) {
            String input = sc.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.print("Input cannot be empty. Try again: ");
        }
    }
}
