package phase2.replacement;

import java.util.*;

public class ReplacementSimulation {
    public static void run(Scanner scanner) {
        System.out.println("\n--- Page Replacement Simulation ---");

        System.out.print("Enter number of frames in memory: ");
        int numFrames = scanner.nextInt();

        System.out.print("Enter page reference sequence (space-separated): ");
        scanner.nextLine(); // consume leftover newline
        String[] inputs = scanner.nextLine().trim().split("\\s+");
        List<Integer> pageReferences = new ArrayList<>();
        for (String input : inputs) {
            pageReferences.add(Integer.parseInt(input));
        }

        System.out.println("Choose Replacement Algorithm:");
        System.out.println("1. FIFO");
        System.out.println("2. LRU");
        int choice = scanner.nextInt();

        ReplacementAlgorithm algorithm = (choice == 2) ? ReplacementAlgorithm.LRU : ReplacementAlgorithm.FIFO;

        PageReplacementManager manager = new PageReplacementManager(numFrames, algorithm);
        manager.simulate(pageReferences);

        System.out.println("\n--- Simulation Summary ---");
        System.out.println("Total Page Faults: " + manager.getPageFaults());
        System.out.println("Total Page Hits:   " + manager.getPageHits());
    }
}
