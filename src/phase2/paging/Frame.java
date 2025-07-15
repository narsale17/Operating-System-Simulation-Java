package phase2.paging;

public class Frame {
    private final int frameNumber;
    private Page page; // null if empty

    public Frame(int frameNumber) {
        this.frameNumber = frameNumber;
        this.page = null;
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public void loadPage(Page page) {
        this.page = page;
    }

    public boolean isEmpty() {
        return page == null;
    }

    public Page getPage() {
        return page;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Frame " + frameNumber + ": [ Empty ]";
        } else {
            return "Frame " + frameNumber + ": [ " + page + " ]";
        }
    }
}
