package week5_stacks_queues_trees.codepath_problems;
import java.util.*;
public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        if (depth(root) == -1) {
            return false;
        }

        return true;
    }

    public int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = depth(root.left);
        int right = depth(root.right);

        if (left == -1 || right == -1) {
            return -1;
        }

        if (Math.max(left, right) - Math.min(left, right) > 1) {
            return -1;
        }

        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        BalancedBinaryTree solution = new BalancedBinaryTree();

        // Test Case 1: Balanced Tree
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(3);
        root1.right.left = new TreeNode(3);
        root1.right.right = new TreeNode(3);

        System.out.println("Test Case 1 - Balanced Tree: Is balanced? " + solution.isBalanced(root1));  // Output: true

        // Test Case 2: Unbalanced Tree
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        root2.left.left = new TreeNode(3);
        root2.left.left.left = new TreeNode(4);

        System.out.println("Test Case 2 - Unbalanced Tree: Is balanced? " + solution.isBalanced(root2));  // Output: false

        // Test Case 3: Null Tree (Empty Tree)
        TreeNode root3 = null;

        System.out.println("Test Case 3 -  Null Tree (Empty Tree): Is balanced? " + solution.isBalanced(root3));  // Output: true (Empty tree is considered balanced)
    }
}
