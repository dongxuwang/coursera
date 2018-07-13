import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private final WeightedQuickUnionUF uf;
  private final WeightedQuickUnionUF fullUF;
  private final int size;
  private final boolean[][] sitesOpened;
  private int opens;

  /**
   * create n-by-n grid, with all sites blocked.
   *
   * @param n the size
   */
  public Percolation(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException("Wrong arguments");
    }
    size = n;
    sitesOpened = new boolean[n][n];

    // 0 | 1 2 3 4 5 6 7 8 9 | 10 when n = 3
    uf = new WeightedQuickUnionUF(n * n + 2);
    fullUF = new WeightedQuickUnionUF(n * n + 1);
    for (int i = 1; i <= n; ++i) {
      uf.union(0, i);
      fullUF.union(0, i);
      uf.union(n * n + 1, n * (n - 1) + i);
    }
  }

  /**
   * open site (row, col) if it is not open already.
   *
   * @param row row
   * @param col column
   */
  public void open(int row, int col) {
    validateArguments(row, col);

    if (!isOpen(row, col)) {
      openSite(row, col);
      connectNeighbors(row, col);
    }
  }

  private void openSite(int row, int col) {
    opens++;
    sitesOpened[row - 1][col - 1] = true;
  }

  private void connectNeighbors(int row, int col) {
    if (row > 1 && isOpen(row - 1, col)) {
      uf.union(xyTo1D(row, col), xyTo1D(row - 1, col));
      fullUF.union(xyTo1D(row, col), xyTo1D(row - 1, col));
    }
    if (row < size && isOpen(row + 1, col)) {
      uf.union(xyTo1D(row, col), xyTo1D(row + 1, col));
      fullUF.union(xyTo1D(row, col), xyTo1D(row + 1, col));
    }
    if (col > 1 && isOpen(row, col - 1)) {
      uf.union(xyTo1D(row, col), xyTo1D(row, col - 1));
      fullUF.union(xyTo1D(row, col), xyTo1D(row, col - 1));
    }
    if (col < size && isOpen(row, col + 1)) {
      uf.union(xyTo1D(row, col), xyTo1D(row, col + 1));
      fullUF.union(xyTo1D(row, col), xyTo1D(row, col + 1));
    }
  }

  /**
   * is site (row, col) open.
   *
   * @param row row
   * @param col column
   * @return true if open
   */
  public boolean isOpen(int row, int col) {
    validateArguments(row, col);

    return sitesOpened[row - 1][col - 1];
  }


  private void validateArguments(int row, int col) {
    if (row < 1 || row > size || col < 1 || col > size) {
      throw new IllegalArgumentException("Wrong arguments");
    }
  }

  /**
   * is site (row, col) full.
   *
   * @param row row
   * @param col column
   * @return true if full
   */
  public boolean isFull(int row, int col) {
    return isOpen(row, col) && fullUF.connected(0, xyTo1D(row, col));
  }

  /**
   * // number of open sites.
   *
   * @return the count of open sites
   */
  public int numberOfOpenSites() {
    return opens;
  }

  /**
   * // does the system percolate.
   *
   * @return true if percolate
   */
  public boolean percolates() {
    if (size == 1) {
      return isOpen(1, 1);
    }
    return uf.connected(0, size * size + 1);
  }

  private int xyTo1D(int x, int y) {
    validateArguments(x, y);
    return size * (x - 1) + y;
  }

}