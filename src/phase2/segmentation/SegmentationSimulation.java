package phase2.segmentation;

import java.util.*;

public class SegmentationSimulation {
    public static void run(Scanner scanner) {
        System.out.println("\n--- Segmentation Simulation ---");

        System.out.print("Enter number of segments in the process: ");
        int numSegments = scanner.nextInt();

        SegmentTable segmentTable = new SegmentTable();

        for (int i = 0; i < numSegments; i++) {
            System.out.print("Enter name of segment " + (i + 1) + ": ");
            String name = scanner.next();
            System.out.print("Enter base address of " + name + ": ");
            int base = scanner.nextInt();
            System.out.print("Enter limit (size) of " + name + ": ");
            int limit = scanner.nextInt();

            segmentTable.addSegment(new Segment(name, base, limit));
        }

        System.out.println("\n--- Segment Table ---");
        segmentTable.printTable();

        System.out.print("\nEnter logical address (segmentName offset): ");
        String segmentName = scanner.next();
        int offset = scanner.nextInt();

        Integer physicalAddress = segmentTable.translate(segmentName, offset);
        if (physicalAddress == null) {
            System.out.println("Segmentation fault: Offset exceeds segment limit or segment not found.");
        } else {
            System.out.println("Physical Address: " + physicalAddress);
        }

        System.out.println("\n--- Memory Layout ---");
        MemoryLayoutPrinter.print(segmentTable.getSegments());
    }
}
