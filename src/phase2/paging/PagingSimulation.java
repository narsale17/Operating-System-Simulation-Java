package phase2.paging;

import java.util.*;

public class PagingSimulation {
    public static void run(Scanner scanner) {
        System.out.println("\n--- Paging Simulation ---");

        System.out.print("Enter number of pages in the process: ");
        int numPages = scanner.nextInt();

        System.out.print("Enter number of frames in memory: ");
        int numFrames = scanner.nextInt();

        System.out.print("Enter page size (in KB): ");
        int pageSize = scanner.nextInt();

        PageTable pageTable = new PageTable(numPages);
        MemoryManager memoryManager = new MemoryManager(numFrames, pageSize);

        for (int i = 0; i < numPages; i++) {
            System.out.print("Load page " + i + " into frame (enter frame number or -1 for page fault): ");
            int frameNumber = scanner.nextInt();
            if (frameNumber >= 0 && frameNumber < numFrames) {
                Frame frame = new Frame(frameNumber);
                Page page = new Page(i, pageSize);
                memoryManager.loadPage(page, frame);
                pageTable.mapPage(i, frameNumber);
            } else {
                System.out.println("Page fault occurred for page " + i + ".");
            }
        }

        System.out.println("\n--- Page Table ---");
        pageTable.printTable();

        System.out.println("\n--- Frame Layout ---");
        memoryManager.printFrames();
    }
}
