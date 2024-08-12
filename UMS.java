import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UMS {
    private List<StudentDetails> students;
    private List<FacultyDetails> faculties;
    private Admin admin;
    private String[] coursesOffered = { "Data Structures", "Algorithms", "Database Management Systems",
            "Operating Systems" };
    private String[] feeStructure = { "CSE: 50000", "ECE: 48000", "ME: 45000" };

    public UMS() {
        students = new ArrayList<>();
        faculties = new ArrayList<>();
        admin = new Admin("admin", "password123", "Admin"); // Example admin credentials
    }

    public void loginMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean continueRunning = true;

        while (continueRunning) {
            System.out.println("Welcome to University Management System:");
            System.out.println("1. Login");
            System.out.println("2. Courses Offered");
            System.out.println("3. Fee Structure");
            System.out.println("4. Students Details");
            System.out.println("5. Faculties Details");

            System.out.println("6. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    courseOffered();
                    break;
                case 3:
                    feeStructure();
                    break;

                case 4:
                    System.out.println("Student details are \n");
                    for (StudentDetails student : students) {
                        student.showDetails();
                    }
                    break;

                case 5:
                    System.out.println("Faculty details are \n");
                    for (FacultyDetails faculty : faculties) {
                        faculty.showDetails();
                    }
                    break;

                case 6:
                    continueRunning = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    private void courseOffered() {
        System.out.println("Courses Offered:");
        for (String course : coursesOffered) {
            System.out.println("- " + course);
        }
    }

    private void feeStructure() {
        System.out.println("Fee Structure:");
        for (String fee : feeStructure) {
            System.out.println("- " + fee);
        }
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);
        boolean continueRunning = true;

        while (continueRunning) {
            System.out.println("Enter the login Type:");
            System.out.println("1. Admin Login");
            System.out.println("2. Student Login");
            System.out.println("3. Faculty Login");
            System.out.println("4. Exit");
            int choose = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choose) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    studentLogin();
                    break;
                case 3:
                    facultyLogin();
                    break;
                case 4:
                    continueRunning = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    private void adminLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        try {
            if (admin.validateUser(username, password)) {
                boolean continueAdmin = true;
                while (continueAdmin) {
                    System.out.println("Admin Operations:");
                    System.out.println("1. Add Student");
                    System.out.println("2. Add Faculty");
                    System.out.println("3. Fee Collection");
                    // System.out.println("4. Add Course");
                    // System.out.println("5. Show Details");
                    System.out.println("6. Exit");
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (choice) {
                        case 1:
                            admin.addStudent(students);
                            break;
                        case 2:
                            admin.addFaculty(faculties);
                            break;
                        case 3:
                            admin.feeCollection();
                            break;

                        case 6:
                            continueAdmin = false;
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }
                }
            } else {
                System.out.println("Invalid username or password.");
            }
        } catch (InvalidLoginException e) {
            System.out.println(e.getMessage());
        }
    }

    private void studentLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Student ID: ");
        String studentId = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        StudentDetails student = validateStudent(studentId, password);
        if (student != null) {
            boolean continueStudent = true;
            while (continueStudent) {
                System.out.println("Student Operations:");
                System.out.println("1. Show Details");
                System.out.println("2. Give Exam");
                System.out.println("3. Take Course");
                System.out.println("4. Exit");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        student.showDetails();
                        break;
                    case 2:
                        student.giveExam();
                        break;
                    case 3:
                        student.takeCourse();
                        break;
                    case 4:
                        continueStudent = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } else {
            System.out.println("Invalid Student ID or password.");
        }
    }

    private void facultyLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Faculty ID: ");
        String facultyId = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        FacultyDetails faculty = validateFaculty(facultyId, password);
        if (faculty != null) {
            boolean continueFaculty = true;
            while (continueFaculty) {
                System.out.println("Faculty Operations:");
                System.out.println("1. Show Details");
                System.out.println("2. Take Exam");
                System.out.println("3. Assign Grade");
                System.out.println("4. Exit");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        faculty.showDetails();
                        break;
                    case 2:
                        faculty.takeExam();
                        break;
                    case 3:
                        faculty.assignGrade();
                        break;
                    case 4:
                        continueFaculty = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } else {
            System.out.println("Invalid Faculty ID or password.");
        }
    }

    private StudentDetails validateStudent(String studentId, String password) {
        for (StudentDetails student : students) {
            if (student.getStudentId().equals(studentId) && student.validatePassword(password)) {
                return student;
            }
        }
        return null;
    }

    private FacultyDetails validateFaculty(String facultyId, String password) {
        for (FacultyDetails faculty : faculties) {
            if (faculty.getFacultyId().equals(facultyId) && faculty.validatePassword(password)) {
                return faculty;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        UMS ums = new UMS();
        // Add some students and faculties for testing
        ums.students.add(new StudentDetails("Rishabh Chandrakar", "Bemetara, Chhattisgarh", "Bhajan Lal Chandrakar",
                "Anjani Chandrakar", "7489273407", "23115080",
                "A", "CSE", "1234"));
        ums.students.add(
                new StudentDetails("Piyush Lokhande", "Nagpur, Maharashtra", " ", " ", "9999999999", "23115071", "A",
                        "CSE", "1234"));

        ums.faculties.add(new FacultyDetails("Dr. Naresh Nagwani", "Chhattisgarh", "+91 99933 12001", "fac001", "CSE",
                "Algorithms", "facpass123"));
        ums.faculties.add(
                new FacultyDetails("Jitendra Rout", "Odisha", "+91 94396 22802", "fac002", "CSE", "DSA", "facpass123"));

        ums.loginMenu();
    }
}
