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
     * 
     * @param filePath 
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
     * @param playerScore
     * @return 
     */
    public boolean checkIfHighscore(int playerScore){
    
        return false;
    }
    
    @Override
    public String toString() {
        StringBuilder SB = new StringBuilder();
        
        for(int i=0; i<scoreList.length; i++){
            SB.append(scoreList[i].getName()).append(" ").append(String.valueOf(scoreList[i].getScore())).append("\n");
        }
        return SB.toString();
    }
    
}
