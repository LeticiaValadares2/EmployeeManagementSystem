import java.util.List;
import java.util.ArrayList;

public class RecursiveMergeSort {
    public static List<Employee> sort(List<Employee> input) {
        if (input.size() <= 1) return new ArrayList<>(input);
        int mid = input.size() / 2;
        List<Employee> left = sort(input.subList(0, mid));
        List<Employee> right = sort(input.subList(mid, input.size()));
        List<Employee> result = new ArrayList<>();
        int i = 0, j = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).getName().compareToIgnoreCase(right.get(j).getName()) <= 0)
                result.add(left.get(i++));
            else result.add(right.get(j++));
        }
        while (i < left.size()) result.add(left.get(i++));
        while (j < right.size()) result.add(right.get(j++));
        return result;
    }
}
