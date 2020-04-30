import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {

    private int width;
    private int height;
    private Pac pac;
    private List<Wall> walls;
    private List<Dot> dots;
    private List<Position> path;
    private int score;
    JLabel scoreLabel;

    public Arena(int width, int height, Pac pac){
        this.width = width;
        this.height = height;
        this.pac = pac;
        createWalls();
        this.score = 0;
    }

    public synchronized boolean processKey(KeyStroke key){
        if(key==null){
            return false;
        }
        boolean res = false;
        switch (key.getKeyType()){
            case ArrowUp:
                res = movePac(pac.moveUp());
                break;
            case ArrowDown:
                res = movePac(pac.moveDown());
                break;
            case ArrowLeft:
                res = movePac(pac.moveLeft());
                break;
            case ArrowRight:
                res = movePac(pac.moveRight());
                break;
        }
        return res;
    }

    private void createWalls() {
        List<Wall> walls = new ArrayList<>();
        List<Dot> dots = new ArrayList<>();
        List<Position> path = new ArrayList<>();

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
    }

    public void draw(TextGraphics graphics) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#111111"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width , height ), ' ');
        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.putString(1,1, "Score: " + score);

        pac.draw(graphics);

        for (Wall wall : walls)
            wall.draw(graphics);
        for(Dot dot : dots)
            dot.draw(graphics);
    }

    private boolean movePac(Position position){
        if(checkMove(position)){
            pac.setPosition(position);
            return true;
        }
        return false;
    }

    private boolean checkMove(Position position) {

        if (position.getX() < 0) return false;

        if (position.getX() > width - 1) return false;

        if (position.getY() < 0) return false;

        if (position.getY() > height - 1) return false;

        for (Wall wall : walls)
            if (wall.getPosition().equals(position)) return false;

        for(Dot dot : dots)
            if(dot.getPosition().equals(position)){
                retrieveDots(dots.indexOf(dot));
                someoneScored();
                break;
            }
        for (Position position1 : path)
            if(position1.equals(position) && testPath(position)){

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

}

