/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pointandclick.Pong;

import java.awt.*;

/**
 *
 * @author leothecrz
 */
public final class Paddle extends Rectangle {
    
    private static int PADDLE_WITH = 15;
    private static int PADDLE_HEIGHT = 50;
    
    private short id;
    
    //constructor
    public Paddle(short id, int x, int y) {
        super(PADDLE_WITH, PADDLE_HEIGHT);
        this.id = id;
        setLocation(x, y);
    }
    
    //draws the paddles 
    public void draw(Graphics g) {
        if(id==1)
            g.setColor(Color.YELLOW);
        else
            g.setColor(Color.GREEN);
            g.fillRect(x, y, PADDLE_WITH, PADDLE_HEIGHT);
    }
}
