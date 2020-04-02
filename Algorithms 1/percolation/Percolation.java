/* *****************************************************************************
 *  Name: Makar Riabcev
 *  Date: 31-3-2020
 *  Description: Algorithms 1: Percolation - Week 1
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int numberOfOpenSites;
    private int gridSize;
    private int gridLine;
    private WeightedQuickUnionUF wqufTop;
    private WeightedQuickUnionUF wqufBottom;
    private int top;
    private int bottom;
    private boolean[] openedGrid;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n > 0) {
            gridLine = n;
            gridSize = n * n;
            numberOfOpenSites = 0;
            top = gridSize;
            bottom = gridSize + 1;
            wqufTop = new WeightedQuickUnionUF(gridSize + 1);
            wqufBottom = new WeightedQuickUnionUF(gridSize + 2);
            /*
            for (int i = 1; i <= gridLine; i++) {
                wqufTop.union(0, getCellGrid(1, i));
                wqufBottom.union(0, getCellGrid(1, i));
                wqufBottom.union(gridSize + 1, getCellGrid(gridLine, i));
            }
            */
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isExistOnGrid(row, col)) {

            openedGrid[getCellGrid(row, col)] = true;
            if (isOpen(row, col)) {

                if (row == 1) {
                    wqufBottom.union(top, getCellGrid(row, col));
                    wqufTop.union(top, getCellGrid(row, col));
                }

                if (row == gridLine) {
                    wqufBottom.union(bottom, getCellGrid(row, col));
                }
                // check - open Left
                if (isExistOnGrid(row, col - 1) && isOpen(row, col - 1)) {
                    wqufBottom.union(getCellGrid(row, col), getCellGrid(row, col - 1));
                    wqufTop.union(getCellGrid(row, col), getCellGrid(row, col - 1));
                }

                // check - open Right
                if (isExistOnGrid(row, col + 1) && isOpen(row, col + 1)) {
                    wqufBottom.union(getCellGrid(row, col), getCellGrid(row, col + 1));
                    wqufTop.union(getCellGrid(row, col), getCellGrid(row, col + 1));
                }

                // check - open Up
                if (isExistOnGrid(row - 1, col) && isOpen(row - 1, col)) {
                    wqufBottom.union(getCellGrid(row, col), getCellGrid(row - 1, col));
                    wqufTop.union(getCellGrid(row, col), getCellGrid(row - 1, col));
                }

                // check - open Down
                if (isExistOnGrid(row + 1, col) && isOpen(row + 1, col)) {
                    wqufBottom.union(getCellGrid(row, col), getCellGrid(row + 1, col));
                    wqufTop.union(getCellGrid(row, col), getCellGrid(row + 1, col));
                }

                numberOfOpenSites++;
                openedGrid[getCellGrid(row, col)] = true;
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (isExistOnGrid(row, col)) {
            return openedGrid[getCellGrid(row, col)];
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (isExistOnGrid(row, col)) {
            return isOpen(row, col) && wqufTop.connected(0, getCellGrid(row, col));
        }
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return wqufBottom.connected(top, bottom);
    }

    private void checkBorders(int row, int col) {
        if (!isExistOnGrid(row, col)) {
            throw new IndexOutOfBoundsException("Exception: IndexOutOfBounds");
        }
    }

    private boolean isExistOnGrid(int row, int col) {
        if (((row < 1) || (row > gridLine)) || ((col < 1) || (col > gridLine))) {
            throw new IndexOutOfBoundsException("Exception: IndexOutOfBounds");
            return false;
        }
        return true;
    }

    private int getCellGrid(int row, int col) {
        return (row - 1) * gridLine + col;
    }

    // test client (optional)
    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        argCount = args.length;
        Percolation percolation = new Percolation(size);
        for (int i = 0; args.length >= 2; i += 2) {
            int row = Integer.parseInt(args[i]);
            int col = Integer.parseInt(args[i + 1]);
            percolation.open(row, col);
            if (percolation.percolates()) {
                StdOut.printf("%n it percolates %n");
            }
            argCount -= 2;
        }
        if (!percolation.percolates()) {
            StdOut.print("doesnt percolate");
        }
    }


}
