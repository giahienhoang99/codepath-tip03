package week5_stacks_queues_trees.my_problems;
import com.sun.source.tree.Tree;

import java.util.*;

public class LevelOrderTraversal {
    public static void printLevels(TreeNode root) {
        if (root == null) {
            return;
        }

        // fifo - queue for bfs on tree
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            TreeNode temp = q.poll();
            System.out.print(temp.val + " ");

            if (temp.left != null) {
                q.add(temp.left);
            }

            if (temp.right != null) {
                q.add(temp.right);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.print("Level order traversal of binary tree is: ");
        printLevels(root);
    }
}
