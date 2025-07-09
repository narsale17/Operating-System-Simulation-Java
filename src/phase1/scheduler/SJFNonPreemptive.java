package phase1.scheduler;

import java.util.*;
import phase1.model.Process;


public class SJFNonPreemptive {

    public void simulate(List<Process> originalList) {
        List<Process> processList = new ArrayList<>();
        for (Process p : originalList) {
            processList.add(new Process(p.pid, p.arrivalTime, p.burstTime, p.priority));
        }

        int currentTime = 0, completed = 0;
        int n = processList.size();
        List<Process> finished = new ArrayList<>();
        List<String> executionOrder = new ArrayList<>();

        while (completed < n) {
            List<Process> ready = new ArrayList<>();
            for (Process p : processList) {
                if (p.arrivalTime <= currentTime && !finished.contains(p)) {
                    ready.add(p);
                }
            }

            if (ready.isEmpty()) {
                currentTime++;
                continue;
            }

            ready.sort(Comparator.comparingInt(p -> p.burstTime));
            Process current = ready.get(0);

            // ✅ Track execution
            executionOrder.add("P" + current.pid);

            current.waitingTime = currentTime - current.arrivalTime;
            currentTime += current.burstTime;
            current.completionTime = currentTime;
            current.turnaroundTime = current.completionTime - current.arrivalTime;

            finished.add(current);
            completed++;
        }

        // ✅ Print Execution Order
        System.out.println("\nOrder of Execution:");
        for (String p : executionOrder) {
            System.out.print("[" + p + "] -> ");
        }
        System.out.println("END");

        printResults(processList);
    }


    private void printResults(List<Process> processList) {
        System.out.println("\n--- SJF (Non-Preemptive) Scheduling Results ---");
        System.out.printf("%-5s %-10s %-10s %-10s %-10s\n", "PID", "Arrival", "Burst", "Waiting", "Turnaround");
        for (Process p : processList) {
            System.out.printf("%-5d %-10d %-10d %-10d %-10d\n",
                    p.pid, p.arrivalTime, p.burstTime, p.waitingTime, p.turnaroundTime);
        }
    }
}
