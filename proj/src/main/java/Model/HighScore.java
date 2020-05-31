package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.concurrent.ConcurrentHashMap;

public class HighScore implements Serializable {

    // Variable initialization
    public static ConcurrentHashMap<String, ConcurrentHashMap<Integer,LocalDate>> highScores;

    // Constructor
    public HighScore(){
        this.highScores = new ConcurrentHashMap<>();
    }

    // Inserts user in the high score hash map
    public void insertIntoHashMap(String username,int score,LocalDate date){
        ConcurrentHashMap<Integer,LocalDate> temp_map = new ConcurrentHashMap<>();
        temp_map.put(score,date);
        highScores.put(username,temp_map);
    }

    // Returns the high scores
    public static ConcurrentHashMap<String, ConcurrentHashMap<Integer, LocalDate>> getHighScores() {
        return highScores;
    }
}
