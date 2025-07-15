package phase2;

import java.util.Scanner;

// Import simulation runners from each part
import phase2.allocation.AllocationSimulation;
import phase2.paging.PagingSimulation;
import phase2.replacement.ReplacementSimulation;
import phase2.segmentation.SegmentationSimulation;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n====== Memory Management Simulation ======");
            System.out.println("1. Partition Allocation");
            System.out.println("2. Paging");
            System.out.println("3. Page Replacement (Virtual Memory)");
            System.out.println("4. Segmentation");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    AllocationSimulation.run(scanner);
                    break;
                case 2:
                    PagingSimulation.run(scanner);
                    break;
                case 3:
                    ReplacementSimulation.run(scanner);
                    break;
                case 4:
                    SegmentationSimulation.run(scanner);
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }
}
