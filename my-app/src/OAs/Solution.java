package OAs;

import java.util.*;
import java.io.*;
import java.util.*;
import java.text.DecimalFormat;


class ProcessSchedule {
    public int startTime;
    public int endTime;

    public ProcessSchedule(int start, int end) {
        startTime = start;
        endTime = end;
    }
}


class Dependency {
    public int pid1;
    public int pid2;

    public Dependency(int p1, int p2) {
        pid1 = p1;
        pid2 = p2;
    }
}
class Scheduler {
    private ArrayList<ProcessSchedule> processes;
    private ArrayList<Dependency> dependencies;
    private Map<Integer, ProcessSchedule> processMap;
    private Map<Integer, List<Integer>> adjacencyList;
    private Map<Integer, Integer> inDegree;

    public Scheduler(ArrayList<ProcessSchedule> processes, ArrayList<Dependency> dependencies) {
        this.processes = processes;
        this.dependencies = dependencies;
        this.processMap = new HashMap<>();
        this.adjacencyList = new HashMap<>();
        this.inDegree = new HashMap<>();

        // Initialize process map
        for (int i = 0; i < processes.size(); i++) {
            processMap.put(i + 1, processes.get(i));
            adjacencyList.put(i + 1, new ArrayList<>());
            inDegree.put(i + 1, 0);
        }

        // Build graph and in-degree count
        for (Dependency dep : dependencies) {
            adjacencyList.get(dep.pid1).add(dep.pid2);
            inDegree.put(dep.pid2, inDegree.get(dep.pid2) + 1);
        }
    }

    public void PrintSchedule() {
        List<Integer> schedule = topologicalSort();
        if (schedule == null) {
            System.out.println("No valid schedule");
            return;
        }

        // Print the schedule
        for (int pid : schedule) {
            ProcessSchedule ps = processMap.get(pid);
            System.out.println("Process " + pid + ": Start Time = " + ps.startTime + ", End Time = " + ps.endTime);
        }
    }

    private List<Integer> topologicalSort() {
        List<Integer> sortedList = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        // Add nodes with zero in-degree to the queue
        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        while (!queue.isEmpty()) {
            int u = queue.poll();
            sortedList.add(u);

            // Decrease the in-degree of adjacent nodes
            for (int v : adjacencyList.get(u)) {
                inDegree.put(v, inDegree.get(v) - 1);
                if (inDegree.get(v) == 0) {
                    queue.add(v);
                }
            }
        }

        // Check if the sorted list contains all nodes
        if (sortedList.size() != processMap.size()) {
            return null; // Graph has a cycle
        }

        return sortedList;
    }
}

public class Solution {
    public static void main(String args[] ) throws Exception {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();

        for (int i = 0; i < T; i++) {
            int N = in.nextInt();
            int M = in.nextInt();

            ArrayList<ProcessSchedule> processes = new ArrayList<ProcessSchedule>();
            for (int j = 0; j < N; j++) {
                int S = in.nextInt();
                int E = in.nextInt();
                processes.add(new ProcessSchedule(S, E));
            }

            ArrayList<Dependency> dependencies = new ArrayList<Dependency>();
            for (int k = 0; k < M; k++) {
                int P1 = in.nextInt();
                int P2 = in.nextInt();
                dependencies.add(new Dependency(P1, P2));
            }

            Scheduler scheduler = new Scheduler(processes, dependencies);
            scheduler.PrintSchedule();
        }
    }
}