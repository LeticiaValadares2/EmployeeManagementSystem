# Employee Management System

**Java Console Application | Algorithms & Architecture Assessment**

## Project overview

This project is a simple employee management system developed in Java. The selected organisation is a fictional **Department Store**. The program uses the employee sample file supplied for the assessment, which contains records from several companies. The original company and department values are kept when the file is loaded.

The system allows users to sort employee names, search for an employee, add a new record, and create a binary tree. It uses recursive algorithms, the Model-View-Controller (MVC) architecture, and an enum for the menu options.

## Requirements

- Java 17 or newer (JDK)
- A terminal, VS Code, or another Java IDE
- The included `data/Applicants_Form.txt` file

No external libraries are needed.

## Project structure

```text
EmployeeManagementUpdated/
├── data/
│   └── Applicants_Form.txt
├── src/
│   ├── Main.java
│   ├── Employee.java
│   ├── EmployeeModel.java
│   ├── EmployeeView.java
│   ├── EmployeeController.java
│   ├── MenuOption.java
│   ├── RecursiveMergeSort.java
│   ├── RecursiveBinarySearch.java
│   └── EmployeeTree.java
└── README.md
```

## How to run

Open a terminal **inside the `EmployeeManagementUpdated` folder**.

**1. Compile:**

```bash
javac -d out src/*.java
```

**2. Run:**

```bash
java -cp out Main
```

**3. Load the data:**

When the program asks for the filename, enter:

```text
data/Applicants_Form.txt
```

The program then displays the main menu. Enter a number from 1 to 5.

## Main menu and features

| Option | Feature | Description |
| --- | --- | --- |
| 1 | SORT | Sort employees alphabetically by full name and show the first 20 |
| 2 | SEARCH | Search for an employee by their full name |
| 3 | ADD RECORDS | Add an employee after validating the name, job type, and department |
| 4 | BINARY TREE | Build a binary tree and show its levels, number of nodes, and height |
| 5 | EXIT | Close the program |

### 1. Recursive Merge Sort

The sorting algorithm divides the employee list into smaller parts, sorts them recursively, and merges them back together. It sorts by full name and displays the first 20 employees.

- **Time complexity:** O(n log n)
- **Reason for choosing it:** predictable performance and stable sorting
- **Comparison:** Insertion Sort may take O(n²) on a large unsorted list; Quick Sort may also take O(n²) in the worst case

### 2. Recursive Binary Search

The user enters a full name. The program sorts a copy of the employee list and uses recursive binary search to find a match. If several employees have the same full name, it displays all matching records.

- **Search complexity after sorting:** O(log n)
- **Total per-search complexity:** O(n log n), because the list is sorted for each search
- **Reason for choosing it:** fewer comparisons than a linear search on an already sorted list

### 3. Add records

The user enters a first and last name, a manager/job type, and a department. The type and department must match values already present in the loaded data. Newly added employees are shown in the terminal.

**Important:** New records are stored in memory. They are **not saved back** to the original text file. The sample file has no `Manager Type` column, so this program uses `Job title` as the manager/job type, with `Position` as a fallback when necessary. Optional details not requested during registration are left blank or zero.

### 4. Binary tree

The program inserts employee records level by level, filling the left child before the right child. It prints a level-order traversal, the total number of nodes, and the height measured in levels.

This is a **complete binary tree**, not a binary search tree. It does not represent real reporting relationships between employees. At least 20 records are required to create the tree.

## MVC architecture

The project follows the **Model-View-Controller** pattern:

| Component | Java files | Responsibility |
| --- | --- | --- |
| Model | `Employee`, `EmployeeModel`, `EmployeeTree`, `RecursiveMergeSort`, `RecursiveBinarySearch` | Stores employee data and performs operations |
| View | `EmployeeView` | Displays the menu, messages, and results |
| Controller | `EmployeeController` | Handles user choices and connects the View to the Model |

For example, when the user selects **SORT**, the controller requests the sorted list from the model and sends the results to the view for display.

## Enum

`MenuOption.java` defines the five menu actions using an enum. This makes the menu easier to read and maintain than using numeric values throughout the code.

## Data file

The supplied text file is comma-separated (CSV format) and includes these nine columns:

`First name, Last name, Gender, Email, Salary, Department, Position, Job title, Company`

The program preserves empty fields and checks the expected header. It reads the original records without changing them.

## Suggested tests

| Test | Expected result |
| --- | --- |
| Load `data/Applicants_Form.txt` | Employee records are loaded and the menu appears |
| Select SORT | First 20 employees appear in alphabetical order |
| Search for `Kate Summer` | Matching employee details appear |
| Search for a name that does not exist | No matching records are shown |
| Add a valid full name, job type, and department | New record appears in the added-records list |
| Add an unknown department | Validation error appears |
| Select BINARY TREE | Levels, total nodes, and height are displayed |
| Select EXIT | Program ends |

These are **test cases to run**, not a claim that all tests have been completed on your computer.

## Limitations and possible improvements

- New employees are not saved to the original file.
- The system is a console application without a graphical interface.
- Searching sorts a copy of the list each time; a cached sorted list could improve repeated searches.
- The tree stores employees in insertion order rather than a real management hierarchy.
- A future version could support editing and deleting records, saving changes, and more complete employee registration.

## Academic poster

The academic poster is a separate deliverable. It should explain the problem, MVC design, algorithm choices, results, and reflection. Add real screenshots from your own program tests and review the poster before submission.

## Assessment note

This project is a starting implementation for the assessment. Review the source code, run your own tests, and make any changes required by your lecturer's full instructions before submitting.
