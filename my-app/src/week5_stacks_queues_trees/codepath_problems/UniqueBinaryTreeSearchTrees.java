package week5_stacks_queues_trees.codepath_problems;

public class UniqueBinaryTreeSearchTrees {
    public int numTrees(int n) {
        // where tab[i] is the number of unique trees of size i
        int[] tab = new int[n + 1];

        // Base cases
        tab[0] = 1;
        tab[1] = 1;

        // bottom-up approach
        for (int size = 2; size <= n; size++) {
            // any value from 1 to size
            for (int rootVal = 1; rootVal <= size; rootVal++) {
                int leftSubTreeSize = rootVal - 1;
                int rightSubTreeSize = size - rootVal;

                tab[size] += tab[leftSubTreeSize] * tab[rightSubTreeSize];
            }
        }
        return tab[n];
    }
}
