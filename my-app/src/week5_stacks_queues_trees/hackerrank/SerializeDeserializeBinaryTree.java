package week5_stacks_queues_trees.hackerrank;

import java.util.*;
public class SerializeDeserializeBinaryTree {
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) return "";

            StringBuilder sb = new StringBuilder();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node == null) {
                    sb.append("null,");
                } else {
                    sb.append(node.val).append(",");
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }

            // Remove the trailing comma
            sb.setLength(sb.length() - 1);

            return sb.toString();
        }

        // Decodes your encoded data to a tree.
        public TreeNode deserialize(String data) {
            if (data == null || data.isEmpty()) return null;

            String[] values = data.split(",");
            Queue<TreeNode> queue = new LinkedList<>();

            TreeNode root = new TreeNode(Integer.parseInt(values[0]));
            queue.offer(root);

            int i = 1;
            while (!queue.isEmpty()) {
                TreeNode parent = queue.poll();

                if (!values[i].equals("null")) {
                    TreeNode left = new TreeNode(Integer.parseInt(values[i]));
                    parent.left = left;
                    queue.offer(left);
                }
                i++;

                if (!values[i].equals("null")) {
                    TreeNode right = new TreeNode(Integer.parseInt(values[i]));
                    parent.right = right;
                    queue.offer(right);
                }
                i++;
            }

            return root;
        }
}
