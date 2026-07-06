import java.util.Scanner;
import java.util.ArrayList;

class Student {
    private String firstName;
    private String lastName;
    private String studentId;
    private ArrayList<Course> courses;

    public Student(String firstName, String lastName, String studentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public double calculateAverage() {
        if (courses.isEmpty()) return 0;
        double total = 0;
        for (Course course : courses) {
            total += course.getAverage();
        }
        return total / courses.size();
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getStudentId() { return studentId; }
    public ArrayList<Course> getCourses() { return courses; }
    public String getFullName() { return firstName + " " + lastName; }
}

class Course {
    private String courseName;
    private double midterm;
    private double finalExam;
    private double homework;

    public Course(String courseName, double midterm, double finalExam, double homework) {
        this.courseName = courseName;
        this.midterm = midterm;
        this.finalExam = finalExam;
        this.homework = homework;
    }

    public double getAverage() {
        return (midterm * 0.3) + (finalExam * 0.5) + (homework * 0.2);
    }

    public String getLetterGrade() {
        double avg = getAverage();
        if (avg >= 90) return "AA";
        else if (avg >= 85) return "BA";
        else if (avg >= 80) return "BB";
        else if (avg >= 75) return "CB";
        else if (avg >= 70) return "CC";
        else if (avg >= 65) return "DC";
        else if (avg >= 60) return "DD";
        else if (avg >= 50) return "FD";
        else return "FF";
    }

    public String getStatus() {
        return getAverage() >= 60 ? "PASSED" : "FAILED";
    }

    public String getCourseName() { return courseName; }
    public double getMidterm() { return midterm; }
    public double getFinalExam() { return finalExam; }
    public double getHomework() { return homework; }
}

public class StudentGradeManagementSystem {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Add demo data
        addDemoData();

        System.out.println("+=============================================+");
        System.out.println("|  WELCOME TO THE STUDENT GRADE MANAGEMENT SYSTEM  |");
        System.out.println("+=============================================+");

        boolean keepGoing = true;
        while (keepGoing) {
            keepGoing = mainMenu();
        }

        System.out.println("\nExiting the program. Have a great day!");
        scanner.close();
    }

    private static void addDemoData() {
        Student s1 = new Student("Ahmet", "Yilmaz", "2021001");
        s1.addCourse(new Course("Mathematics", 75, 80, 90));
        s1.addCourse(new Course("Physics", 70, 75, 85));
        s1.addCourse(new Course("Chemistry", 65, 70, 80));
        students.add(s1);

        Student s2 = new Student("Ayse", "Demir", "2021002");
        s2.addCourse(new Course("Mathematics", 90, 95, 100));
        s2.addCourse(new Course("Physics", 85, 90, 95));
        students.add(s2);
    }

    private static boolean mainMenu() {
        System.out.println("\n+=============================================+");
        System.out.println("|                  MAIN MENU                   |");
        System.out.println("+=============================================+");
        System.out.println("|  1. Add New Student                          |");
        System.out.println("|  2. Add Course Grade to a Student             |");
        System.out.println("|  3. Student List                             |");
        System.out.println("|  4. Detailed Student Report                  |");
        System.out.println("|  5. Class Overview                           |");
        System.out.println("|  6. Search Student                           |");
        System.out.println("|  7. Top Performing Students                  |");
        System.out.println("|  8. Exit                                     |");
        System.out.println("+=============================================+");
        System.out.print("\nYour choice (1-8): ");

        int choice = 0;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("[X] Invalid input!");
            return true;
        }

        switch (choice) {
            case 1: addNewStudent(); break;
            case 2: addCourseGrade(); break;
            case 3: studentList(); break;
            case 4: detailedStudentReport(); break;
            case 5: classOverview(); break;
            case 6: searchStudent(); break;
            case 7: topPerformingStudents(); break;
            case 8: return false;
            default: System.out.println("[X] Invalid choice!");
        }

        return true;
    }

