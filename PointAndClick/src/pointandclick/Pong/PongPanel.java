
package pointandclick.Pong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author leothecrz
 */
public final class PongPanel extends JPanel implements ActionListener{
    
    private static int MILLISECONDSBETWEENFRAMES = 16;
    
    ActionListener pongListener;
    
    public Timer gameLoopTimer;
    Ball pongBall;
    Paddle paddle1;
    Paddle paddle2;
    
    
    public PongPanel(){
        super();
        setLayout(null);
        setPreferredSize(new Dimension(600, 400));
        setBackground(Color.BLACK);
          
        ActionListener PongListener = evt -> {
            
            
        };
        gameLoopTimer = new Timer(MILLISECONDSBETWEENFRAMES, this);
        
        //method to reset the paddles and ball
        resetPaddles();
        resetBall();
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        pongBall.draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(pongBall.x < 585){
            pongBall.x += 1;
            pongBall.setLocation(pongBall.x, pongBall.y);
        } else {
            gameLoopTimer.stop();
        }
        repaint();    
    }
    
    public void resetPaddles() {
        paddle1 = new Paddle((short)1, 30, 200);
        paddle2 = new Paddle((short)2, 555, 200);
        
    }
    
    public void resetBall() {
        rand = new Random();
        pongBall = new Ball(300-8, 200-8);
    }
    
}
