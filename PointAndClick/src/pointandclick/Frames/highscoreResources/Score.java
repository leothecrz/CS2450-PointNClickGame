package pointandclick.Frames.highscoreResources;

/**
 *
 * @author leothecrz
 */
public class Score {
    private String initials;
    private String name;
    private int score;
    
    public Score(int score, String initial, String name){
        this.initials = initial;
        this.name = name;
        this.score = score;
    }
    
    public Score(int score, String initials){
        this(score, initials, "");
    }
    
    public Score(String name, int score){
        this(score, "", name);
    }
    
    public int getScore(){
        return this.score;
    }
    
    public void setScore(int score){
        this.score = score;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getInitials(){
        return this.initials;
    }
    
    public void setInitials(String initials){
        this.initials = initials;
    }
    
}
