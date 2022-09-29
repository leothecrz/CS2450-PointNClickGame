package pointandclick.Frames.colorResources;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

public class ColorButton extends JComponent implements MouseInputListener {
    private Color color;
    private boolean highlight;
    private ColorButtonListener listener;

    public ColorButton(Color color, ColorButtonListener listener) {
        this.color = color;
        this.listener = listener;
        highlight = false;
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        listener.buttonPressed(color);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        highlight = true;
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        highlight = false;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(highlight ? color.darker() : color);
        g.fillOval(0, 0, 100, 100);
    }
    
}
