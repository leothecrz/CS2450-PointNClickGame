
package pointandclick.Pong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
        
        
        pongBall = new Ball(300, 200);
        paddle1 = new Paddle((short)1, 30, 200);
        paddle2 = new Paddle((short)2, 555, 200);
          
        ActionListener PongListener = evt -> {
            
            
        };
        gameLoopTimer = new Timer(MILLISECONDSBETWEENFRAMES, this);
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setColor(pongBall.getFillColor());
        g2.fill(pongBall);
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
    
}
