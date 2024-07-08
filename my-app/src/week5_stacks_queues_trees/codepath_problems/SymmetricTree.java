package week5_stacks_queues_trees.codepath_problems;

import java.util.*;
public class SymmetricTree {
    private static boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        return (left.val == right.val) && isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    public static void main(String[] args) {
        SymmetricTree solution = new SymmetricTree();

        // First test case: [1, 2, 2, 2, null, 2]
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(2);
        root1.left.right = null;
        root1.right.left = new TreeNode(2);

        System.out.println("Tree 1: [1, 2, 2, 2, null, 2]");
        System.out.println("Test Case 1: " + solution.isSymmetric(root1));  // Output: false

        // Second test case: [1, 2, 2, null, 3, null, 3]
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        root2.left.left = null;
        root2.left.right = new TreeNode(3);
        root2.right.left = null;
        root2.right.right = new TreeNode(3);

        System.out.println("Tree 2: [1, 2, 2, null, 3, null, 3]");
        System.out.println("Test Case 2: " + solution.isSymmetric(root2));  // Output: false

        // Third test case: [1, 2, 2, 3, 4, 4, 3]
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(2);
        root3.left.left = new TreeNode(3);
        root3.left.right = new TreeNode(4);
        root3.right.left = new TreeNode(4);
        root3.right.right = new TreeNode(3);

        System.out.println("Tree 3: [1, 2, 2, 3, 4, 4, 3]");
        System.out.println("Test Case 3: " + solution.isSymmetric(root3));  // Output: true
    }
}