    private static void addNewStudent() {
        System.out.println("\n+=============================================+");
        System.out.println("|              ADD NEW STUDENT                 |");
        System.out.println("+=============================================+");

        System.out.print("Student First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Student Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();

        // Check for duplicate ID
        for (Student s : students) {
            if (s.getStudentId().equals(studentId)) {
                System.out.println("[X] A student with this ID is already registered!");
                return;
            }
        }

        Student newStudent = new Student(firstName, lastName, studentId);
        students.add(newStudent);
        System.out.println("[OK] Student added successfully!");
        System.out.println("-> " + firstName + " " + lastName + " - " + studentId);
    }

    private static void addCourseGrade() {
        System.out.println("\n+=============================================+");
        System.out.println("|              ADD COURSE GRADE                |");
        System.out.println("+=============================================+");

        if (students.isEmpty()) {
            System.out.println("[X] No students registered in the system!");
            return;
        }

        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();

        Student student = findStudent(studentId);
        if (student == null) {
            System.out.println("[X] Student not found!");
            return;
        }

        System.out.println("Student: " + student.getFullName());
        System.out.print("\nCourse Name: ");
        String courseName = scanner.nextLine();

        try {
            System.out.print("Midterm Grade (0-100): ");
            double midterm = scanner.nextDouble();

            System.out.print("Final Exam Grade (0-100): ");
            double finalExam = scanner.nextDouble();

            System.out.print("Homework Grade (0-100): ");
            double homework = scanner.nextDouble();
            scanner.nextLine();

            if (midterm < 0 || midterm > 100 || finalExam < 0 || finalExam > 100 || homework < 0 || homework > 100) {
                System.out.println("[X] Grades must be between 0 and 100!");
                return;
            }

            Course newCourse = new Course(courseName, midterm, finalExam, homework);
            student.addCourse(newCourse);

            System.out.println("\n[OK] Course grade added successfully!");
            System.out.printf("Average: %.2f\n", newCourse.getAverage());
            System.out.println("Letter Grade: " + newCourse.getLetterGrade());
            System.out.println("Status: " + newCourse.getStatus());

        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("[X] Invalid grade entered!");
        }
    }

    private static void studentList() {
        System.out.println("\n+=================================================================+");
        System.out.println("|                          STUDENT LIST                          |");
        System.out.println("+=================================================================+");

        if (students.isEmpty()) {
            System.out.println("[X] No students registered in the system!");
            return;
        }

        System.out.println("\n" + "-".repeat(65));
        System.out.printf("%-15s %-20s %-15s %-10s\n", "ID", "FULL NAME", "COURSE COUNT", "AVERAGE");
        System.out.println("-".repeat(65));

        for (Student s : students) {
            System.out.printf("%-15s %-20s %-15d %.2f\n",
                s.getStudentId(),
                s.getFullName(),
                s.getCourses().size(),
                s.calculateAverage());
        }
        System.out.println("-".repeat(65));
        System.out.println("Total Students: " + students.size());
    }

    private static void detailedStudentReport() {
        System.out.println("\n+=============================================+");
        System.out.println("|          DETAILED STUDENT REPORT             |");
        System.out.println("+=============================================+");

        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();

        Student student = findStudent(studentId);
        if (student == null) {
            System.out.println("[X] Student not found!");
            return;
        }

        System.out.println("\n" + "=".repeat(80));
        System.out.println("Student: " + student.getFullName());
        System.out.println("ID: " + student.getStudentId());
        System.out.println("=".repeat(80));

        if (student.getCourses().isEmpty()) {
            System.out.println("[X] This student has no course grades yet!");
            return;
        }

        System.out.println("\nCOURSE GRADES:");
        System.out.println("-".repeat(80));
        System.out.printf("%-20s %-8s %-8s %-8s %-10s %-8s %-10s\n",
            "COURSE", "MIDTERM", "FINAL", "HOMEWORK", "AVERAGE", "LETTER", "STATUS");
        System.out.println("-".repeat(80));

        for (Course course : student.getCourses()) {
            System.out.printf("%-20s %-8.0f %-8.0f %-8.0f %-10.2f %-8s %-10s\n",
                course.getCourseName(),
                course.getMidterm(),
                course.getFinalExam(),
                course.getHomework(),
                course.getAverage(),
                course.getLetterGrade(),
                course.getStatus());
        }

        System.out.println("-".repeat(80));
        System.out.printf("OVERALL AVERAGE: %.2f\n", student.calculateAverage());
        System.out.println("=".repeat(80));
    }

    private static void classOverview() {
        System.out.println("\n+=============================================+");
        System.out.println("|                CLASS OVERVIEW                |");
        System.out.println("+=============================================+");

        if (students.isEmpty()) {
            System.out.println("[X] No students registered in the system!");
            return;
        }

        double totalAverage = 0;
        int passedCount = 0;
        int failedCount = 0;
        double highest = 0;
        double lowest = 100;

        for (Student s : students) {
            if (s.getCourses().isEmpty()) continue;

            double avg = s.calculateAverage();
            totalAverage += avg;

            if (avg >= 60) passedCount++;
            else failedCount++;

            if (avg > highest) highest = avg;
            if (avg < lowest) lowest = avg;
        }

        double classAverage = totalAverage / students.size();

        System.out.println("\nSTATISTICS:");
        System.out.println("-".repeat(40));
        System.out.println("Total Students: " + students.size());
        System.out.println("Passed: " + passedCount);
        System.out.println("Failed: " + failedCount);
        System.out.printf("Class Average: %.2f\n", classAverage);
        System.out.printf("Highest Average: %.2f\n", highest);
        System.out.printf("Lowest Average: %.2f\n", lowest);
        System.out.println("-".repeat(40));
    }

    private static void searchStudent() {
        System.out.println("\n+=============================================+");
        System.out.println("|               SEARCH STUDENT                 |");
        System.out.println("+=============================================+");

        System.out.print("Enter student first or last name: ");
        String query = scanner.nextLine().toLowerCase();

        boolean found = false;
        System.out.println("\nSEARCH RESULTS:");
        System.out.println("-".repeat(60));

        for (Student s : students) {
            if (s.getFirstName().toLowerCase().contains(query) ||
                s.getLastName().toLowerCase().contains(query)) {
                System.out.printf("%s - %s (Average: %.2f)\n",
                    s.getFullName(), s.getStudentId(), s.calculateAverage());
                found = true;
            }
        }

        if (!found) {
            System.out.println("[X] No matching student found!");
        }
        System.out.println("-".repeat(60));
    }

    private static void topPerformingStudents() {
        System.out.println("\n+=============================================+");
        System.out.println("|          TOP PERFORMING STUDENTS             |");
        System.out.println("+=============================================+");

        if (students.isEmpty()) {
            System.out.println("[X] No students registered in the system!");
            return;
        }

        // Sort students by average grade
        ArrayList<Student> sortedList = new ArrayList<>(students);
        sortedList.sort((s1, s2) -> Double.compare(s2.calculateAverage(), s1.calculateAverage()));

        System.out.println("\nTOP 5 STUDENTS:");
        System.out.println("-".repeat(60));

        int rank = 0;
        for (Student s : sortedList) {
            if (s.getCourses().isEmpty()) continue;

            rank++;
            String medal;
            if (rank == 1) medal = "[1st]";
            else if (rank == 2) medal = "[2nd]";
            else if (rank == 3) medal = "[3rd]";
            else medal = "     ";

            System.out.printf("%s %d. %-25s %.2f\n",
                medal, rank, s.getFullName(), s.calculateAverage());

            if (rank >= 5) break;
        }
        System.out.println("-".repeat(60));
    }

    private static Student findStudent(String studentId) {
        for (Student s : students) {
            if (s.getStudentId().equals(studentId)) {
                return s;
            }
        }
        return null;
    }
}
