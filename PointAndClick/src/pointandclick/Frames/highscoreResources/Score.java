package pointandclick.Frames.highscoreResources;


public class Score {
    private String initials;
    private String name;
    private int score;
    
    /**
     * 
     * @param score
     * @param initial
     * @param name 
     */
    public Score(int score, String initial, String name){
        this.initials = initial;
        this.name = name;
        this.score = score;
    }
    
    /**
     * 
     * @param score
     * @param initials 
     */
    public Score(int score, String initials){
        this(score, initials, "");
    }
    
    /**
     * 
     * @param name
     * @param score 
     */
    public Score(String name, int score){
        this(score, "", name);
    }
    
    /**
     * 
     * @return 
     */
    public int getScore(){
        return this.score;
    }
    
    /**
     * 
     * @param score 
     */
    public void setScore(int score){
        this.score = score;
    }
    
    /**
     * 
     * @return 
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * 
     * @param name 
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * 
     * @return 
     */
    public String getInitials(){
        return this.initials;
    }
    
    /**
     * 
     * @param initials 
     */
    public void setInitials(String initials){
        this.initials = initials;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        StringBuilder SB = new StringBuilder();
        SB.append(String.valueOf(name)).append("\n").append(String.valueOf(score));
        return SB.toString();
    }

}
