package OAs;

// Databricks - CodeSignal - 3rd Question - General Coding Framework
public class DiagonalPattern {
    public static int solution(int[][] matrix) {
        int maxLength = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Directions: [row_direction, col_direction]
        int[][] directions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        // Traverse each cell in the matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // If the current cell is 1, try all diagonal directions
                if (matrix[i][j] == 1) {
                    for (int[] dir : directions) {
                        int currentLength = findDiagonalLength(matrix, i, j, dir);
                        maxLength = Math.max(maxLength, currentLength);
                    }
                }
            }
        }

        return maxLength;
    }

    private static int findDiagonalLength(int[][] matrix, int startRow, int startCol, int[] direction) {
        int length = 0;
        int row = startRow;
        int col = startCol;
        int patternIndex = 0;
        int[] pattern = {1, 2, 0};

        while (row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length) {
            if (matrix[row][col] != pattern[patternIndex]) {
                break;
            }
            length++;
            row += direction[0];
            col += direction[1];
            patternIndex = (patternIndex + 1) % 3;
        }

        // Check if the diagonal ends at the matrix border
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) {
            return length;
        }

        return 0; // If the diagonal does not end at the border, return 0
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 0, 1, 2},
                {0, 2, 2, 2},
                {2, 1, 0, 1}
        };

        int result = solution(matrix);
        System.out.println("Test Case 1: " + result);

        int[][] matrix2 = {
                {1, 2, 0, 2},
                {2, 0, 2, 0},
                {0, 2, 1, 2},
                {2, 0, 2, 0}
        };
        System.out.println("Test Case 2: " + solution(matrix2)); // Expected output: 4

        int[][] matrix3 = {
                {1, 0, 2, 0},
                {0, 1, 2, 2},
                {2, 2, 0, 1},
                {1, 0, 2, 0}
        };
        System.out.println("Test Case 3: " + solution(matrix3)); // Expected output: 3

        int[][] matrix4 = {
                {2, 2, 0, 2},
                {0, 1, 2, 0},
                {1, 0, 2, 0},
                {0, 1, 2, 0}
        };
        System.out.println("Test Case 4: " + solution(matrix4)); // Expected output: 2

        int[][] matrix5 = {
                {1, 2, 0, 2, 0, 2},
                {2, 0, 2, 0, 1, 2},
                {0, 2, 0, 2, 0, 2},
                {2, 0, 1, 2, 0, 1},
                {0, 2, 0, 1, 2, 0},
                {2, 0, 2, 0, 2, 1}
        };
        System.out.println("Test Case 5: " + solution(matrix5)); // Expected output: 6
    }
}
