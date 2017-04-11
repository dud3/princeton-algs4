import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int size;
    private int openSites;

    private double[] results;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {

        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();

        openSites = 0;
        size = n * n;

        results = new double[trials];

        for (int i = 0; i < trials; i++) {

       		Percolation perc = new Percolation(n);

            do {

                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);

                if (!perc.isOpen(row, col)) {
                    perc.open(row, col);
                    openSites++;
                }

            } while (!perc.percolates());

            // Store result of each experiment
            results[i] = (double) openSites / ((double) size);
        }

    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
    }

    public double confidenceLo() {
        return mean() - confidence();
    }

    private double confidence() {
        return (1.96 * stddev() / Math.sqrt(results.length));
    }

    public double confidenceHi() {
        return mean() + confidence();
    }

    public static void main(String[] args) {
        int arg0 = Integer.parseInt(args[0]);
        int arg1 = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(arg0, arg1);

        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }

}
