package week6_graphs.codepath;
import java.util.*;
public class FloodFill {
    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        // starting pixel already = color
        if (image[sr][sc] == color) {
            return image;
        }

        int[] dx = new int[]{ 0, 0, 1,-1};
        int[] dy = new int[]{ 1,-1, 0, 0};

        Queue<int[]> q = new LinkedList<>();
        int initColor = image[sr][sc];
        q.add(new int[]{sr, sc});

        while (!q.isEmpty()) {
            int n = q.size();
            for (int i = 0; i < n; i++) {
                int[] p = q.poll();
                image[p[0]][p[1]] = color;
                for (int j = 0; j < 4; j++) {
                    int x = p[0] + dx[j];
                    int y = p[1] + dy[j];
                    // index bounds checking + diff from initColor
                    if (x < 0 || x >= image.length || y < 0 || y >= image[0].length || image[x][y] != initColor) {
                        continue;
                    }

                    image[x][y] = color;
                    q.add(new int[]{x, y});
                }
            }
        }
        return image;
    }

    public static void printImg(int[][] img) {
        for (int[] row : img) {
            for (int i : row) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("Test 1:");
        int[][] image1 = {{1, 1 ,1},
                          {1, 1, 0},
                          {1, 0, 1}};
        printImg(image1);
        floodFill(image1, 1, 1, 2);
        printImg(image1);

        System.out.println("Test 2:");
        int[][] image2 = {{1, 1 ,1},
                          {1, 1, 1},
                          {1, 1, 1}};
        printImg(image2);
        floodFill(image1, 1, 1, 1);
        printImg(image2);
    }
}
