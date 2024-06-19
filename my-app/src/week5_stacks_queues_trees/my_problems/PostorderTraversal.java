package week5_stacks_queues_trees.my_problems;

import java.util.ArrayList;
import java.util.List;

public class PostorderTraversal {
    private List<Integer> preorderHelper(TreeNode root, List<Integer> result) {
        // base case
        if (root == null) return result;

        // recursive on left subtree
        result = preorderHelper(root.left, result);
        // recursive on right subtree
        result = preorderHelper(root.right, result);
        // track root.val
        result.add(root.val);

        return result;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        result = preorderHelper(root, result);
        return result;
    }
}
