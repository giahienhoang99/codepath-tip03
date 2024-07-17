package week5_stacks_queues_trees.hackerrank;
import java.util.*;

public class BSTIterator {
    private static Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        pushAllLeft(root);
    }

    // return whether we have a next smallest number
    public static boolean hasNext() {
        return !stack.isEmpty();
    }

    // return the next smallest number
    public static int next() {
        TreeNode node = stack.pop();
        int result = node.val;
        if (node.right != null) {
            pushAllLeft(node.right);
        }
        return result;
    }

    private static void pushAllLeft(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic Test
        TreeNode root1 = new TreeNode(7);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(15);
        root1.right.left = new TreeNode(9);
        root1.right.right = new TreeNode(20);

        BSTIterator iterator1 = new BSTIterator(root1);
        System.out.println("Basic Test:");
        while (iterator1.hasNext()) {
            System.out.print(iterator1.next() + " ");
        }
        System.out.println();

        // Test Case 2: Empty Tree
        TreeNode root2 = null;
        BSTIterator iterator2 = new BSTIterator(root2);
        System.out.println("Empty Tree Test:");
        System.out.println("Has Next: " + iterator2.hasNext());

        // Test Case 3: Complex Tree
        TreeNode root3 = new TreeNode(10);
        root3.left = new TreeNode(5);
        root3.right = new TreeNode(20);
        root3.left.left = new TreeNode(3);
        root3.left.right = new TreeNode(7);
        root3.right.left = new TreeNode(15);
        root3.right.right = new TreeNode(30);

        BSTIterator iterator3 = new BSTIterator(root3);
        System.out.println("Complex Tree Test:");
        while (iterator3.hasNext()) {
            System.out.print(iterator3.next() + " ");
        }
        System.out.println();

        // Test Case 4: Single Node Tree
        TreeNode root4 = new TreeNode(1);
        BSTIterator iterator4 = new BSTIterator(root4);
        System.out.println("Single Node Tree Test:");
        while (iterator4.hasNext()) {
            System.out.print(iterator4.next() + " ");
        }
        System.out.println();
    }
}
