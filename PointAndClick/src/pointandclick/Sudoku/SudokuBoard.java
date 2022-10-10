package pointandclick.Sudoku;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SudokuBoard extends JComponent {
    private static final int[][] NUMBERS = new int[][]
    {
        {8, 3, 5, 4, 1, 6, 9, 2, 7},
        {2, 9, 6, 8, 5, 7, 4, 3, 1},
        {4, 1, 7, 2, 9, 3, 6, 5, 8},
        {5, 6, 9, 1, 3, 4, 7, 8, 2},
        {1, 2, 3, 6, 7, 8, 5, 4, 9},
        {7, 4, 8, 5, 2, 9, 1, 6, 3},
        {6, 5, 2, 7, 8, 1, 3, 9, 4},
        {9, 8, 1, 3, 4, 5, 2, 7, 6},
        {3, 7, 4, 9, 6, 2, 8, 1, 5},
    };

    private static final boolean[][] GIVEN = new boolean[][]
    {
        {true,  false, false, true,  false, true,  false, false, true },
        {false, false, false, false, false, false, true,  false, false},
        {false, true,  false, false, false, false, true,  true,  false},
        {true,  false, true,  false, true,  false, true,  true,  false},
        {false, false, false, false, true,  false, false, false, false},
        {false, true,  true,  false, true,  false, true,  false, true },
        {false, true,  true,  false, false, false, false, true,  false},
        {false, false, true,  false, false, false, false, false, false},
        {true,  false, false, true,  false, true,  false, false, true },
    };

    private SudokuCell[][] cells;

    public SudokuBoard() {
        setLayout(new GridLayout(9, 9));

        cells = new SudokuCell[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
               cells[row][col] = new SudokuCell(row, col, NUMBERS[row][col], GIVEN[row][col]);
               add(cells[row][col]);
            }
        }
        // setPreferredSize(new Dimension(9 * 40, 9 * 40));
    }
}