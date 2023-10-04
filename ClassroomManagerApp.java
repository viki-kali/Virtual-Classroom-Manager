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
            scanner.nextLine(); // Consume the newline character

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
                case 5:
                    System.out.println("Select User:");
                    System.out.println("1. Classroom Management");
                    System.out.println("2. Student Management");
                    System.out.println("3. Assignment Management");
                    System.out.print("Enter your user choice: ");
                    int userChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    switch (userChoice) {
                        case 1:
                            // Classroom Management
                            System.out.println("Classroom Management Options:");
                            System.out.println("1. Add Classrooms");
                            System.out.println("2. List Classrooms");
                            System.out.println("3. Remove Classrooms");
                            System.out.print("Enter your choice: ");
                            int classroomChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume the newline character

                            switch (classroomChoice) {
                                case 1:
                                    // Add Classrooms
                                    System.out.print("Enter the class name: ");
                                    String newClassName = scanner.nextLine();
                                    classrooms.put(newClassName, new Classroom(newClassName));
                                    System.out.println("Classroom added.");
                                    break;
                                case 2:
                                    // List Classrooms
                                    System.out.println("List of Classrooms:");
                                    for (String clsName : classrooms.keySet()) {
                                        System.out.println(clsName);
                                    }
                                    break;
                                case 3:
                                    // Remove Classrooms
                                    System.out.print("Enter the class name to remove: ");
                                    String classNameToRemove = scanner.nextLine();
                                    Classroom removedClassroom = classrooms.remove(classNameToRemove);
                                    if (removedClassroom != null) {
                                        studentsByClass.remove(classNameToRemove);
                                        System.out.println("Classroom removed.");
                                    } else {
                                        System.out.println("Classroom not found.");
                                    }
                                    break;
                                default:
                                    System.out.println("Invalid choice.");
                            }
                            break;
                        case 2:
                            // Student Management
                            System.out.println("Student Management Options:");
                            System.out.println("1. Enroll Students into Classrooms");
                            System.out.println("2. List Students in Each Classroom");
                            System.out.print("Enter your choice: ");
                            int studentChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume the newline character

                            switch (studentChoice) {
                                case 1:
                                    // Enroll Students into Classrooms
                                    System.out.print("Enter student ID: ");
                                    String studentIdToEnroll = scanner.nextLine();
                                    System.out.print("Enter class name: ");
                                    String classToEnroll = scanner.nextLine();
                                    Student studentToEnroll = new Student(studentIdToEnroll, classToEnroll);

                                    List<Student> enrolledStudents = studentsByClass.get(classToEnroll);
                                    if (enrolledStudents == null) {
                                        enrolledStudents = new ArrayList<>();
                                        studentsByClass.put(classToEnroll, enrolledStudents);
                                    }
                                    enrolledStudents.add(studentToEnroll);
                                    System.out.println("Student enrolled in the class.");
                                    break;
                                case 2:
                                    // List Students in Each Classroom
                                    System.out.println("List of Students in Each Classroom:");
                                    for (Map.Entry<String, List<Student>> entry : studentsByClass.entrySet()) {
                                        System.out.println("Classroom: " + entry.getKey());
                                        List<Student> studentsInClass = entry.getValue();
                                        for (Student s : studentsInClass) {
                                            System.out.println("Student ID: " + s.getStudentId());
                                        }
                                    }
                                    break;
                                default:
                                    System.out.println("Invalid choice.");
                            }
                            break;
                        case 3:
                            // Assignment Management
                            System.out.println("Assignment Management Options:");
                            System.out.println("1. Schedule Assignments for Classrooms");
                            System.out.println("2. Allow Students to Submit Assignments");
                            System.out.print("Enter your choice: ");
                            int assignmentUserChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume the newline character

                            switch (assignmentUserChoice) {
                                case 1:
                                    // Schedule Assignments for Classrooms
                                    System.out.print("Enter the class name: ");
                                    String assignmentClass = scanner.nextLine();
                                    System.out.print("Enter assignment details: ");
                                    String assignmentDetailsForSchedule = scanner.nextLine();
                                    Assignment assignmentForSchedule = new Assignment(assignmentClass, assignmentDetailsForSchedule);
                                    assignments.add(assignmentForSchedule);
                                    System.out.println("Assignment scheduled for the class.");
                                    break;
                                case 2:
                                    // Allow Students to Submit Assignments
                                    System.out.print("Enter student ID: ");
                                    String studentIdForSubmit = scanner.nextLine();
                                    System.out.print("Enter class name: ");
                                    String classNameForSubmit = scanner.nextLine();
                                    System.out.print("Enter assignment details: ");
                                    String assignmentDetailsForSubmit = scanner.nextLine();

                                    boolean assignmentSubmitted = false;

                                    for (Assignment a : assignments) {
                                        if (a.getClassName().equals(classNameForSubmit)
                                                && a.getAssignmentDetails().equals(assignmentDetailsForSubmit)) {
                                            System.out.println("Assignment marked as submitted.");
                                            assignmentSubmitted = true;
                                            break;
                                        }
                                    }

                                    if (!assignmentSubmitted) {
                                        System.out.println("Assignment not found.");
                                    }
                                    break;
                                default:
                                    System.out.println("Invalid choice.");
                            }
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }
                    break;
                case 6:
                    // Exit the program
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
