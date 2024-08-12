public class StudentDetails extends UserDetails implements StudentOperations {
    private String studentId;
    private String grade;
    private String department;
    private String password;
    private String father_s_name;
    private String mother_s_name;

    public StudentDetails(String name, String address, String father_s_name, String mother_s_name, String mobileNo,
            String studentId, String grade,
            String department, String password) {
        super(name, address, mobileNo);
        this.studentId = studentId;
        this.father_s_name = father_s_name;
        this.mother_s_name = mother_s_name;
        this.grade = grade;
        this.department = department;
        this.password = password;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getGrade() {
        return grade;
    }

    public String getDepartment() {
        return department;
    }

    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public void giveExam() {
        System.out.println("Student " + getName() + " is giving an exam.");

    }

    @Override
    public void takeCourse() {
        System.out.println("Student " + getName() + " is taking a course in " + department + ".");

    }

    @Override
    public void showDetails() {
        System.out.println(super.getName() + "\'s details are " + '\n' +
                "studentId='" + studentId + '\n' +
                "Adress'" + super.getAddress() + '\n' +
                "Father's name='" + father_s_name + '\n' +
                "Mother's name='" + mother_s_name + '\n' +
                "Mobile Number '" + super.getMobileNo() + '\n' +
                "grade='" + grade + '\n' +
                "department='" + department + '\n');
    }
}
