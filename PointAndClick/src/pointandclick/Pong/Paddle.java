/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pointandclick.Pong;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 *
 * @author leothecrz
 */
public final class Paddle extends Rectangle {
    
    private static int PADDLE_WITH = 15;
    private static int PADDLE_HEIGHT = 50;
    private int yDirection;
    private short id;
    
    //constructor
    public Paddle(short id, int x, int y) {
        super(PADDLE_WITH, PADDLE_HEIGHT);
        this.x = x;
        this.y = y;
        this.id = id;
        setLocation(x, y);
    }
    
    public void keyPressed(KeyEvent e){
        switch(id){
            default:
                System.out.println("Invalid ID in paddle constructor");
                break;
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(-1);
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(1);
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(-1);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(1);
                }
                break;
        }
    }
    
    public void keyReleased(KeyEvent e){
        switch(id){
            default:
                System.out.println("Invalid ID in paddle constructor");
                break;
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(0);
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(0);
                }
                break;
        }
    }
    
    //draws the paddles 
    public void draw(Graphics g) {
        if(id==1)
            g.setColor(Color.YELLOW);
        else
            g.setColor(Color.GREEN);
            g.fillRect(x, y, PADDLE_WITH, PADDLE_HEIGHT);
    }
    
    public void setYDirection(int yDir) {
		yDirection = yDir;
	}
}
