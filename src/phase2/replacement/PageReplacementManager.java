package phase2.replacement;

import java.util.*;

public class PageReplacementManager {
    private final int capacity;
    private final ReplacementAlgorithm algorithm;
    private final Set<Integer> memory;
    private final Queue<Integer> fifoQueue;
    private final Map<Integer, Integer> lruMap; // Page → Last used time
    private int time = 0;
    private int pageFaults = 0;
    private int pageHits = 0;

    public PageReplacementManager(int capacity, ReplacementAlgorithm algorithm) {
        this.capacity = capacity;
        this.algorithm = algorithm;
        this.memory = new HashSet<>();
        this.fifoQueue = new LinkedList<>();
        this.lruMap = new HashMap<>();
    }

    public void simulate(List<Integer> referenceString) {
        for (int page : referenceString) {
            System.out.print("Accessing page " + page + ": ");

            if (memory.contains(page)) {
                System.out.println("Hit");
                pageHits++;
                if (algorithm == ReplacementAlgorithm.LRU) {
                    lruMap.put(page, time);
                }
            } else {
                System.out.println("Fault");
                pageFaults++;

                if (memory.size() == capacity) {
                    int toRemove = -1;

                    if (algorithm == ReplacementAlgorithm.FIFO) {
                        toRemove = fifoQueue.poll();
                    } else if (algorithm == ReplacementAlgorithm.LRU) {
                        toRemove = getLRUPage();
                    }

                    if (toRemove != -1) {
                        memory.remove(toRemove);
                        if (algorithm == ReplacementAlgorithm.LRU) {
                            lruMap.remove(toRemove);
                        }
                        System.out.println("  → Evicted page: " + toRemove);
                    }
                }

                memory.add(page);
                if (algorithm == ReplacementAlgorithm.FIFO) {
                    fifoQueue.offer(page);
                } else if (algorithm == ReplacementAlgorithm.LRU) {
                    lruMap.put(page, time);
                }
            }

            time++;
        }
    }

    private int getLRUPage() {
        int lruPage = -1;
        int minTime = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Integer> entry : lruMap.entrySet()) {
            if (entry.getValue() < minTime) {
                minTime = entry.getValue();
                lruPage = entry.getKey();
            }
        }

        return lruPage;
    }

    public int getPageFaults() {
        return pageFaults;
    }

    public int getPageHits() {
        return pageHits;
    }
}
