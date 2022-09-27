package pointandclick.Frames.highscoreResources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class ScoreTable {
    
    private final String filePath;
    private Score[] scoreList;
    
    
    public ScoreTable(String filePath){
        
        this.filePath = filePath;
        
        this.scoreList = new Score[5];
        for (int i=0; i<scoreList.length; i++) {
            scoreList[i] = new Score(i+1, "-", "-"); // Actual will have 0 and 2 blanks
        }
        
        loadScores(); // Pull Scores after construction
    }
    
    public void saveScores(){
        
        File scoresFile = new File(filePath);
        
        
        
    }
    
    /**
     * Pull Scores from the highscore.txt file. If the file does not exist 
     * a default will be created and loaded.
     */
    private void loadScores(){
        
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
                FileWriter writer;
                scoresFile.createNewFile();
                writer = new FileWriter(scoresFile);
                for (var activeScore : this.scoreList) {
                    writer.write(activeScore.toString() + "\n");
                 }
                writer.close();

            } catch (IOException ex1) {
                System.err.println("default highscore table could not be created");
            }
        
        } // Catch end
    }
    
    public boolean isHighscore(int playerScore){
    
        return false;
    }
    
    
    
    //Test only
    public static void main(String[] args){
        ScoreTable test = new ScoreTable("Data/highscore.txt");
        test.saveScores();
    }
    
    
    
}
