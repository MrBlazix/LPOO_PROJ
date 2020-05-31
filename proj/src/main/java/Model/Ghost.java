package Model;

public class Ghost {
    private Position position = new Position();
    private String Colour;
    private Position initialPosition= new Position();

    // Ghost's constructor
    public Ghost(int x, int y, String colour){
        this.position.setX(x);
        this.position.setY(y);
        this.initialPosition.setX(x);
        this.initialPosition.setY(y);
        this.Colour = colour;
    }

    // Sets ghost's position
    public void setPosition(Position position) {
        this.position = position;
    }

    // Sets ghost's initial position
    public void setInitialPosition(){
        this.position = this.initialPosition;
    }

    // Returns position
    public Position getPosition() {
        return position;
    }

    // Returns colour
    public String getColour() {
        return Colour;
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
