package Model;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class Dot {
    private Position position = new Position();
    private String type;

    //Constructor with pre defined coordinates
    public Dot(int x, int y, String type){
        this.position.setX(x);
        this.position.setY(y);
        this.type = type;
    }

    public Position getPosition(){return position;}

    public String getType() {
        return type;
    }
}
