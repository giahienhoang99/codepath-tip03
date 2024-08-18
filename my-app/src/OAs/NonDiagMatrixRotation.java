package OAs;

public class NonDiagMatrixRotation {
    public static void main(String[] args) {
        // Test case 1
        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] expected1 = {
                {1, 4, 3},
                {8, 5, 2},
                {7, 6, 9}
        };
        testRotation(matrix1, 1, expected1);

        // Test case 2
        int[][] matrix2 = {
                {1, 2, 1},
                {5, 1, 3},
                {1, 4, 1}
        };
        int[][] expected2 = {
                {1, 4, 1},
                {3, 1, 5},
                {1, 2, 1}
        };
        testRotation(matrix2, 2, expected2);

        // Test case 3
        int[][] matrix3 = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
        };
        int[][] expected3 = {
                {1, 16, 11, 6, 5},
                {22, 7, 12, 9, 2},
                {23, 18, 13, 8, 3},
                {24, 17, 14, 19, 4},
                {21, 20, 15, 10, 25}
        };
        testRotation(matrix3, 1, expected3);

        // Test case 4
        int[][] matrix4 = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
        };
        int[][] expected4 = {
                {1, 24, 23, 22, 5},
                {20, 7, 18, 9, 16},
                {15, 14, 13, 12, 11},
                {10, 17, 8, 19, 6},
                {21, 4, 3, 2, 25}
        };
        testRotation(matrix4, 2, expected4);

        // Add more test cases as needed
    }

    public static void testRotation(int[][] matrix, int turns, int[][] expected) {
        NonDiagMatrixRotation sol = new NonDiagMatrixRotation();
        int[][] result = sol.solution(matrix, turns);

        if (arraysAreEqual(result, expected)) {
            System.out.println("Test passed");
        } else {
            System.out.println("Test failed");
            System.out.println("Expected:");
            printMatrix(expected);
            System.out.println("Got:");
            printMatrix(result);
        }
    }

    public static boolean arraysAreEqual(int[][] arr1, int[][] arr2) {
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i].length != arr2[i].length) return false;
            for (int j = 0; j < arr1[i].length; j++) {
                if (arr1[i][j] != arr2[i][j]) return false;
            }
        }
        return true;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public int[][] solution(int[][] matrix, int turns) {
        int n = matrix.length;

        // Normalize the number of turns, since rotating 4 times is equivalent to no rotation.
        turns = turns % 4;

        for (int t = 0; t < turns; t++) {
            // Create a copy of the matrix to store the rotated values
            int[][] newMatrix = new int[n][n];

            // Copy the diagonals as they don't change
            for (int i = 0; i < n; i++) {
                newMatrix[i][i] = matrix[i][i];
                newMatrix[i][n - 1 - i] = matrix[i][n - 1 - i];
            }

            // Rotate the segments
            for (int i = 0; i < n / 2; i++) {
                for (int j = i + 1; j < n - 1 - i; j++) {
                    // Top-left to top-right
                    newMatrix[i][j] = matrix[n - 1 - j][i];

                    // Top-right to bottom-right
                    newMatrix[j][n - 1 - i] = matrix[i][j];

                    // Bottom-right to bottom-left
                    newMatrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];

                    // Bottom-left to top-left
                    newMatrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                }
            }

            // Update the matrix to the new rotated matrix
            matrix = newMatrix;
        }

        return matrix;
    }
}
