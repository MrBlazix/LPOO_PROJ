package Model;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arena {

    // Variable initialization
    private int width;
    private int height;
    public boolean superTime;
    private int lives;
    private Pac pac;
    private List<Ghost> ghosts;
    private List<Wall> walls;
    private List<Dot> dots;
    private List<Position> path;
    private List<Position> ghostArea;
    private int score;
    private int level;

    // Creates the arena
    public Arena(int width, int height, Pac pac){
        this.width = width;
        this.height = height;
        this.pac = pac;
        createWalls();
        this.score = 0;
        this.lives = 3;
        this.level = 1;
    }

    // Returns current lives
    public int getLives() {
        return lives;
    }

    // Sets current lives
    public void setLives(int lives) {
        this.lives = lives;
    }

    // Increases level
    public void increaseLevel(){
        this.level++;
    }

    // Returns current level
    public int getLevel() {
        return level;
    }

    // Resets the ghosts
    public void resetGhosts(){
        for(Ghost ghost : ghosts){
            ghost.setInitialPosition();
        }
    }

    // Creates the walls and dots
    public void createWalls() {

        List<Ghost> ghosts = new ArrayList<>();
        List<Wall> walls = new ArrayList<>();
        List<Dot> dots = new ArrayList<>();
        List<Position> path = new ArrayList<>();
        List<Position> ghostArea = new ArrayList<>();

        ghosts.add(new Ghost(19,11,"Red"));
        ghosts.add(new Ghost(21,11,"Blue"));


        try (FileReader f = new FileReader("map.txt")) {

            int row = 0;
            int column = 0;
            while (f.ready()) {
                char c = (char) f.read();
                if (c == '\n') {
                    row++;
                    column = 0;
                } else if(c == '#') {
                    walls.add(new Wall(column,row));
                    column++;
                }
                else if(c== '.'){
                    dots.add(new Dot(column, row,"Normal"));
                    path.add(new Position(column, row));
                    column++;
                }
                else if(c== '*'){
                    dots.add(new Dot(column, row,"Super"));
                    path.add(new Position(column, row));
                    column++;
                }
                else if(c== ' '){
                    path.add(new Position(column, row));
                    column++;
                }
                else if(c== '-'){
                    ghostArea.add(new Position(column, row));
                    column++;
                }

                else{
                    column++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.walls = walls;
        this.dots = dots;
        this.path = path;
        this.ghosts = ghosts;
        this.ghostArea = ghostArea;
    }

    // Checks if the dot is a superpoint
    public boolean isSuperPoint(Position position){

        Dot temp_dot = new Dot(position.getX(),position.getY(),"Super");

        if(this.dots.contains(temp_dot)){
            return true;
        }
        else
            return false;
    }

    // Moves Pac
    public boolean movePac(Position position){
        if(checkMove(position,1)){
            pac.setPosition(position);
            return true;
        }
        return false;
    }

    // Moves ghosts
    public boolean moveGhost(Ghost ghost, Position position){
        if(checkMove(position,0)){
            ghost.setPosition(position);
            return true;
        }
        return false;
    }

    // Returns ghosts
    public List<Ghost> getGhosts() {
        return ghosts;
    }

    // Checks if Pac has collided with some ghost
    public boolean detectDeath(){
        for(Ghost ghost : ghosts){
            if(ghost.getPosition().equals(this.getPac().getPosition())){
                return true;
            }
        }
        return false;
    }

    // Checks the move to be made
    public boolean checkMove(Position position, int type) {

        if (position.getX() < 0) return false;

        if (position.getX() > width - 1) return false;

        if (position.getY() < 0) return false;

        if (position.getY() > height - 1) return false;

        for (Wall wall : walls)
            if (wall.getPosition().equals(position)) return false;

        for(Dot dot : dots)
            if(dot.getPosition().equals(position) && type ==1){
                if(isSuperPoint(position)){
                    this.superTime = true;
                }

                retrieveDots(dots.indexOf(dot));

                someoneScored();
                break;
            }

        if(checkIfGhost(position)){
            return false;
        }
        for (Position position1 : path)
            if(position1.equals(position) && testPath(position)){

                return true;
            }

        for (Position position1 : ghostArea){
            if(position1.equals(position) && testPath(position) && (type ==0)){

                return true;
            }
        }


        return false;
    }

    // Tests the current path
    public boolean testPath(Position position){

        if(this.walls.contains(new Wall(position.getX()+1,position.getY())) || this.walls.contains(new Wall(position.getX()-1,position.getY()))){
            return false;
        }
        return true;
    }

    // Checks if it's a ghost
    public boolean checkIfGhost(Position position){

        for(Ghost ghost : ghosts){
            if(ghost.getPosition().equals(position)){
                return true;
            }
        }
        return false;
    }

    // Deletes a dot
    private void retrieveDots(int i){
        dots.remove(i);
    }

    // Increments score
    public void someoneScored()
    {
        score++;
    }

    // Returns height
    public int getHeight() {
        return height;
    }

    // Returns score
    public int getScore() {
        return score;
    }

    // Returns width
    public int getWidth() {
        return width;
    }

    // Returns Pac
    public Pac getPac() {
        return pac;
    }

    // Returns walls
    public List<Wall> getWalls() {
        return walls;
    }

    // Returns dots
    public List<Dot> getDots() {
        return dots;
    }
}

