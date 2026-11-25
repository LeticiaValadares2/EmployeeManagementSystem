import java.io.IOException;
import java.util.List;

public class EmployeeController {
    private final EmployeeModel model = new EmployeeModel();
    private final EmployeeView view = new EmployeeView();
    public void run() {
        while (true) {
            String filename = view.ask("Please enter the filename to read (e.g. data/Applicants_Form.txt): ");
            try {
                model.loadFile(filename);
                System.out.println("File read successfully! Loaded " + model.size() + " records.");
                break;
            } catch (IOException e) { System.out.println("File error: " + e.getMessage()); }
        }
        while (true) {
            view.menu();
            String choice = view.ask("Choose an option: ");
            try {
                MenuOption option = MenuOption.fromNumber(Integer.parseInt(choice));
                switch (option) {
                    case SORT -> sort();
                    case SEARCH -> search();
                    case ADD_RECORDS -> add();
                    case BINARY_TREE -> tree();
                    case EXIT -> { System.out.println("Goodbye!"); return; }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: " + e.getMessage());
            }
        }
    }
    private void sort() {
        List<Employee> sorted = model.sortedEmployees();
        System.out.println("\nFirst 20 employees in alphabetical order:");
        view.showEmployees(sorted.subList(0, Math.min(20, sorted.size())));
    }
    private void search() {
        String name = view.ask("Enter the full name to search: ");
        System.out.println("Search results:");
        view.showEmployees(model.search(name));
    }
    private void add() {
        String name = view.ask("Enter first and last name: ");
        view.showChoices("Available manager/job types", model.managerTypes());
        String manager = view.ask("Choose manager/job type: ");
        view.showChoices("Available departments", model.departments());
        String department = view.ask("Choose department: ");
        model.add(name, manager, department);
        System.out.println("Record added successfully. New records:");
        view.showEmployees(model.getNewlyAdded());
    }
    private void tree() {
        if (model.size() < 20) { System.out.println("At least 20 records are required."); return; }
        EmployeeTree tree = model.buildTree();
        tree.displayByLevel();
        System.out.println("Total nodes: " + tree.getCount());
        System.out.println("Tree height (levels): " + tree.height());
    }
}
