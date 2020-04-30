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

        /*for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, 0));
        }*/

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
                    column++;
                }
                else if(c== '*'){
                    dots.add(new Dot(column, row,"Super"));
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
        return true;
    }

    /* private List<Dot> createDots(){
        Random random = new Random();
        ArrayList<Dot> dots = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            dots.add(new Dot(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        }
        return dots;
    }*/

    private void retrieveDots(int i){
        dots.remove(i);
    }

    public void someoneScored()
    {
        score++;
    }

}

