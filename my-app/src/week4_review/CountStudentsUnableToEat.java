package week4_review;
import java.util.*;

public class CountStudentsUnableToEat {
    public static int countStudents(List<Integer> students, List<Integer> sandwiches) {
        Queue<Integer> studentQueue = new LinkedList<>(students);
        int sandwichIndex = 0;

        int attempts = 0;
        while (!studentQueue.isEmpty()) {
            if (studentQueue.peek() == sandwiches.get(sandwichIndex)) {
                studentQueue.poll();
                sandwichIndex++;
                attempts = 0;  // Reset attempts after a successful sandwich take
            } else {
                studentQueue.add(studentQueue.poll());
                attempts++;
            }

            // If all students have been attempted and none can take the current sandwich
            if (attempts == studentQueue.size()) {
                break;
            }
        }

        return studentQueue.size();
    }

    public static void main(String[] args) {
        List<Integer> students1 = Arrays.asList(6, 1, 1, 0, 0, 1, 1);
        List<Integer> sandwiches1 = Arrays.asList(6, 1, 0, 0, 0, 1, 1);
        //return the #students who couldn't get a sandwich
        System.out.println(countStudents(students1, sandwiches1)); //expected: 3

        List<Integer> students2 = Arrays.asList(4, 1, 0, 1, 0);
        List<Integer> sandwiches2 = Arrays.asList(1, 0, 4, 0, 1);
        //return the #students who couldn't get a sandwich
        System.out.println(countStudents(students2, sandwiches2)); //expected: 0
    }
}