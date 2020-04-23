import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

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

    public Arena(int width, int height, Pac pac){
        this.width = width;
        this.height = height;
        this.pac = pac;
        this.walls = createWalls();
        this.dots = createDots();
        this.score = 0;
    }

    public void processKey(KeyStroke key){
        switch (key.getKeyType()){
            case ArrowUp:
                movePac(pac.moveUp());
                break;
            case ArrowDown:
                movePac(pac.moveDown());
                break;
            case ArrowLeft:
                movePac(pac.moveLeft());
                break;
            case ArrowRight:
                movePac(pac.moveRight());
                break;
        }
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, 0));
        }

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
                else{
                    column++;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return walls;
    }

    public void draw(TextGraphics graphics) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#111111"));

        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width , height ), ' ');
        pac.draw(graphics);
        for (Wall wall : walls)
            wall.draw(graphics);
        for(Dot dot : dots)
            dot.draw(graphics);
    }

    private void movePac(Position position){
        if(checkMove(position)){
            pac.setPosition(position);
        }

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
                score += 1;
                break;
            }
        return true;
    }

    private List<Dot> createDots(){
        Random random = new Random();
        ArrayList<Dot> dots = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            dots.add(new Dot(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        }
        return dots;
    }

    private void retrieveDots(int i){
        dots.remove(i);
    }
}
