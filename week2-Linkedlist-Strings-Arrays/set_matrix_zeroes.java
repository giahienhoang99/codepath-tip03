class Solution {
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
    public void setZeroes(int[][] matrix) {
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
}