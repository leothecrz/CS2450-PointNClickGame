package pointandclick.Frames.highscoreResources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public final class ScoreTable {
    
    private final String filePath;
    private Score[] scoreList;
    private final int MAXHIGHSCORESSTORED = 5;
    
    /**
     * Array of Score of size 5. Can write and load
     * high scores to and from a file. 
     * @param filePath - path to txt file used to store data.
     */
    public ScoreTable(String filePath){
        
        this.filePath = filePath;
        
        this.scoreList = new Score[MAXHIGHSCORESSTORED];
        for (int i=0; i<scoreList.length; i++) {
            scoreList[i] = new Score(0, "-", "-");
        }
        
        loadScores(); // Pull Scores after construction
    }
    
    /**
     * Save the current highs cores in memory to file on disk.
     * Deletes the file on disk and replaces file 
     */
    public void saveScores(){
        
        File scoresFile = new File(filePath);
        scoresFile.delete();
        try {
            writeMemoryToFile();
        } catch (IOException ex) {
            System.err.println("Failed to save memory to disk");
            ex.printStackTrace();
        }
        
    }
    
    /**
     * Pull Scores from the 'highscore.txt ' file. If the file does not exist 
     * a default will be created and loaded.
     */
    public void loadScores(){
        
        File scoresFile = new File(filePath);
        
        try {
        //load the scores
        
            Scanner reader = new Scanner(scoresFile); // trows FileNotFoundExeception
            // highscore file format is Name\n Score\n Name\n Score\n
            for (Score s1 : scoreList) {
                s1.setName(String.valueOf(reader.nextLine()));
                s1.setScore(reader.nextInt());
                reader.nextLine(); // eats the lone '\n'
            }
    
        } catch (FileNotFoundException ex) { 
        // File is not found make a default
            
            try {
                
                writeMemoryToFile();

            } catch (IOException ex1) {
                System.err.println("\n The Default Highscore table could not be created\n");
                ex1.printStackTrace();
            }
        
        } // Catch end
    }
    
    /**
     * The file held at the path stored in filePath must not exist
     * or it will throw Exception. Will write the contents of the
     * table onto the file.
     * @throws IOException 
     */
    private void writeMemoryToFile() throws IOException{
        
        File scoresFile = new File(filePath);
        FileWriter writer;
        scoresFile.createNewFile();
        writer = new FileWriter(scoresFile);
        for (var activeScore : this.scoreList) {
            writer.write(activeScore.toString() + "\n"); //USes the Score Class To String Method
         }
        writer.close();

    }
    
    /**
     * Get the score array associated with the
     * table.
     * @return A Score Array of size 5. 
     */
    public Score[] getScoreArray(){
        return this.scoreList;
    }
    
    /**
     * Checks if the given score should be allowed
     * to be added.
     * @param playerScore - Score to be checked.
     * @return True if it should be added.
     */
    public boolean checkIfHighscoreBoolean(int playerScore){
        
        for(var s1 : scoreList ){
            if(playerScore > s1.getScore())
                return true;
        }
        
        return false;
    }
    
    /**
     * Checks if the int playerScore is bigger than any of the 
     * high scores starting from the lowest. Will keep track of what position it is bigger
     * than.
     * @param playerScore - Score to be checked
     * @return the position where the score should be placed in.
     */
    private int checkIfHighscore(int playerScore){
        
        int i = -1;
        for(int j=4; j>-1; j--){
            if(playerScore > scoreList[j].getScore()){
                i = j;
                
            }
        }
        
        return i;
    }
    
    /**
     * 
     * Securely request to add a score and name to the table. The score is
     * checked with (int)checkIfHighscore(playerScore). If it returns with a position 
     * the score will be added in that positions and all lower scores are moved down by one
     * throwing away the lowest score.
     * 
     * @param playerScore - Score to check
     * @param name - Name to identify score
     * @return True if addition was successful
     */
    public boolean secureAdd(int playerScore, String name){
        
        int biggerThanPos = checkIfHighscore(playerScore);
        if(biggerThanPos == -1) return false;
        
        
        for(int i=(scoreList.length-1); i>biggerThanPos; i--){
            scoreList[i].setScore(scoreList[i-1].getScore());
            scoreList[i].setName(scoreList[i-1].getName());
            
        }
        scoreList[biggerThanPos].setScore(playerScore);
        scoreList[biggerThanPos].setName(name);
        
        saveScores();
        
        return true;
    }
    
    /**
     * Formats a string in the style of "NAME SCORE\nNAME SCORE\n" 
     * @return The formatted string
     */
    @Override
    public String toString() {
        StringBuilder SB = new StringBuilder();
        
        for (Score s0 : scoreList) {
            SB.append(s0.getName()).append(" ").append(String.valueOf(s0.getScore())).append("\n");
        }
        return SB.toString();
    }
    
    /** Example Usage
    public static void main(String[] args){
        
        ScoreTable ST = new ScoreTable(pointandclick.PointAndClick.SCOREFILEPATH);
        
        int points = 404;
        if( ST.checkIfHighscore(points) != -1 ){
            // ask for name to feed to secureAdd Method
            System.out.println(ST.checkIfHighscore(points));
            System.out.println(ST.secureAdd(points, "Top001"));
        }
        
    }
    **/
    
    
    
}
