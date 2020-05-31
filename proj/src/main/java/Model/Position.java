package Model;

import java.util.Objects;

public class Position {

    // Variable initialization
    private int x;
    private int y;

    // Constructor
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    // Constructor
    public Position() {this(0, 0);}

    // Returns x position
    public int getX() {return x;}

    // Sets x position
    public void setX(int x) {this.x = x;}

    // Returns y position
    public int getY() {return y;}

    // Sets y position
    public void setY(int y) {this.y = y;}

    // Overrides the equals method to check if two positions are the same
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    // Overrides the hashCode method to return the has code of the position
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
