package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class HighScore implements Serializable {
    public static ConcurrentHashMap<String, ConcurrentHashMap<Integer,LocalDate>> highScores;

    public HighScore(){
        this.highScores = new ConcurrentHashMap<>();
    }

    public void insertIntoHashMap(String username,int score,LocalDate date){
        ConcurrentHashMap<Integer,LocalDate> temp_map = new ConcurrentHashMap<>();
        temp_map.put(score,date);
        highScores.put(username,temp_map);
    }

    public static ConcurrentHashMap<String, ConcurrentHashMap<Integer, LocalDate>> getHighScores() {
        return highScores;
    }


}
