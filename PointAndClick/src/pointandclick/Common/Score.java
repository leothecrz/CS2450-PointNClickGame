package pointandclick.Common;

public final class Score {
    private String initials;
    private String name;
    private int score;
    
    /**
     * Score Data Type. The data type can hold both identifiers.
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
     * Score Data Type must have at least one of the strings to
     * identify who owns the score.
     * 
     * @param score
     * @param initials 
     */
    public Score(int score, String initials){
        this(score, initials, "");
    }
    
    /**
     * Score Data Type must have at least one of the strings to
     * identify who owns the score.
     * 
     * @param name
     * @param score 
     */
    public Score(String name, int score){
        this(score, "", name);
    }
    
    /**
     * @return Integer score value.
     */
    public int getScore() {
        return this.score;
    }
    
    /**
     * Sets the score of the class
     * @param score 
     */
    public void setScore(int score){
        this.score = score;
    }
    
    /**
     * @return String name value
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Sets the name of the class
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return String initials
     */
    public String getInitials() {
        return this.initials;
    }
    
    /**
     * Sets the initials of the class
     * @param initials 
     */
    public void setInitials(String initials) {
        this.initials = initials;
    }
    
    /**
     * A formatted String in the style of:
     * "NAME,\nSCORE".
     * @return 
     */
    @Override
    public String toString() {
        StringBuilder SB = new StringBuilder();
        SB.append(String.valueOf(name)).append("\n").append(String.valueOf(score));
        return SB.toString();
    }

}
