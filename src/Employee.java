public class Employee {
    private final String firstName, lastName, gender, email, department, position, jobTitle, company;
    private final double salary;

    public Employee(String firstName, String lastName, String gender, String email, double salary,
                    String department, String position, String jobTitle, String company) {
        this.firstName = firstName.trim(); this.lastName = lastName.trim();
        this.gender = gender.trim(); this.email = email.trim(); this.salary = salary;
        this.department = department.trim(); this.position = position.trim();
        this.jobTitle = jobTitle.trim(); this.company = company.trim();
    }
    public String getName() { return firstName + " " + lastName; }
    public String getDepartment() { return department; }
    public String getJobTitle() { return jobTitle; }
    public String getPosition() { return position; }
    public String getCompany() { return company; }
    public String getEmail() { return email; }
    public String getGender() { return gender; }
    public double getSalary() { return salary; }
    public String getManagerType() { return jobTitle.isEmpty() ? position : jobTitle; }
    @Override public String toString() {
        return getName() + " | Manager/Job type: " + getManagerType()
                + " | Department: " + department + " | Company: " + company;
    }
}
