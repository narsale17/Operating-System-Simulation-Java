package phase1.scheduler;

import java.util.*;
import phase1.model.Process;


public class FCFS {

    public void simulate(List<Process> originalList) {
        List<Process> processList = new ArrayList<>();
        for (Process p : originalList) {
            processList.add(new Process(p.pid, p.arrivalTime, p.burstTime, p.priority));
        }

        processList.sort(Comparator.comparingInt(p -> p.arrivalTime));
        int currentTime = 0;
        List<String> executionOrder = new ArrayList<>();

        for (Process p : processList) {
            if (currentTime < p.arrivalTime) {
                currentTime = p.arrivalTime;
            }

            // ✅ Track execution
            executionOrder.add("P" + p.pid);

            p.waitingTime = currentTime - p.arrivalTime;
            currentTime += p.burstTime;
            p.completionTime = currentTime;
            p.turnaroundTime = p.completionTime - p.arrivalTime;
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
        System.out.println("\n--- FCFS Scheduling Results ---");
        System.out.printf("%-5s %-10s %-10s %-10s %-10s\n", "PID", "Arrival", "Burst", "Waiting", "Turnaround");
        for (Process p : processList) {
            System.out.printf("%-5d %-10d %-10d %-10d %-10d\n",
                    p.pid, p.arrivalTime, p.burstTime, p.waitingTime, p.turnaroundTime);
        }
    }
}
