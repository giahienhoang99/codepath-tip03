package week6_graphs.codepath;

public class FindCircleNum {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        if (n == 1) return 1;
        boolean[] visited = new boolean[n];
        int numProvinces = 0;

        for (int x = 0; x < n; x++) {
            if (!visited[x]) {
                numProvinces++;
                dfs(isConnected, x, visited);
            }
        }

        return numProvinces;
    }

    public void dfs(int[][] matrix, int x, boolean[] visited) {
        if (visited[x]) return;
        visited[x] = true;
        for (int i = 0; i < matrix[x].length; i++) {
            if (matrix[x][i] == 1 && !visited[i]) {
                dfs(matrix, i, visited);
            }
        }
    }

    public static void main (String[] args) {
        FindCircleNum circleNum = new FindCircleNum();
        // Test case 1: Single city
        int[][] isConnected1 = {
                {1}
        };
        System.out.println("Test 1: " + (circleNum.findCircleNum(isConnected1) == 1 ? "Passed" : "Failed"));

        // Test case 2: All cities connected
        int[][] isConnected2 = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };
        System.out.println("Test 2: " + (circleNum.findCircleNum(isConnected2) == 1 ? "Passed" : "Failed"));

        // Test case 3: No cities connected
        int[][] isConnected3 = {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        System.out.println("Test 3: " + (circleNum.findCircleNum(isConnected3) == 3 ? "Passed" : "Failed"));

        // Test case 4: Multiple provinces
        int[][] isConnected4 = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };
        System.out.println("Test 4: " + (circleNum.findCircleNum(isConnected4) == 2 ? "Passed" : "Failed"));

        // Test case 5: Complex case with various connections
        int[][] isConnected5 = {
                {1, 0, 0, 1},
                {0, 1, 1, 0},
                {0, 1, 1, 1},
                {1, 0, 1, 1}
        };
        System.out.println("Test 5: " + (circleNum.findCircleNum(isConnected5) == 1 ? "Passed" : "Failed"));

        // Test case 6: Larger matrix with multiple provinces
        int[][] isConnected6 = {
                {1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 1, 0, 1},
                {0, 0, 0, 1, 1},
                {0, 0, 1, 1, 1}
        };
        System.out.println("Test 6: " + (circleNum.findCircleNum(isConnected6) == 3 ? "Passed" : "Failed"));
    }
}
