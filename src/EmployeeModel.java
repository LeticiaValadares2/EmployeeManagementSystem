import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class EmployeeModel {
    private final List<Employee> employees = new ArrayList<>();
    private final List<Employee> newlyAdded = new ArrayList<>();
    public void loadFile(String filename) throws IOException {
        List<Employee> loaded = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Path.of(filename), StandardCharsets.UTF_8)) {
            String header = reader.readLine();
            if (header == null || !header.replace("\uFEFF", "").startsWith("First name,Last name,Gender,Email,Salary,Department,Position,Job title,Company"))
                throw new IOException("Incorrect CSV header. Use the provided Applicants_Form.txt file.");
            String line; int number = 1;
            while ((line = reader.readLine()) != null) {
                number++;
                if (line.isBlank()) continue;
                List<String> fields = parseCsvLine(line);
                if (fields.size() != 9) throw new IOException("Line " + number + " must have 9 fields.");
                try {
                    loaded.add(new Employee(fields.get(0), fields.get(1), fields.get(2), fields.get(3),
                        Double.parseDouble(fields.get(4).trim()), fields.get(5), fields.get(6),
                        fields.get(7), fields.get(8)));
                } catch (NumberFormatException e) { throw new IOException("Invalid salary at line " + number, e); }
            }
        }
        employees.clear(); employees.addAll(loaded); newlyAdded.clear();
    }
    // Handles commas inside quoted CSV fields and preserves empty fields.
    private List<String> parseCsvLine(String line) throws IOException {
        List<String> fields = new ArrayList<>();
        StringBuilder current = new StringBuilder(); boolean quoted = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                if (quoted && i + 1 < line.length() && line.charAt(i + 1) == '"') { current.append('"'); i++; }
                else quoted = !quoted;
            } else if (c == ',' && !quoted) { fields.add(current.toString().trim()); current.setLength(0); }
            else current.append(c);
        }
        if (quoted) throw new IOException("Unclosed quote in CSV line.");
        fields.add(current.toString().trim());
        return fields;
    }
    public List<Employee> sortedEmployees() { return RecursiveMergeSort.sort(employees); }
    public List<Employee> search(String name) {
        List<Employee> sorted = sortedEmployees();
        int index = RecursiveBinarySearch.search(sorted, name);
        List<Employee> matches = new ArrayList<>();
        if (index == -1) return matches;
        int start = index;
        while (start > 0 && sorted.get(start - 1).getName().equalsIgnoreCase(name.trim())) start--;
        while (start < sorted.size() && sorted.get(start).getName().equalsIgnoreCase(name.trim()))
            matches.add(sorted.get(start++));
        return matches;
    }
    public Set<String> departments() {
        Set<String> values = new LinkedHashSet<>();
        for (Employee e : employees) if (!e.getDepartment().isEmpty()) values.add(e.getDepartment());
        return values;
    }
    public Set<String> managerTypes() {
        Set<String> values = new LinkedHashSet<>();
        for (Employee e : employees) if (!e.getManagerType().isEmpty()) values.add(e.getManagerType());
        return values;
    }
    public String canonical(Set<String> allowed, String input) {
        for (String value : allowed) if (value.equalsIgnoreCase(input.trim())) return value;
        return null;
    }
    public void add(String name, String managerType, String department) {
        name = name.trim().replaceAll("\\s+", " ");
        if (!name.contains(" ")) throw new IllegalArgumentException("Enter first and last name.");
        String dept = canonical(departments(), department);
        String manager = canonical(managerTypes(), managerType);
        if (dept == null || manager == null) throw new IllegalArgumentException("Choose an existing department and manager/job type.");
        int lastSpace = name.lastIndexOf(' ');
        // Other fields are not requested for new records; blank values are kept in memory.
        Employee employee = new Employee(name.substring(0, lastSpace), name.substring(lastSpace + 1),
            "", "", 0, dept, "", manager, "Department Store");
        employees.add(employee); newlyAdded.add(employee);
    }
    public List<Employee> getNewlyAdded() { return new ArrayList<>(newlyAdded); }
    public int size() { return employees.size(); }
    public EmployeeTree buildTree() {
        EmployeeTree tree = new EmployeeTree();
        for (Employee employee : employees) tree.insert(employee);
        return tree;
    }
}
