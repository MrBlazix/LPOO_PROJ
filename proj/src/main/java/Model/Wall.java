package Model;

import java.util.Objects;

public class Wall {
    private Position position = new Position();

    //Constructor with pre defined coordinates
    public Wall(int x, int y){
        this.position.setX(x);
        this.position.setY(y);
    }

    // Returns wall's position
    public Position getPosition(){return position;}


    // Overrides the equals method to check if two walls are the same
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wall)) return false;
        Wall wall = (Wall) o;
        return position.equals(wall.position);
    }

    // Overrides the hashCode method to return the has code of the wall's position
    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
