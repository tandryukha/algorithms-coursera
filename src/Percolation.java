import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final int TOP_VIRTUAL_SITE_INDEX = 0;
    private final WeightedQuickUnionUF unionFind;
    private int numberOfOpenSites;
    private final int n;
    private final int bottomVirtualSiteIndex;
    private final boolean[] openSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 1) throw new IllegalArgumentException("n should be > 0");
        this.n = n;
        this.bottomVirtualSiteIndex = n * n + 1;
        this.openSites = new boolean[n * n + 2];
        openSites[TOP_VIRTUAL_SITE_INDEX] = true;
        openSites[bottomVirtualSiteIndex] = true;
        int virtualUpperAndBottomSites = 2;
        unionFind = new WeightedQuickUnionUF(n * n + virtualUpperAndBottomSites);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (isOpen(row, col)) return;

        if (row == 1 || isOpen(row - 1, col)) union(row, col, row - 1, col);
        if (col > 1 && isOpen(row, col - 1)) union(row, col, row, col - 1);
        if (col < n && isOpen(row, col + 1)) union(row, col, row, col + 1);
        if (row == n || row < n && isOpen(row + 1, col)) union(row, col, row + 1, col);

        numberOfOpenSites++;
        openSites[getIndex(row, col)] = true;
    }

    private void union(int row, int col, int otherRow, int otherCol) {
        unionFind.union(getIndex(row, col), getIndex(otherRow, otherCol));
    }

    private int getIndex(int row, int col) {
        if (row < 1) return TOP_VIRTUAL_SITE_INDEX;
        if (row > n) return bottomVirtualSiteIndex;
        return (row - 1) * n + col;
    }

    private void validate(int row, int col) {
        if (row < 1 || col < 1) throw new IllegalArgumentException("row/col should be >0");
        if (row > n || col > n) throw new IllegalArgumentException("row/col should be <n");
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return openSites[getIndex(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        int topVirtualSiteRow = 0;
        return find(row, col) == find(topVirtualSiteRow, 1);
    }

    private int find(int row, int col) {
        return unionFind.find(getIndex(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return unionFind.find(bottomVirtualSiteIndex) == unionFind.find(TOP_VIRTUAL_SITE_INDEX);
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = 100;
        Percolation percolation = new Percolation(n);
        while (!percolation.percolates()) {
            int row = StdRandom.uniform(n) + 1;
            int col = StdRandom.uniform(n) + 1;
            StdOut.println("opening row=" + row + ",col=" + col);
            StdOut.println("num of open sites: " + percolation.numberOfOpenSites());
            percolation.open(row, col);
        }
        StdOut.println("percolation threshold: " + n * n / percolation.numberOfOpenSites());
    }
}