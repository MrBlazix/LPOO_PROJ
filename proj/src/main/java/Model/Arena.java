package Model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arena {

    private int width;
    private int height;
    private Pac pac;
    private List<Ghost> ghosts;
    private List<Wall> walls;
    private List<Dot> dots;
    private List<Position> path;
    private List<Position> ghostArea;
    private int score;
    JLabel scoreLabel;

    public Arena(int width, int height, Pac pac){
        this.width = width;
        this.height = height;
        this.pac = pac;
        createWalls();
        this.score = 0;
    }


    private void createWalls() {

        List<Ghost> ghosts = new ArrayList<>();
        List<Wall> walls = new ArrayList<>();
        List<Dot> dots = new ArrayList<>();
        List<Position> path = new ArrayList<>();
        List<Position> ghostArea = new ArrayList<>();

        ghosts.add(new Ghost(9,15,"#FF0000"));
        ghosts.add(new Ghost(20,13,"#89cff0"));

        try (FileReader f = new FileReader("map.txt")) {
            StringBuffer sb = new StringBuffer();
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



    public boolean movePac(Position position){
        if(checkMove(position,1)){
            pac.setPosition(position);
            return true;
        }
        return false;
    }

    public boolean moveGhost(Ghost ghost, Position position){
        if(checkMove(position,0)){
            ghost.setPosition(position);
            return true;
        }
        return false;
    }

    public List<Ghost> getGhosts() {
        return ghosts;
    }

    private boolean checkMove(Position position, int type) {

        if (position.getX() < 0) return false;

        if (position.getX() > width - 1) return false;

        if (position.getY() < 0) return false;

        if (position.getY() > height - 1) return false;

        for (Wall wall : walls)
            if (wall.getPosition().equals(position)) return false;

        for(Dot dot : dots)
            if(dot.getPosition().equals(position) && type ==1){
                retrieveDots(dots.indexOf(dot));
                someoneScored();
                break;
            }
        for (Position position1 : path)
            if(position1.equals(position) && testPath(position)){

                return true;
            }

        for (Position position1 : ghostArea)
            if(position1.equals(position) && testPath(position) && (type ==0)){

                return true;
            }


        return false;
    }

    public boolean testPath(Position position){
        Position tempPosition1 = new Position(position.getX(),position.getY()+1);
        Position tempPosition2 = new Position(position.getX(),position.getY()-1);
        if(this.walls.contains(new Wall(position.getX()+1,position.getY())) || this.walls.contains(new Wall(position.getX()-1,position.getY()))){
            return false;
        }
        return true;
    }


    private void retrieveDots(int i){
        dots.remove(i);
    }

    public void someoneScored()
    {
        score++;
    }

    public int getHeight() {
        return height;
    }

    public int getScore() {
        return score;
    }

    public int getWidth() {
        return width;
    }

    public Pac getPac() {
        return pac;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public List<Dot> getDots() {
        return dots;
    }
}

