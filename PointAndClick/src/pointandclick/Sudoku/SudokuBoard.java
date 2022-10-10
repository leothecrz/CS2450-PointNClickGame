package pointandclick.Sudoku;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SudokuBoard extends JComponent implements ActionListener {
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
    private int selectedRow;
    private int selectedColumn;

    public SudokuBoard() {
        setLayout(new GridLayout(9, 9));
        
        Action numberAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (selectedRow != -1 && selectedColumn != -1)
                    cells[selectedRow][selectedColumn].setUserAnswer(Integer.parseInt(e.getActionCommand()));
            }
        };

        for (int i = 0; i <= 9; i++) {
            getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(String.valueOf(i)), String.valueOf(i));
            getActionMap().put(String.valueOf(i), numberAction);
        }
    }

    public void setupBoard() {
        cells = new SudokuCell[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                cells[row][col] = new SudokuCell(row, col, NUMBERS[row][col], GIVEN[row][col], this);
                add(cells[row][col]);
            }
        }

        selectedRow = -1;
        selectedColumn = -1;
    }

    public int calculateScore() {
        int score = 540;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                SudokuCell cell = cells[row][col];
                if (cell.given)
                    continue;
                if (!cell.isCorrect())
                    score -= 10;
            }
        }
        return score;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SudokuCell cell = (SudokuCell) e.getSource(); // Get the cell that was clicked
        if (cell.given) // Don't select cells that were given
            return;
        if (selectedRow != -1 && selectedColumn != -1)
            cells[selectedRow][selectedColumn].setSelected(false); // Set the selected field of the previously selected cell to false
        selectedRow = cell.row;
        selectedColumn = cell.column;
        cell.setSelected(true);
    }
}