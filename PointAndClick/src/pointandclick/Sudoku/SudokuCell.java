package pointandclick.Sudoku;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SudokuCell extends JComponent implements MouseListener {
    private static final int CELL_SIZE = 38;
    private Font markerFeltFont;

    public int row;
    public int column;
    public int number;
    public boolean given;
    private int userAnswer;
    private boolean selected;
    private ActionListener listener;

    public SudokuCell(int row, int column, int number, boolean given, ActionListener listener) {
        this.row = row;
        this.column = column;
        this.number = number;
        this.given = given;
        this.listener = listener;
        this.userAnswer = given ? number : 0;

        markerFeltFont = new Font("Marker Felt", Font.PLAIN, 12);
        addMouseListener(this);
        setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        repaint();
    }

    public void setUserAnswer(int answer) {
        if(!given) {
            this.userAnswer = answer;
            repaint();
        }
    }

    public boolean isCorrect() {
        return userAnswer == number;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(selected ? Color.GRAY : Color.WHITE);
        if(selected && given)
            g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, CELL_SIZE, CELL_SIZE);

        // Center text
        if (given || userAnswer != 0) {
            g.setFont(markerFeltFont);
            FontMetrics metric = g.getFontMetrics();
            String text = String.valueOf(userAnswer);
            int x = (CELL_SIZE - metric.stringWidth(text)) / 2;
            int y = (CELL_SIZE - metric.getHeight()) / 2 + metric.getAscent();
            g.setColor(given ? Color.BLACK : Color.BLUE);
            g.drawString(text, x, y);
        }
    }

    @Override
    protected void paintBorder(Graphics g) {
        super.paintBorder(g);

        // Left
        if (column != 0) {
            g.setColor(column == 3 || column == 6 ? Color.BLACK : Color.GRAY);
            g.drawLine(0, 0, 0, CELL_SIZE);
        }

        // Top
        if (row != 0) {
            g.setColor(row == 3 || row == 6 ? Color.BLACK : Color.GRAY);
            g.drawLine(0, 0, CELL_SIZE, 0);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));
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
