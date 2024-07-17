package week5_stacks_queues_trees.hackerrank;

import java.util.*;
public class AverageOfLevels {
    public static List<Double> averageOfLevels(TreeNode root) {
        // Initialize a list to store the average values of each level.
        List<Double> averages = new ArrayList<>();

        // If the root is null, return the empty list of averages.
        if (root == null) {
            return averages;
        }

        // Initialize a queue for level-order traversal (BFS) and add the root to it.
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);

        // Loop until the queue is empty (all levels are processed).
        while (!q.isEmpty()) {
            // Get the number of nodes at the current level.
            int size = q.size();

            // Initialize a variable to store the sum of node values at the current level.
            double sum = 0;

            // Process each node at the current level.
            for (int i = 0; i < size; i++) {
                // Poll the front node from the queue.
                TreeNode cur = q.poll();

                // Add the value of the current node to the sum.
                sum += cur.val;

                // Add the left child of the current node to the queue if it is not null.
                if (cur.left != null) {
                    q.add(cur.left);
                }

                // Add the right child of the current node to the queue if it is not null.
                if (cur.right != null) {
                    q.add(cur.right);
                }
            }

            // Calculate the average value for the current level and add it to the list of averages.
            averages.add(sum / size);
        }
        return averages;
    }

}
