package week2_linkedlist_strings_arrays;

import java.util.HashSet;
import java.util.Set;

public class SetMatrixZeroes {
    private void setRowZero(int[][] matrix, int row) {
        for (int i = 0; i < matrix[row].length; i++) {
            matrix[row][i] = 0;
        }
    }
    private void setColZero(int[][] matrix, int col) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][col] = 0;
        }
    }
    public void setZeroesHashSet(int[][] matrix) {
        Set<Integer> cols = new HashSet<>();
        Set<Integer> rows = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    if (!rows.contains(i)) {
                        rows.add(i);
                    }
                    if (!cols.contains(j)) {
                        cols.add(j);
                    }
                }
            }
        }
        for (int col : cols) {
            setColZero(matrix, col);
        }
        for (int row : rows) {
            setRowZero(matrix, row);
        }
    }

    public void setZeroesMarker(int[][] matrix) {
        // 1) Is first row/col zero?
        // 2) Add markers (first row/col) based on cell values
        // 3) Zero out the cells based on the markers in step2
        // 4) Handle first row/col
        int rows = matrix.length;
        int cols = matrix[0].length;

        // 1) Is first row/col zero?
        boolean first_row_has_zero = false;
        boolean first_col_has_zero = false;
        for (int i = 0; i < cols; i++) {
            if (matrix[0][i] == 0) first_row_has_zero = true;
        }
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) first_col_has_zero = true;
        }

        // 2) Add markers (first row/col) based on cell values
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 3) Zero out the cells based on the markers in step2
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 4) Handle first row/col
        for (int col = 0; col < cols; col++) {
            if (first_row_has_zero) matrix[0][col] = 0;
        }
        for (int row = 0; row < rows; row++) {
            if (first_col_has_zero) matrix[row][0] = 0;
        }
    }
}
