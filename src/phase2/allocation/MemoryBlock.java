package phase2.allocation;

public class MemoryBlock {
    private int size;  // Removed 'final' to allow resizing in dynamic allocation
    private int allocatedSize = 0;
    private int processId = -1;

    public MemoryBlock(int size) {
        this.size = size;
    }

    public boolean isFree() {
        return processId == -1;
    }

    public boolean canFit(int processSize) {
        return isFree() && size >= processSize;
    }

    public void allocate(int processId, int allocatedSize) {
        this.processId = processId;
        this.allocatedSize = allocatedSize;
        this.size = allocatedSize;  // Shrink for dynamic allocation
    }

    public void deallocate() {
        this.processId = -1;
        this.allocatedSize = 0;
    }

    public int getSize() {
        return size;
    }

    public int getAllocatedSize() {
        return allocatedSize;
    }

    public int getProcessId() {
        return processId;
    }

    public int getFragmentation() {
        return isFree() ? 0 : size - allocatedSize;
    }

    @Override
    public String toString() {
        if (isFree()) {
            return "[ Free (" + size + " KB) ]";
        } else {
            return "[ P" + processId + " (" + allocatedSize + "/" + size + " KB) ]";
        }
    }
}
