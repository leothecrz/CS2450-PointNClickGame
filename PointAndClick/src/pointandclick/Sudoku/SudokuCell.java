package pointandclick.Sudoku;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SudokuCell extends JComponent implements MouseListener {
    public int row;
    public int column;
    public int number;
    public boolean given;

    public SudokuCell(int row, int column, int number, boolean given) {
        this.row = row;
        this.column = column;
        this.number = number;
        this.given = given;

        addMouseListener(this);
        setPreferredSize(new Dimension(35, 35));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 35, 35);
    }

    @Override
    protected void paintBorder(Graphics g) {
        super.paintBorder(g);

        // Left
        if (column != 0) {
            g.setColor(column == 3 || column == 6 ? Color.BLACK : Color.GRAY);
            g.drawLine(0, 0, 0, 35);
        }

        // Top
        if (row != 0) {
            g.setColor(row == 3 || row == 6 ? Color.BLACK : Color.GRAY);
            g.drawLine(0, 0, 35, 0);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.printf("%d %d\n", row, column);
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
