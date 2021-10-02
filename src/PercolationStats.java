import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] thresholds;
    private final int trials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        thresholds = new double[trials];
        this.trials = trials;
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                System.out.println("opening row=" + row + ",col=" + col);
                System.out.println("num of open sites: " + percolation.numberOfOpenSites());
                percolation.open(row, col);
            }
            int threshold = n * n / percolation.numberOfOpenSites();
            System.out.println("percolation threshold: " + threshold);
            thresholds[i] = threshold;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(trials);

    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(100, 100);
        System.out.println("percolationStats mean = " + percolationStats.mean());
        System.out.println("percolationStats confidenceLo = " + percolationStats.confidenceLo());
        System.out.println("percolationStats confidenceHi = " + percolationStats.confidenceHi());
    }

}
