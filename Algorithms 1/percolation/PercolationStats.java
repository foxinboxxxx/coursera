/* *****************************************************************************
 *  Name: Makar Riabcev
 *  Date: 31-3-2020
 *  Description: Algorithms 1: Percolation - Week 1
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int trials;
    private double[] results;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n > 0 && trials > 0) {
            int gridLine = n;
            int gridSize = n * n;
            trials = trials;
            results = new double[trials];

            for (int i = 0; i < trials; i++) {
                Percolation percolation = new Percolation(gridLine);
                while (!percolation.percolates()) {
                    int row = StdRandom.uniform(1, gridLine + 1);
                    int col = StdRandom.uniform(1, gridLine + 1);
                    percolation.open(row, col);
                }
                int numberOfOpenSites = percolation.numberOfOpenSites();
                results[trials] = (double) numberOfOpenSites / gridSize;
            }
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(trials));
    }

    // test client (see below)
    public static void main(String[] args) {
        int gridLine = 9;
        int trials = 0;
        if (args.length >= 2) {
            gridLine = Integer.parseInt(args[0]);
            trials = Integer.parseInt(args[1]);
        }
        PercolationStats percolationStats = new PercolationStats(gridLine, trials);
        String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + confidence);
    }
}
