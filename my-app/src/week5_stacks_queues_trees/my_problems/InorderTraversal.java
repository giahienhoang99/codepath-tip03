package week5_stacks_queues_trees.my_problems;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class InorderTraversal {
    public List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            // Reach the leftmost node of the current node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // Current must be null at this point
            current = stack.pop();
            result.add(current.val); // Visit the node

            // We have visited the node and its left subtree
            // Now, it's right subtree's turn
            current = current.right;
        }

        return result;
    }
    private List<Integer> inorderHelper(TreeNode root, List<Integer> result) {
        // base case
        if (root == null) {
            return result;
        }
        // recursive on left subtree
        result = inorderHelper(root.left, result);
        // record root.val
        result.add(root.val);
        // recursive on right subtree
        result = inorderHelper(root.right, result);

        return result;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        result = inorderHelper(root, result);
        return result;
    }

    public static void main(String[] args) {
        InorderTraversal solution = new InorderTraversal();

        // Test 1: Empty Tree
        TreeNode root1 = null;
        List<Integer> result1 = solution.inorderTraversal(root1);
        List<Integer> result1it = solution.inorderTraversalIterative(root1);
        System.out.println(result1); // Expected: []
        System.out.println(result1it); // Expected: []

        // Test 2: Tree with only one node
        TreeNode root2 = new TreeNode(1);
        List<Integer> result2 = solution.inorderTraversal(root2);
        List<Integer> result2it = solution.inorderTraversalIterative(root2);
        System.out.println(result2); // Expected: [1]
        System.out.println(result2it); // Expected: [1]

        // Test 3: Tree with nodes only on the left
        TreeNode root3 = new TreeNode(3);
        root3.left = new TreeNode(2);
        root3.left.left = new TreeNode(1);
        List<Integer> result3 = solution.inorderTraversal(root3);
        List<Integer> result3it = solution.inorderTraversalIterative(root3);
        System.out.println(result3); // Expected: [1, 2, 3]
        System.out.println(result3it); // Expected: [1, 2, 3]

        // Test 4: Tree with nodes only on the right
        TreeNode root4 = new TreeNode(1);
        root4.right = new TreeNode(2);
        root4.right.right = new TreeNode(3);
        List<Integer> result4 = solution.inorderTraversal(root4);
        List<Integer> result4it = solution.inorderTraversalIterative(root4);
        System.out.println(result4); // Expected: [1, 2, 3]
        System.out.println(result4it); // Expected: [1, 2, 3]

        // Test 5: Balanced Tree
        TreeNode root5 = new TreeNode(2);
        root5.left = new TreeNode(1);
        root5.right = new TreeNode(3);
        List<Integer> result5 = solution.inorderTraversal(root5);
        List<Integer> result5it = solution.inorderTraversalIterative(root5);
        System.out.println(result5); // Expected: [1, 2, 3]
        System.out.println(result5it); // Expected: [1, 2, 3]

        // Test 6: Unbalanced Tree
        TreeNode root6 = new TreeNode(4);
        root6.left = new TreeNode(2);
        root6.right = new TreeNode(5);
        root6.left.left = new TreeNode(1);
        root6.left.right = new TreeNode(3);
        List<Integer> result6 = solution.inorderTraversal(root6);
        List<Integer> result6it = solution.inorderTraversalIterative(root6);
        System.out.println(result6); // Expected: [1, 2, 3, 4, 5]
        System.out.println(result6it); // Expected: [1, 2, 3, 4, 5]
    }
}
