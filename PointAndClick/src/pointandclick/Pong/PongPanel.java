
package pointandclick.Pong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;

import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import pointandclick.Common.RoundedBorder;

/**
 *
 * @author leothecrz
 */
public final class PongPanel extends JPanel implements ActionListener{
    
    private static int MILLISECONDSBETWEENFRAMES = 33;
    
    public Timer gameLoopTimer;
    private Ball pongBall;
    private Paddle paddle1;
    private Paddle paddle2;
    
    private KeyListener pongKeyListener;
    
    private boolean gameRunning;
        
    public PongPanel(ActionListener endOfGameListener){
        super();
        this.setLayout(null);
        this.setPreferredSize(new Dimension(600, 400));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        
        
        pongKeyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println(e.toString());
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };
        
        addKeyListener(pongKeyListener);
        
        
        
        JButton quitButton = new JButton();
        quitButton.setText("Quit");
        quitButton.setActionCommand("PongQuit");
        quitButton.addActionListener(endOfGameListener);
        quitButton.setBorder(new RoundedBorder(15));
        quitButton.setForeground(Color.WHITE);
        quitButton.setFont(new Font("Marker Felt", Font.PLAIN, 12));
        quitButton.setContentAreaFilled(false);
        quitButton.setBounds(20, 10, 50, 25);
        add(quitButton);
        
        gameRunning = false;
        
        paddle1 = new Paddle((short)1, 30, 150);
        paddle2 = new Paddle((short)2, 555, 150);
        pongBall = new Ball();
        
        /**{ // Keybindings
        Action wKey = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paddle1.keyPressed(new KeyEvent(getParent(), KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_W, 'w'));
            }
        };
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "W_KEY");
        getActionMap().put("W_KEY", wKey);
        
        Action sKey = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paddle1.keyPressed(new KeyEvent(getParent(), KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_S, 's'));
            }
        };
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "S_KEY");
        getActionMap().put("S_KEY", sKey);
        
        Action upKey = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paddle2.keyPressed(new KeyEvent(getParent(), KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED));
            }
        };
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "UP_KEY");
        getActionMap().put("UP_KEY", upKey);
        
        Action downKey = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paddle2.keyPressed(new KeyEvent(getParent(), KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_DOWN, KeyEvent.CHAR_UNDEFINED));
            }
        };
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "DOWN_KEY");
        getActionMap().put("DOWN_KEY", downKey);
        }
    */
        
        gameLoopTimer = new Timer(MILLISECONDSBETWEENFRAMES, this);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        pongBall.draw(g);
        paddle1.draw(g);
        paddle2.draw(g);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pongBall.tickPass();
        repaint();
        
    }
    
    public void playPong(){
        gameLoopTimer.start();
        pongBall.resetBall(true);
        gameRunning = true;
        this.grabFocus();
        
    }
    
    public void endPong(){
        gameLoopTimer.stop();
    }
    
}
