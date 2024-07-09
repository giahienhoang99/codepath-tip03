package week5_stacks_queues_trees.codepath_problems;
import java.util.*;

public class InvertBinaryTree {
    private static void invert(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode tempLeft = root.left;
        root.left = root.right;
        root.right = tempLeft;

        invert(root.left);
        invert(root.right);
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }
        invert(root);
        return root;
    }

    public static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("[]");
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<String> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current != null) {
                result.add(String.valueOf(current.val));
                queue.add(current.left);
                queue.add(current.right);
            } else {
                result.add("null");
            }
        }
        // Remove trailing "null" elements for clearer visualization
        while (result.get(result.size() - 1).equals("null")) {
            result.remove(result.size() - 1);
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        InvertBinaryTree solution = new InvertBinaryTree();

        // Test Case 1: [4,2,7,1,3,6,9]
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(7);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(3);
        root1.right.left = new TreeNode(6);
        root1.right.right = new TreeNode(9);

        System.out.print("Original Tree 1: ");
        printTree(root1);
        TreeNode invertedRoot1 = solution.invertTree(root1);
        System.out.print("Inverted Tree 1: ");
        printTree(invertedRoot1); // Expected: [4,7,2,9,6,3,1]

        // Test Case 2: [2,1,3]
        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(3);

        System.out.print("Original Tree 2: ");
        printTree(root2);
        TreeNode invertedRoot2 = solution.invertTree(root2);
        System.out.print("Inverted Tree 2: ");
        printTree(invertedRoot2); // Expected: [2,3,1]

        // Test Case 3: []
        TreeNode root3 = null;

        System.out.print("Original Tree 3: ");
        printTree(root3);
        TreeNode invertedRoot3 = solution.invertTree(root3);
        System.out.print("Inverted Tree 3: ");
        printTree(invertedRoot3); // Expected: []
    }
}
