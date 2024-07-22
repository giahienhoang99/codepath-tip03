package week6_graphs.hackerrank;

import java.util.*;

public class WallsAndGates {
    // Define constants for the problem
    private static final int EMPTY = Integer.MAX_VALUE;
    private static final int GATE = 0;
    private static final int WALL = -1;
    private static final int[] DIRECTIONS = {-1, 0, 1, 0, -1};

    public static void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        if (m == 0) return;
        int n = rooms[0].length;

        Queue<int[]> queue = new LinkedList<>();

        // Enqueue all gates
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == GATE) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        // Perform BFS from each gate
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int row = point[0];
            int col = point[1];
            for (int i = 0; i < 4; i++) {
                int r = row + DIRECTIONS[i];
                int c = col + DIRECTIONS[i + 1];
                if (r < 0 || c < 0 || r >= m || c >= n || rooms[r][c] != EMPTY) {
                    continue;
                }
                rooms[r][c] = rooms[row][col] + 1;
                queue.add(new int[]{r, c});
            }
        }
    }

    public static void main(String[] args) {
        int INF = Integer.MAX_VALUE;
        int[][] rooms = {
                {INF, -1, 0, INF},
                {INF, INF, INF, -1},
                {INF, -1, INF, -1},
                {0, -1, INF, INF}
        };

        wallsAndGates(rooms);

        // Print the updated rooms array
        for (int[] row : rooms) {
            System.out.println(Arrays.toString(row));
        }
    }
}

