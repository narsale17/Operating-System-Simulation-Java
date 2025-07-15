package phase2.segmentation;

import java.util.Collection;

public class MemoryLayoutPrinter {
    public static void print(Collection<Segment> segments) {
        System.out.println("\nMemory Layout:");
        int current = 0;

        for (Segment segment : segments) {
            if (segment.getBase() > current) {
                System.out.println("[ Free : " + (segment.getBase() - current) + " KB ]");
            }

            System.out.println("[ " + segment.getName() + " : " + segment.getLimit() + " KB @ Base " + segment.getBase() + " ]");
            current = segment.getBase() + segment.getLimit();
        }

        System.out.println("[ End of Memory ]");
    }
}
