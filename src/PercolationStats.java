import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

import static java.util.Arrays.stream;

public class PercolationStats {
    double[] thresholds;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        thresholds = new double[trials];
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
        return stream(thresholds).sum() / thresholds.length;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return 0d;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return 0d;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return 0d;

    }

    // test client (see below)
    public static void main(String[] args) {

    }

}
