package phase2.segmentation;

import java.util.*;

public class SegmentTable {
    private final Map<String, Segment> segments = new LinkedHashMap<>();

    public void addSegment(Segment segment) {
        segments.put(segment.getName(), segment);
    }

    public Segment getSegment(String name) {
        return segments.get(name);
    }

    public Integer translate(String segmentName, int offset) {
        Segment segment = segments.get(segmentName);
        if (segment == null || !segment.isAddressValid(offset)) {
            return null;
        }
        return segment.getPhysicalAddress(offset);
    }

    public void printTable() {
        System.out.println("Segment\t→\tBase\tLimit");
        for (Segment segment : segments.values()) {
            System.out.println(segment.getName() + "\t→\t" + segment.getBase() + "\t" + segment.getLimit());
        }
    }

    public Collection<Segment> getSegments() {
        return segments.values();
    }
}
