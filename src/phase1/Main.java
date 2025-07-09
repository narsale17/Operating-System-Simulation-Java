package phase1;

import phase1.scheduler.*;
import phase1.model.Process;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Process> processes = new ArrayList<>();

        // Hardcoded Test Case (Uncomment to use manual input instead)
        /*
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("Process " + (i + 1) + ":");
            System.out.print("PID: ");
            int pid = sc.nextInt();
            System.out.print("Arrival Time: ");
            int at = sc.nextInt();
            System.out.print("Burst Time: ");
            int bt = sc.nextInt();
            System.out.print("Priority (lower is higher): ");
            int pri = sc.nextInt();
            processes.add(new Process(pid, at, bt, pri));
        }
        */

        // Predefined Test Case
        processes.add(new Process(1, 0, 5, 2));
        processes.add(new Process(2, 1, 3, 1));
        processes.add(new Process(3, 2, 8, 4));
        processes.add(new Process(4, 3, 6, 3));

        // Scheduling algorithm selection
        System.out.println("\nChoose Scheduling Algorithm:");
        System.out.println("1. FCFS");
        System.out.println("2. SJF (Non-Preemptive)");
        System.out.println("3. SJF (Preemptive)");
        System.out.println("4. Priority (Non-Preemptive)");
        System.out.println("5. Priority (Preemptive)");
        System.out.println("6. Round Robin");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1 -> new FCFS().simulate(processes);
            case 2 -> new SJFNonPreemptive().simulate(processes);
            case 3 -> new SJFPreemptive().simulate(processes);
            case 4 -> new PriorityNonPreemptive().simulate(processes);
            case 5 -> new PriorityPreemptive().simulate(processes);
            case 6 -> {
                System.out.print("Enter Time Quantum: ");
                int tq = sc.nextInt();
                new RoundRobin(tq).simulate(processes);
            }
            default -> System.out.println("Invalid choice.");
        }

        sc.close();
    }
}
