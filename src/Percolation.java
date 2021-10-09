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
        int targetSiteIndex = row * col;
        int upperSiteIndex = (row - 1) * col;
        int leftSiteIndex = row * (col - 1);
        int rightSiteIndex = row * (col + 1);
        int lowerSiteIndex = (row + 1) * col;

        if (isOpen(row - 1, col)) unionFind.union(targetSiteIndex, upperSiteIndex);
        if (col > 1 && isOpen(row, col - 1)) unionFind.union(targetSiteIndex, leftSiteIndex);
        if (col < n && isOpen(row, col + 1)) unionFind.union(targetSiteIndex, rightSiteIndex);
        if (row < n && isOpen(row + 1, col)) unionFind.union(targetSiteIndex, lowerSiteIndex);
        if (row == n) unionFind.union(targetSiteIndex, bottomVirtualSiteIndex);

        numberOfOpenSites++;
        openSites[targetSiteIndex] = true;
    }

    private void validate(int row, int col) {
        if (row < 1 || col < 1) throw new IllegalArgumentException("index should be >0");
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
//        validate(row, col);
        return openSites[row * col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return unionFind.find(row * col) == unionFind.find(TOP_VIRTUAL_SITE_INDEX);
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