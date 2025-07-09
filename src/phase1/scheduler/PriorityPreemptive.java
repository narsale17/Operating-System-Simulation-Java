package phase1.scheduler;

import java.util.*;
import phase1.model.Process;


public class PriorityPreemptive {

    public void simulate(List<Process> originalList) {
        List<Process> processList = new ArrayList<>();
        for (Process p : originalList) {
            processList.add(new Process(p.pid, p.arrivalTime, p.burstTime, p.priority));
        }

        int currentTime = 0, completed = 0;
        int n = processList.size();
        List<String> executionOrder = new ArrayList<>();
        Process current = null;

        while (completed < n) {
            List<Process> ready = new ArrayList<>();
            for (Process p : processList) {
                if (p.arrivalTime <= currentTime && p.remainingTime > 0) {
                    ready.add(p);
                }
            }

            if (ready.isEmpty()) {
                currentTime++;
                continue;
            }

            // Sort by priority (lower value = higher priority)
            ready.sort(Comparator.comparingInt(p -> p.priority));
            Process selected = ready.get(0);

            // ✅ Log only if context switched
            if (current == null || current.pid != selected.pid) {
                executionOrder.add("P" + selected.pid);
            }

            current = selected;
            current.remainingTime--;
            currentTime++;

            if (current.remainingTime == 0) {
                current.completionTime = currentTime;
                current.turnaroundTime = current.completionTime - current.arrivalTime;
                current.waitingTime = current.turnaroundTime - current.burstTime;
                completed++;
            }
        }

        // ✅ Print Execution Order
        System.out.println("\nOrder of Execution (by time slices):");
        for (String p : executionOrder) {
            System.out.print("[" + p + "] -> ");
        }
        System.out.println("END");

        printResults(processList);
    }

    private void printResults(List<Process> processList) {
        System.out.println("\n--- Priority (Preemptive) Scheduling Results ---");
        System.out.printf("%-5s %-10s %-10s %-10s %-10s %-10s\n", "PID", "Arrival", "Burst", "Priority", "Waiting", "Turnaround");
        for (Process p : processList) {
            System.out.printf("%-5d %-10d %-10d %-10d %-10d %-10d\n",
                    p.pid, p.arrivalTime, p.burstTime, p.priority, p.waitingTime, p.turnaroundTime);
        }
    }
}
