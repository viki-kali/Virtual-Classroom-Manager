import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ClassroomManagerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Classroom> classrooms = new HashMap<>();
        Map<String, List<Student>> studentsByClass = new HashMap<>();
        List<Assignment> assignments = new ArrayList<>();

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add Classroom");
            System.out.println("2. Add Student");
            System.out.println("3. Schedule Assignment");
            System.out.println("4. Submit Assignment");
            System.out.println("5. Select User");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); //
            switch (choice) {
                case 1:
                    System.out.print("Enter the class name: ");
                    String className = scanner.nextLine();
                    classrooms.put(className, new Classroom(className));
                    System.out.println("Classroom added.");
                    break;
                case 2:
                    System.out.print("Enter the student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Enter the class name: ");
                    String studentClassName = scanner.nextLine();
                    Student student = new Student(studentId, studentClassName);

                    List<Student> students = studentsByClass.get(studentClassName);
                    if (students == null) {
                        students = new ArrayList<>();
                        studentsByClass.put(studentClassName, students);
                    }
                    students.add(student);

                    System.out.println("Student added to the class.");
                    break;
                case 3:
                    System.out.print("Enter the class name: ");
                    String assignmentClassName = scanner.nextLine();
                    System.out.print("Enter assignment details: ");
                    String assignmentDetails = scanner.nextLine();
                    Assignment assignment = new Assignment(assignmentClassName, assignmentDetails);
                    assignments.add(assignment);
                    System.out.println("Assignment scheduled for the class.");
                    break;
                case 4:
                    System.out.print("Enter student ID: ");
                    String studentIdForSubmission = scanner.nextLine();
                    System.out.print("Enter class name: ");
                    String classNameForSubmission = scanner.nextLine();
                    System.out.print("Enter assignment details: ");
                    String assignmentDetailsForSubmission = scanner.nextLine();

                    boolean assignmentSubmittedFlag = false;

                    for (Assignment a : assignments) {
                        if (a.getClassName().equals(classNameForSubmission)
                                && a.getAssignmentDetails().equals(assignmentDetailsForSubmission)) {
                            System.out.println("Assignment marked as submitted.");
                            assignmentSubmittedFlag = true;
                            break;
                        }
                    }

                    if (!assignmentSubmittedFlag) {
                        System.out.println("Assignment not found.");
                    }
                    break;
            
              default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
