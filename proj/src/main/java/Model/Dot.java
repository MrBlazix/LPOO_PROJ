package Model;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;
import java.util.Objects;

public class Dot {
    private Position position = new Position();
    private String type;

    //Constructor with pre defined coordinates
    public Dot(int x, int y, String type){
        this.position.setX(x);
        this.position.setY(y);
        this.type = type;
    }

    // Returns Position
    public Position getPosition(){return position;}

    // Returns dot's type
    public String getType() {
        return type;
    }

    // Override the equals method to check if two positions are the same
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dot)) return false;
        Dot dot = (Dot) o;
        return position.equals(dot.position) &&
                type.equals(dot.type);
    }

    // Override the hashCode method to return the hash of the dot
    @Override
    public int hashCode() {
        return Objects.hash(position, type);
    }
}
