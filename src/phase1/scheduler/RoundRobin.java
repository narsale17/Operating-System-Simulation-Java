package phase1.scheduler;

import java.util.*;
import phase1.model.Process;


public class RoundRobin {

    private int timeQuantum;

    public RoundRobin(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    public void simulate(List<Process> originalList) {
        List<Process> processList = new ArrayList<>();
        for (Process p : originalList) {
            processList.add(new Process(p.pid, p.arrivalTime, p.burstTime, p.priority));
        }

        int currentTime = 0;
        Queue<Process> queue = new LinkedList<>();
        List<String> executionOrder = new ArrayList<>();
        List<Process> arrived = new ArrayList<>();

        while (arrived.size() < processList.size()) {
            for (Process p : processList) {
                if (p.arrivalTime <= currentTime && !arrived.contains(p)) {
                    queue.offer(p);
                    arrived.add(p);
                }
            }
            if (queue.isEmpty()) {
                currentTime++;
            } else {
                break;
            }
        }

        while (!queue.isEmpty()) {
            Process current = queue.poll();

            // ✅ Track execution order
            executionOrder.add("P" + current.pid);

            int execTime = Math.min(timeQuantum, current.remainingTime);
            current.remainingTime -= execTime;
            currentTime += execTime;

            for (Process p : processList) {
                if (p.arrivalTime <= currentTime && !arrived.contains(p)) {
                    queue.offer(p);
                    arrived.add(p);
                }
            }

            if (current.remainingTime > 0) {
                queue.offer(current);
            } else {
                current.completionTime = currentTime;
                current.turnaroundTime = current.completionTime - current.arrivalTime;
                current.waitingTime = current.turnaroundTime - current.burstTime;
            }
        }

        // ✅ Print Execution Order
        System.out.println("\nOrder of Execution (Round Robin turns):");
        for (String p : executionOrder) {
            System.out.print("[" + p + "] -> ");
        }
        System.out.println("END");

        printResults(processList);
    }

    private void printResults(List<Process> processList) {
        System.out.println("\n--- Round Robin Scheduling Results ---");
        System.out.printf("%-5s %-10s %-10s %-10s %-10s\n", "PID", "Arrival", "Burst", "Waiting", "Turnaround");
        for (Process p : processList) {
            System.out.printf("%-5d %-10d %-10d %-10d %-10d\n",
                    p.pid, p.arrivalTime, p.burstTime, p.waitingTime, p.turnaroundTime);
        }
    }
}
