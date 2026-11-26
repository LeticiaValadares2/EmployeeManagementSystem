import java.util.List;

public class RecursiveBinarySearch {
    public static int search(List<Employee> sorted, String name) {
        return search(sorted, name.trim(), 0, sorted.size() - 1);
    }
    private static int search(List<Employee> sorted, String name, int low, int high) {
        if (low > high) return -1;
        int mid = low + (high - low) / 2;
        int comparison = sorted.get(mid).getName().compareToIgnoreCase(name);
        if (comparison == 0) return mid;
        if (comparison < 0) return search(sorted, name, mid + 1, high);
        return search(sorted, name, low, mid - 1);
    }
}
