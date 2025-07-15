package phase2.allocation;

import java.util.*;

public class MemoryAllocator {
    private final AllocationStrategy strategy;
    private final boolean isFixed;
    private final Map<Integer, Integer> allocationMap = new LinkedHashMap<>();

    public MemoryAllocator(AllocationStrategy strategy, boolean isFixed) {
        this.strategy = strategy;
        this.isFixed = isFixed;
    }

    public void allocate(List<MemoryBlock> memoryBlocks, List<Integer> processSizes) {
        for (int i = 0; i < processSizes.size(); i++) {
            int pid = i + 1;
            int size = processSizes.get(i);
            int blockIndex = findBlockIndex(memoryBlocks, size);

            if (blockIndex != -1) {
                MemoryBlock block = memoryBlocks.get(blockIndex);
                int originalSize = block.getSize();

                if (isFixed) {
                    block.allocate(pid, size);
                } else {
                    // Split the block in dynamic partitioning
                    block.allocate(pid, size);
                    int remaining = originalSize - size;

                    if (remaining > 0) {
                        MemoryBlock freeBlock = new MemoryBlock(remaining);
                        memoryBlocks.add(blockIndex + 1, freeBlock);
                    }
                }

                allocationMap.put(pid, blockIndex);
            } else {
                allocationMap.put(pid, -1); // not allocated
            }
        }
    }

    private int findBlockIndex(List<MemoryBlock> blocks, int size) {
        List<Integer> candidates = new ArrayList<>();

        for (int i = 0; i < blocks.size(); i++) {
            MemoryBlock block = blocks.get(i);
            if (block.canFit(size)) {
                candidates.add(i);
            }
        }

        if (candidates.isEmpty()) return -1;

        if (strategy == AllocationStrategy.FIRST_FIT) {
            return candidates.get(0);
        }

        int bestIndex = candidates.get(0);
        int bestSize = blocks.get(bestIndex).getSize();

        for (int i : candidates) {
            int currentSize = blocks.get(i).getSize();

            if (strategy == AllocationStrategy.BEST_FIT && currentSize < bestSize) {
                bestSize = currentSize;
                bestIndex = i;
            }

            if (strategy == AllocationStrategy.WORST_FIT && currentSize > bestSize) {
                bestSize = currentSize;
                bestIndex = i;
            }
        }

        return bestIndex;
    }

    public void printAllocationResult(List<MemoryBlock> memoryBlocks) {
        System.out.println("\nMemory Allocation Result:");
        for (Map.Entry<Integer, Integer> entry : allocationMap.entrySet()) {
            int pid = entry.getKey();
            int index = entry.getValue();
            if (index != -1) {
                System.out.println("Process " + pid + " allocated to Block " + (index + 1));
            } else {
                System.out.println("Process " + pid + " could not be allocated.");
            }
        }

        System.out.println("\nMemory Layout:");
        for (MemoryBlock block : memoryBlocks) {
            System.out.println(block);
        }
    }
}
