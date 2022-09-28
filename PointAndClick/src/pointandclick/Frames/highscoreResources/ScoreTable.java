package pointandclick.Frames.highscoreResources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public final class ScoreTable {
    
    private final String filePath;
    private Score[] scoreList;
    
    /**
     * Array of Score of size 5. Can write and load
     * high scores to and from a file. 
     * @param filePath - path to txt file used to store data.
     */
    public ScoreTable(String filePath){
        
        this.filePath = filePath;
        
        this.scoreList = new Score[5];
        for (int i=0; i<scoreList.length; i++) {
            scoreList[i] = new Score(0, "-", "-"); // Actual will have 0 and 2 blanks
        }
        
        loadScores(); // Pull Scores after construction
    }
    
    /**
     * 
     */
    public void saveScores(){
        
        File scoresFile = new File(filePath);
        scoresFile.delete();
        try {
            writeMemoryToFile();
        } catch (IOException ex) {
            
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
            for(int i=0; i<scoreList.length; i++){
                scoreList[i].setName(String.valueOf(reader.nextLine()));
                scoreList[i].setScore(reader.nextInt());
                reader.nextLine(); // eats the lone '\n'
            }
    
        } catch (FileNotFoundException ex) { 
        // File is not found make a default
            
            try {
                
                writeMemoryToFile();

            } catch (IOException ex1) {
                System.err.println("default highscore table could not be created");
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
            writer.write(activeScore.toString() + "\n");
         }
        writer.close();

    }
    
    /**
     * 
     * @return A Score Array of size 5. 
     */
    public Score[] getScoreArray(){
        return this.scoreList;
    }
    
    /**
     * 
     * @param playerScore
     * @return 
     */
    public int checkIfHighscore(int playerScore){
        
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
     * @param playerScore
     * @param name
     * @return 
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
    
    @Override
    public String toString() {
        StringBuilder SB = new StringBuilder();
        
        for (Score s0 : scoreList) {
            SB.append(s0.getName()).append(" ").append(String.valueOf(s0.getScore())).append("\n");
        }
        return SB.toString();
    }
    
    //** Example Usage
    public static void main(String[] args){
        
        ScoreTable ST = new ScoreTable(pointandclick.PointAndClick.SCOREFILEPATH);
        
        int points = 404;
        if( ST.checkIfHighscore(points) != -1 ){
            System.out.println(ST.checkIfHighscore(points));
            System.out.println(ST.secureAdd(points, "Top001"));
        }
        
    }
    //**/
    
}
