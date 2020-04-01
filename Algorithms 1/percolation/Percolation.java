/* *****************************************************************************
 *  Name: Makar Riabcev
 *  Date: 31-3-2020
 *  Description: Algorithms 1: Percolation - Week 1
 **************************************************************************** */

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
            gridSize = Math.pow(n, 2);
            numberOfOpenSites = 0;
            top = gridSize;
            bottom = gridSize + 1;
            wqufTop = new WeightedQuickUnionUF(gridSize + 1);
            wqufBottom = new WeightedQuickUnionUF(gridSize + 2);
        }

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkBorders(row, col);
        return openedGrid[pid(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkBorders(row, col);
        return isOpen(row, col) && wqufTop.connected(0, pid(row, col));
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

    // test client (optional)
    public static void main(String[] args) {

    }


}
