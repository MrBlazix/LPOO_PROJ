package Controller;

import Model.*;
import View.ArenaDrawer;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;

public class Game {

    // Variable initializing
    private KeyStroke key;
    private Arena arena;
    private ArenaDrawer drawer;
    private int superCounter = 0;
    private boolean end = false;
    public static HighScore highScore;


    // Initializes the terminal and screen
    public Game(Arena arena, ArenaDrawer drawer) {
        this.arena = arena;
        this.drawer = drawer;
    }

    // Runs the game
    public void run() throws IOException {
        highScore = new HighScore();
        retrieveDataFromFile();

        KeyStroke temporaryKey = null;

        end = drawer.Menu(arena);

        if(!end){
            sound();
        }

        while (!end) {

            drawer.draw(arena);
            key = drawer.getCommand();

            if (key != null) {

                boolean res1 = processKey(key);

                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                    drawer.closeScreen();
                    end = true;
                }
                if(!res1){
                    processKey(temporaryKey);
                }
                else{
                    temporaryKey = key;

                    if (key.getKeyType() == KeyType.EOF) {
                        break;
                    }
                }
            }
            else {
                boolean res2 = processKey(temporaryKey);
            }
            try { Thread.sleep(250); } catch (InterruptedException e) { e.printStackTrace(); }
            processGhost();
            checkIfLevelOver();
        }
    }

    // Checks if the level is over
    public void checkIfLevelOver(){
        if(arena.getDots().size() == 0){
            arena.getPac().setPosition(new Position(19, 3));
            arena.increaseLevel();
            resetLevel();

        }
    }

    // Returns the distance between an object and Pac
    public int getDistance(Position position){
        int man = 0;
        man += Math.abs(position.getX() - arena.getPac().getPosition().getX());
        man += Math.abs(position.getY() - arena.getPac().getPosition().getY());

        return man;
    }

    // Calculates the ghosts next move
    public String calculateMove(Ghost ghost){
        ArrayList<String> positions = new ArrayList<>();
        HashMap<Integer,String> directions = new HashMap<>();
        int ghost_position_x = ghost.getPosition().getX();
        int ghost_position_y = ghost.getPosition().getY();

        Position position_top = new Position(ghost_position_x,ghost_position_y-1);
        int position_top_man = getDistance(position_top);
        if(arena.checkMove(position_top,0)){
            directions.put(position_top_man,"Top");
        }

        Position position_down = new Position(ghost_position_x,ghost_position_y+1);
        int position_down_man = getDistance(position_down);
        if(arena.checkMove(position_down,0)){
            directions.put(position_down_man,"Down");
        }

        Position position_left = new Position(ghost_position_x-1,ghost_position_y);
        int position_left_man = getDistance(position_left);
        if(arena.checkMove(position_left,0)){
            directions.put(position_left_man,"Left");
        }


        Position position_right = new Position(ghost_position_x+1,ghost_position_y);
        int position_right_man = getDistance(position_right);
        if(arena.checkMove(position_right,0)){
            directions.put(position_right_man,"Right");
        }

        SortedSet<Integer> keys = new TreeSet<Integer>(directions.keySet());

    if(!arena.superTime){
        return directions.get(keys.first());
    }
    else
        return directions.get(keys.last());


    }

    // Processes ghosts movement
    public synchronized void processGhost() throws IOException {
        Random rand = new Random();
        //arena.moveGhost(ghost,ghost.moveUp());

        //int rand_int1 = rand.nextInt(4);

        for (Ghost ghost : arena.getGhosts()){

            String move = calculateMove(ghost);

            switch (move){
                case "Top":
                    arena.moveGhost(ghost,ghost.moveUp());
                    break;
                case "Down":
                    arena.moveGhost(ghost,ghost.moveDown());
                    break;
                case "Left":
                    arena.moveGhost(ghost,ghost.moveLeft());
                    break;
                case "Right":
                    arena.moveGhost(ghost,ghost.moveRight());
                    break;
            }

            if (arena.detectDeath()){
                if (!arena.superTime){
                    death();
                }
                else {
                    ghostDeath(ghost);
                }
            }

        }
        superCounter++;
        if(superCounter>30){
            arena.superTime = false;
            superCounter = 0;
        }

    }

    // Processes the key pressed
    public synchronized boolean processKey(KeyStroke key){
        if(key==null){
            return false;
        }
        boolean res = false;
        switch (key.getKeyType()){
            case ArrowUp:
                res = arena.movePac(arena.getPac().moveUp());
                break;
            case ArrowDown:
                res = arena.movePac(arena.getPac().moveDown());
                break;
            case ArrowLeft:
                res = arena.movePac(arena.getPac().moveLeft());
                break;
            case ArrowRight:
                res = arena.movePac(arena.getPac().moveRight());
                break;
        }
        return res;
    }

    // Resets the level
    public void resetLevel(){
        arena.createWalls();
    }

    // Resets the game once Pac has collided with a ghost
    public void death() throws IOException {
        int currentLives = arena.getLives()-1;

        arena.setLives(currentLives);
        if (currentLives > 0){
            arena.getPac().setPosition(new Position(19, 3));
            arena.resetGhosts();
            //arena.createWalls();
        }
        else{
            highScores();
            drawer.closeScreen();
            end = true;
        }


    }

    // Ghost's death
    public void ghostDeath(Ghost ghost){
        arena.someoneScored();
        ghost.setInitialPosition();
    }

    // Soundtrack for the game
    public void sound() {
        try {
            Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
            File file = new File(path + "/Pac_Man_intro_music.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            // If you want the sound to loop infinitely, then put: clip.loop(Clip.LOOP_CONTINUOUSLY);
            // If you want to stop the sound, then use clip.stop();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Prints the high score
    public void printHighScores(){

        highScore.getHighScores().entrySet().forEach(entry->{
            String username = entry.getKey();
            entry.getValue().entrySet().forEach(entry2->{
                    int score = entry2.getKey();
                    LocalDate date = entry2.getValue();
                    System.out.println(username + " " + score + " " + date);
                    System.out.println(highScore.getHighScores().size());
            });

        });
    }

    // High Scores
    public void highScores(){

        String username = System.getProperty("user.name");
        int score = arena.getScore();
        LocalDate date = LocalDate.now();

        highScore.insertIntoHashMap(username,score,date);
        printHighScores();
        saveDataInFile();
    }

    /*
    * Saves the data on the designated files
    * Based on "https://www.tutorialspoint.com/java/java_serialization.htm"
    */
    public synchronized void saveDataInFile(){
        try {
            String filename = "Data.ser";

            File file = new File(filename);
            if (!file.exists()) {
               // file.getParentFile().mkdirs();
                file.createNewFile();
            }

            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(highScore);
            out.close();
            fileOut.close();

        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /*
    * Retrieves the data from the file
    * Based on "https://www.tutorialspoint.com/java/java_serialization.htm"
    */
    public synchronized void retrieveDataFromFile(){
        try {
            String filename = "Data.ser";


            File file = new File(filename);
            if (!file.exists()) {
                return;
            }

            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            highScore = (HighScore) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Storage class not found");
            c.printStackTrace();
        }
    }
}
