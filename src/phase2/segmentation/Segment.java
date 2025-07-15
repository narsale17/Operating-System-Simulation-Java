package phase2.segmentation;

public class Segment {
    private final String name;
    private final int base;
    private final int limit;

    public Segment(String name, int base, int limit) {
        this.name = name;
        this.base = base;
        this.limit = limit;
    }

    public String getName() {
        return name;
    }

    public int getBase() {
        return base;
    }

    public int getLimit() {
        return limit;
    }

    public boolean isAddressValid(int offset) {
        return offset >= 0 && offset < limit;
    }

    public int getPhysicalAddress(int offset) {
        return base + offset;
    }

    @Override
    public String toString() {
        return name + " → Base: " + base + ", Limit: " + limit;
    }
}
