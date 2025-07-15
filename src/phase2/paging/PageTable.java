package phase2.paging;

import java.util.HashMap;
import java.util.Map;

public class PageTable {
    private final Map<Integer, Integer> table;

    public PageTable(int numPages) {
        table = new HashMap<>();
        for (int i = 0; i < numPages; i++) {
            table.put(i, -1); // -1 means page not loaded
        }
    }

    public void mapPage(int pageNumber, int frameNumber) {
        table.put(pageNumber, frameNumber);
    }

    public int getFrameNumber(int pageNumber) {
        return table.getOrDefault(pageNumber, -1);
    }

    public void printTable() {
        System.out.println("Page\t→\tFrame");
        for (Map.Entry<Integer, Integer> entry : table.entrySet()) {
            int page = entry.getKey();
            int frame = entry.getValue();
            if (frame == -1) {
                System.out.println(" " + page + "\t→\tPage Fault");
            } else {
                System.out.println(" " + page + "\t→\t" + frame);
            }
        }
    }
}
