package Model;
import java.util.Random;

public class Ghost {
    private Position position = new Position();
    private String Colour;
    private Position initialPosition= new Position();

    public Ghost(int x, int y, String colour){
        this.position.setX(x);
        this.position.setY(y);
        this.initialPosition.setX(x);
        this.initialPosition.setY(y);
        this.Colour = colour;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setInitialPosition(){
        this.position = this.initialPosition;
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
