package OAs;
import java.util.Arrays;

public class JobExecution {

    public static int findMinimumOperations(int[] executionTime, int x, int y) {
        int n = executionTime.length;
        int operations = 0;

        while (true) {
            // Find the maximum remaining time
            int maxTimeIndex = -1;
            int maxTime = -1;
            for (int i = 0; i < n; i++) {
                if (executionTime[i] > 0 && executionTime[i] > maxTime) {
                    maxTime = executionTime[i];
                    maxTimeIndex = i;
                }
            }

            // If all jobs are completed
            if (maxTimeIndex == -1) {
                break;
            }

            // Execute the major job for x seconds
            executionTime[maxTimeIndex] -= x;

            // Execute all other jobs for y seconds
            for (int i = 0; i < n; i++) {
                if (i != maxTimeIndex && executionTime[i] > 0) {
                    executionTime[i] -= y;
                }
            }

            // Count this operation
            operations++;

            // Remove completed jobs (set execution time to 0 if negative)
            for (int i = 0; i < n; i++) {
                if (executionTime[i] < 0) {
                    executionTime[i] = 0;
                }
            }
        }

        return operations;
    }

    public static void main(String[] args) {
        int[] executionTime = {3, 4, 1, 7, 6};
        int x = 4;
        int y = 2;
        int result = findMinimumOperations(executionTime, x, y);
        System.out.println("Minimum number of operations: " + result); // Output should match the example given.
    }
}

