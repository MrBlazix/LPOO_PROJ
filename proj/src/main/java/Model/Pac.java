package Model;

public class Pac {

    //Creates a new position for Pac
    private Position position = new Position();


    //Constructor with pre defined coordinates
    public Pac (int x, int y){
        this.position.setX(x);
        this.position.setY(y);
    }

    //Returns position
    public Position getPosition(){return position;}

    // Sets Pac position
    public void setPosition(Position position){
        this.position.setY(position.getY());
        this.position.setX(position.getX());
    }

    // Moves up
    public Position moveUp(){
        return new Position(position.getX(), position.getY() - 1);
    }

    // Moves down
    public Position moveDown(){return new Position(position.getX(), position.getY() + 1);}

    // Moves left
    public Position moveLeft(){return new Position(position.getX() -1 , position.getY());}

    // Moves right
    public Position moveRight(){return new Position(position.getX() + 1, position.getY());}
}

