package pointandclick.MainMenu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import pointandclick.PointAndClick;

public final class ScoreTable {
    public static final String SCORE_FILE_PATH = "Data/highscore.txt"; 
    private final String filePath;
    private Score[] scoreList;
    private final int HIGH_SCORES_COUNT = 5;
    
    /**
     * Array of Score of size 5. Can write and load
     * high scores to and from a file. 
     * @param filePath - path to txt file used to store data.
     */
    public ScoreTable(){
        this.filePath = SCORE_FILE_PATH;
        this.scoreList = new Score[HIGH_SCORES_COUNT];
        for (int i = 0; i < scoreList.length; i++) {
            scoreList[i] = new Score(0, "-", "-");
        }
        
        if(fileExist()){
            loadScores();
        } else {
            createNewFile();
        }
        
    }
    
    
    /**
     * Save the current highs cores in memory to file on disk.
     * Deletes the file on disk and replaces file 
     */
    public void saveScores(){
        File scoresFile = new File(PointAndClick.fixFilePath(filePath));
        
        try {
            new FileWriter(scoresFile, false).close();
            writeMemoryToFile(scoresFile);
        } catch (IOException ex) {
            System.err.println("Failed to save memory to disk");
        }
        
    }
    
    /**
     * Pull Scores from the 'highscore.txt ' file. If the file does not exist 
     * a default will be created and loaded.
     */
    public void loadScores(){
        File scoresFile = new File(PointAndClick.fixFilePath(filePath));
        try {
            Scanner reader = new Scanner(scoresFile); // Throws FileNotFoundExeception
            // High score file format is:
            // Name
            // Score
            // Name
            // Score
            // ...
            for (Score s1 : scoreList) {
                s1.setName(String.valueOf(reader.nextLine()));
                s1.setScore(reader.nextInt());
                reader.nextLine(); // eats the lone '\n'
            }
            reader.close();
        } 
        catch (FileNotFoundException ex) { System.err.println("File Not Found Error Not Handled"); } 
        catch (NoSuchElementException ex1){
            System.err.println("High score file has an error");
            scoresFile.delete();
            createNewFile();
        } // Catch end
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
        for (var s1 : scoreList ){
            if (playerScore > s1.getScore())
                return true;
        }
        
        return false;
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
        if (biggerThanPos == -1) return false;

        for(int i = (scoreList.length - 1); i > biggerThanPos; i--){
            scoreList[i].setScore(scoreList[i-1].getScore());
            scoreList[i].setName(scoreList[i-1].getName());
        }
        scoreList[biggerThanPos].setScore(playerScore);
        scoreList[biggerThanPos].setName(name);
        
        saveScores();
        
        return true;
    }
    
    private boolean fileExist(){
        File testFile = new File(filePath);
        return testFile.exists();
    }
    
    private void createNewFile(){
        File testFile = new File(filePath);
        try {
            
            testFile.createNewFile();
            new FileWriter(testFile, false).close();
            writeMemoryToFile(testFile);

        } catch (IOException ex) {} 
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
        for (int j = 4; j > -1; j--){
            if (playerScore > scoreList[j].getScore()){
                i = j;
            }
        }
        return i;
    }
    
    /**
     * The file held at the path stored in filePath must not exist
     * or it will throw Exception. Will write the contents of the
     * table onto the file.
     * @throws IOException 
     */
    private void writeMemoryToFile(File file) throws IOException{
        FileWriter writer = new FileWriter(file, false);
        for (var activeScore : this.scoreList) {
            writer.write(activeScore.toString() + "\n"); // Uses the Score class toString Method
         }
        writer.close();
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
    
}
