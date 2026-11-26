import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class EmployeeView {
    private final Scanner scanner = new Scanner(System.in);
    public String ask(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    public void menu() {
        System.out.println("\n===== DEPARTMENT STORE EMPLOYEE SYSTEM =====");
        System.out.println("1. SORT (recursive Merge Sort)");
        System.out.println("2. SEARCH (recursive Binary Search)");
        System.out.println("3. ADD RECORDS");
        System.out.println("4. CREATE BINARY TREE");
        System.out.println("5. EXIT");
    }
    public void showEmployees(List<Employee> employees) {
        if (employees.isEmpty()) { System.out.println("No records found."); return; }
        for (Employee employee : employees) System.out.println(employee);
    }
    public void showChoices(String label, Set<String> choices) {
        System.out.println(label + ":");
        for (String choice : choices) System.out.println(" - " + choice);
    }
}
