package phase2.allocation;

import java.util.*;

public class AllocationSimulation {
    public static void run(Scanner scanner) {
        System.out.println("\n--- Partition Allocation Simulation ---");

        System.out.print("Enter total memory size: ");
        int memorySize = scanner.nextInt();

        System.out.print("Enter number of processes: ");
        int n = scanner.nextInt();

        List<Integer> processSizes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter size of Process " + (i + 1) + ": ");
            processSizes.add(scanner.nextInt());
        }

        System.out.println("Choose allocation strategy:");
        System.out.println("1. First Fit");
        System.out.println("2. Best Fit");
        System.out.println("3. Worst Fit");
        int strategyChoice = scanner.nextInt();

        AllocationStrategy strategy;
        switch (strategyChoice) {
            case 1:
                strategy = AllocationStrategy.FIRST_FIT;
                break;
            case 2:
                strategy = AllocationStrategy.BEST_FIT;
                break;
            case 3:
                strategy = AllocationStrategy.WORST_FIT;
                break;
            default:
                System.out.println("Invalid strategy. Defaulting to First Fit.");
                strategy = AllocationStrategy.FIRST_FIT;
        }

        // Ask for fixed or dynamic
        System.out.print("Use fixed partitioning? (yes/no): ");
        boolean isFixed = scanner.next().equalsIgnoreCase("yes");

        List<MemoryBlock> memoryBlocks = new ArrayList<>();

        if (isFixed) {
            System.out.print("Enter number of fixed partitions: ");
            int p = scanner.nextInt();
            for (int i = 0; i < p; i++) {
                System.out.print("Enter size of partition " + (i + 1) + ": ");
                memoryBlocks.add(new MemoryBlock(scanner.nextInt()));
            }
        } else {
            // Start with one large block of free memory
            memoryBlocks.add(new MemoryBlock(memorySize));
        }

        // Perform allocation
        MemoryAllocator allocator = new MemoryAllocator(strategy, isFixed);
        allocator.allocate(memoryBlocks, processSizes);

        // Show results
        allocator.printAllocationResult(memoryBlocks);
        FragmentationAnalyzer.analyze(memoryBlocks, processSizes, isFixed);
    }
}
