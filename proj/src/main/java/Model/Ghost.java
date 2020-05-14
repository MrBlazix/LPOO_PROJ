package Model;
import java.util.Random;

public class Ghost {
    private Position position = new Position();
    private String Colour;

    public Ghost(int x, int y, String colour){
        this.position.setX(x);
        this.position.setY(y);
        this.Colour = colour;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public String getColour() {
        return Colour;
    }

    public Position moveUp(){
        return new Position(position.getX(), position.getY() - 1);

    }

    public Position moveDown(){return new Position(position.getX(), position.getY() + 1);}

    public Position moveLeft(){return new Position(position.getX() -1 , position.getY());}

    public Position moveRight(){return new Position(position.getX() + 1, position.getY());}



}
