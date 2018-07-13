import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

  private static final double CONFIDENCE = 1.96;
  private final int trials;
  private final double[] xs;
  private double mean;
  private double stddev;

  /**
   * perform trials independent experiments on an n-by-n grid.
   */
  public PercolationStats(int n, int trials) {
    if (n <= 0 || trials <= 0) {
      throw new IllegalArgumentException("Wrong arguments");
    }

    this.trials = trials;
    xs = new double[trials];

    for (int i = 0; i < trials; ++i) {
      Percolation perc = new Percolation(n);
      while (!perc.percolates()) {
        int j = StdRandom.uniform(1, n + 1);
        int k = StdRandom.uniform(1, n + 1);
        perc.open(j, k);
      }
      xs[i] = perc.numberOfOpenSites() / (double) (n * n);
    }
  }

  /**
   * sample mean of percolation threshold.
   */
  public double mean() {
    if (mean == 0d) {
      mean = StdStats.mean(xs);
    }
    return mean;
  }

  /**
   * sample standard deviation of percolation threshold.
   */
  public double stddev() {
    if (stddev == 0d) {
      stddev = StdStats.stddev(xs);
    }
    return stddev;
  }

  /**
   * low  endpoint of 95% confidence interval.
   */
  public double confidenceLo() {
    return mean() - CONFIDENCE * stddev() / Math.sqrt(trials);
  }

  /**
   * high endpoint of 95% confidence interval.
   */
  public double confidenceHi() {
    return mean() + CONFIDENCE * stddev() / Math.sqrt(trials);
  }

  /**
   * test client (described below).
   */
  public static void main(String[] args) {
    if (args.length < 2 || !args[0].matches("\\d+") || !args[1].matches("\\d+")) {
      throw new IllegalArgumentException("Wrong args");
    }
    PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]),
        Integer.parseInt(args[1]));
    StdOut.println("mean                    = " + percolationStats.mean());
    StdOut.println("stddev                  = " + percolationStats.stddev());
    StdOut.println(
        "95% confidence interval = [" + percolationStats.confidenceLo() + ", " + percolationStats
            .confidenceHi() + "]");
  }
}