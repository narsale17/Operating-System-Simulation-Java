package phase2.paging;

public class Page {
    private final int pageNumber;
    private final int size;

    public Page(int pageNumber, int size) {
        this.pageNumber = pageNumber;
        this.size = size;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Page " + pageNumber + " (" + size + " KB)";
    }
}
