import java.util.ArrayDeque;
import java.util.Queue;

public class EmployeeTree {
    private static class Node {
        Employee employee;
        Node left, right;
        Node(Employee employee) { this.employee = employee; }
    }
    private Node root;
    private int count;
    public void insert(Employee employee) {
        Node newNode = new Node(employee);
        if (root == null) { root = newNode; count++; return; }
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node current = queue.remove();
            if (current.left == null) { current.left = newNode; break; }
            queue.add(current.left);
            if (current.right == null) { current.right = newNode; break; }
            queue.add(current.right);
        }
        count++;
    }
    public void displayByLevel() {
        if (root == null) { System.out.println("Tree is empty."); return; }
        Queue<Node> queue = new ArrayDeque<>(); queue.add(root);
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            System.out.println("LEVEL " + level + ":");
            for (int i = 0; i < size; i++) {
                Node node = queue.remove();
                System.out.println("  " + node.employee);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            level++;
        }
    }
    public int height() { return height(root); }
    private int height(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }
    public int getCount() { return count; }
}
