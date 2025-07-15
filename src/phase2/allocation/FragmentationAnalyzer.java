package phase2.allocation;

import java.util.List;

public class FragmentationAnalyzer {

    public static void analyze(List<MemoryBlock> blocks, List<Integer> processSizes, boolean isFixed) {
        int internal = 0;
        int external = 0;
        int totalFree = 0;
        int totalRequested = processSizes.stream().mapToInt(Integer::intValue).sum();

        for (MemoryBlock block : blocks) {
            if (block.isFree()) {
                totalFree += block.getSize();
            } else {
                internal += block.getFragmentation();
            }
        }

        if (!isFixed) {
            // External fragmentation is only meaningful in dynamic partitioning
            external = totalFree;
        }

        System.out.println("\n--- Fragmentation Report ---");
        System.out.println("Internal Fragmentation: " + internal + " KB");
        System.out.println("External Fragmentation: " + external + " KB");
    }
}
