import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Percolation {

    private boolean[][] grid;
    private int size;
    private int top = 0;
    private int bottom;
    private int openSites;
    private WeightedQuickUnionUF uf;

    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();

        size = n;
        bottom = size * size + 1;
        openSites = 0;
        uf = new WeightedQuickUnionUF(size * size + 2);
        grid = new boolean[size][size];
    }

    private int rowToCol(int i, int j) {
        return size * (i - 1) + j;
    }

    public void open(int i, int j) {
        validate(i, j);

        grid[i - 1][j - 1] = true;

        // Top and bottom rows

        if (i == 1) {
            uf.union(rowToCol(i, j), top);
        }

        if (i == size) {
            uf.union(rowToCol(i, j), bottom);
        }

        // Other rows

        if (j > 1 && isOpen(i, j - 1)) {
            uf.union(rowToCol(i, j), rowToCol(i, j - 1));
        }

        if (i > 1 && isOpen(i - 1, j)) {
            uf.union(rowToCol(i, j), rowToCol(i - 1, j));
        }

        if (j < size && isOpen(i, j + 1)) {
            uf.union(rowToCol(i, j), rowToCol(i, j + 1));
        }

        if (i < size && isOpen(i + 1, j)) {
            uf.union(rowToCol(i, j), rowToCol(i + 1, j));
        }

        if(isOpen(i, j))
            openSites++;
    }

    public boolean isOpen(int i, int j) {
        validate(i, j);
        return grid[i - 1][j - 1];
    }

    public boolean isFull(int i, int j) {
        validate(i, j);
        return uf.connected(top, rowToCol(i, j));
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return uf.connected(top, bottom);
    }

    private void validate(int i, int j) {
        if (i < 1 || i > size || j < 1 || j > size)
            throw new java.lang.IndexOutOfBoundsException();
    }

    public static void main(String[] args) {
        int arg0 = Integer.parseInt(args[0]);

        // repeatedly read in sites to open and draw resulting system
        Percolation perc = new Percolation(arg0);

        while (!StdIn.isEmpty()) {
            int i = StdIn.readInt();
            int j = StdIn.readInt();
            perc.open(i, j);
            StdOut.println(perc.numberOfOpenSites());
        }
    }

}
