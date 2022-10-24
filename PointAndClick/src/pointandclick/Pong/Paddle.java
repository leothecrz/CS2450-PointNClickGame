
package pointandclick.Pong;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 *
 */
public final class Paddle extends Rectangle {
    
    private final static int PADDLE_WITH = 15;
    private final static int PADDLE_HEIGHT = 75;
    
    private final static int MAX_PADDLE_Y_VELOCITY = 25;
    private final static int PADDLE_ACCELARATION = 13;
    private final static int BOTTOM_LIMIT_BUFFER = 115;
    
    private final int x_Spawn;
    private final int y_Spawn;
    
    private int yVelocity;
    private short id;
    
    /**
     * 
     * @param id
     * @param x
     * @param y 
     */
    public Paddle(short id, int x, int y) {
        super(PADDLE_WITH, PADDLE_HEIGHT);
        this.x_Spawn = x;
        this.y_Spawn = y;
        this.x = x;
        this.y = y;
        this.id = id;
        setLocation(x, y);
    }
    
    /**
     * 
     * @param e 
     */
    public void keyPressed(KeyEvent e){
        switch (id){
            default:
                System.out.println("Invalid ID in paddle constructor");
                break;
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    if(getYVelocity() > -MAX_PADDLE_Y_VELOCITY){
                        addYVelocity(-PADDLE_ACCELARATION);
                    }
                    System.out.print("W");
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    if(getYVelocity() < MAX_PADDLE_Y_VELOCITY){
                        addYVelocity(PADDLE_ACCELARATION);
                    }
                    System.out.print("S");

                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if(getYVelocity() > -MAX_PADDLE_Y_VELOCITY){
                        addYVelocity(-PADDLE_ACCELARATION);
                    }
                    System.out.print("UP");

                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if(getYVelocity() < MAX_PADDLE_Y_VELOCITY){
                        addYVelocity(PADDLE_ACCELARATION);
                    }
                    System.out.print("DOWN");

                }
                break;
        }
    }
    
    /**
     * 
     * @param e 
     */
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
    
    /**
     * 
     * @param g 
     */
    public void draw(Graphics2D g) {
        if(id==1)
            g.setColor(Color.YELLOW);
        else
            g.setColor(Color.GREEN);
        //g.fillRect(x, y, this.width, this.height);
        g.fill(this);
    }
    
    /**
     * 
     */
    public void tickPass(){
        Point nxtPosition = nextPosition();
        if(nxtPosition.y < 0){
            this.y = 0;
        }else if(nxtPosition.y >= 0 && nxtPosition.y <= 400-(BOTTOM_LIMIT_BUFFER)){
            this.y = nxtPosition.y;
        } else {
            this.y = 400-(BOTTOM_LIMIT_BUFFER);
        }
    }
    
    /**
     * 
     * @param yVel 
     */
    public void addYVelocity(int yVel){
        this.yVelocity += yVel;
    }
    
    /**
     * 
     * @param yVel 
     */
    public void setYVelocity(int yVel){
        this.yVelocity = yVel;
    }
    
    /**
     * 
     * @return 
     */
    public int getYVelocity(){
        return this.yVelocity;
    }
    
    /**
     * 
     * @return 
     */
    public Point nextPosition(){
        return new Point(x, y+=yVelocity);
    }
    
    /**
     * 
     */
    public void resetPaddle(){
        setLocation(x_Spawn, y_Spawn);
    }
    
}
