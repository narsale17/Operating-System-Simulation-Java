package phase2.paging;

import java.util.ArrayList;
import java.util.List;

public class MemoryManager {
    private final List<Frame> frames;
    private final int pageSize;

    public MemoryManager(int numFrames, int pageSize) {
        this.pageSize = pageSize;
        frames = new ArrayList<>();
        for (int i = 0; i < numFrames; i++) {
            frames.add(new Frame(i));
        }
    }

    public void loadPage(Page page, Frame targetFrame) {
        targetFrame.loadPage(page);
    }

    public void printFrames() {
        for (Frame frame : frames) {
            System.out.println(frame);
        }
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public int getPageSize() {
        return pageSize;
    }
}
