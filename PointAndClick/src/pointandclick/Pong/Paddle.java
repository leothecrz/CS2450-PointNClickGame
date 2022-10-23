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
    private static int PADDLE_HEIGHT = 75;
    
    private int yDirection;
    private int yVelocity;
    
    private int x_Spawn;
    private int y_Spawn;
    
    
    private short id;
    
    //constructor
    public Paddle(short id, int x, int y) {
        super(PADDLE_WITH, PADDLE_HEIGHT);
        this.x_Spawn = x;
        this.y_Spawn = y;
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
                    if(getYVelocity() > -50){
                        addYVelocity(-5);
                    }
                    System.out.print("W");
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    if(getYVelocity() < 50){
                        addYVelocity(5);
                    }
                    System.out.print("S");

                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if(getYVelocity() > -50){
                        addYVelocity(-5);
                    }
                    System.out.print("UP");

                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if(getYVelocity() < 50){
                        addYVelocity(5);
                    }
                    System.out.print("DOWN");

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
                    if(getYVelocity() != 0)
                        setYVelocity(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    if(getYVelocity() != 0)
                        setYVelocity(0);
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if(getYVelocity() != 0)
                        setYVelocity(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if(getYVelocity() != 0)
                        setYVelocity(0);
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
        g.fillRect(x, y, this.width, this.height);
    }
    
    public void setYDirection(int yDir) {
        yDirection = yDir;
    }
    
    public void tickPass(){
        Point nxtPosition = nextPosition();
        this.y = nxtPosition.y;
    }
    
    public void addYVelocity(int yVel){
        this.yVelocity += yVel;
    }
    
    public void setYVelocity(int yVel){
        this.yVelocity = yVel;
    }
    
    public int getYVelocity(){
        return this.yVelocity;
    }
    
    public Point nextPosition(){
        return new Point(x, y+=yVelocity);
    }
    
    public void resetPaddle(){
        setLocation(x_Spawn, y_Spawn);
    }
    
}
