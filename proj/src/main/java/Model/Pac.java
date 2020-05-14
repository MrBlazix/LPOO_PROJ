package Model;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Pac {

    //Creates a new Model.Position for Model.Pac
    private Position position = new Position();


    //Constructor with pre defined coordinates
    public Pac (int x, int y){
        this.position.setX(x);
        this.position.setY(y);
    }


    //Returns Model.Position
    public Position getPosition(){return position;}

    public void setPosition(Position position){
        this.position.setY(position.getY());
        this.position.setX(position.getX());
    }

    public Position moveUp(){
        return new Position(position.getX(), position.getY() - 1);

    }

    public Position moveDown(){return new Position(position.getX(), position.getY() + 1);}

    public Position moveLeft(){return new Position(position.getX() -1 , position.getY());}

    public Position moveRight(){return new Position(position.getX() + 1, position.getY());}


}

