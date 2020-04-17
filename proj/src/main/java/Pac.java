import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Pac {

    //Creates a new Position for Pac
    private Position position = new Position();

    //Constructor with pre defined coordinates
    public Pac (int x, int y){
        this.position.setX(x);
        this.position.setY(y);
    }

    //Returns Position
    public Position getPosition(){return position;}

    public void setPosition(Position position){
        this.position.setY(position.getY());
        this.position.setX(position.getX());
    }

    public Position moveUp(){return new Position(position.getX(), position.getY() - 1);}

    public Position moveDown(){return new Position(position.getX(), position.getY() + 1);}

    public Position moveLeft(){return new Position(position.getX() -1 , position.getY());}

    public Position moveRight(){return new Position(position.getX() + 1, position.getY());}

    public void draw(Screen screen) throws IOException {
        screen.clear();
        screen.setCharacter(getPosition().getX(), getPosition().getY(), new TextCharacter('O'));
        screen.refresh();
    }
}

