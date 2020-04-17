import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Pac {

    //Creates a new Position for Pac
    private Position position = new Position();

    //Constructor with pre defined coordinates
    public Pac (int x_pos, int y_pos){
        this.position.setX(x_pos);
        this.position.setY(y_pos);
    }

    //Returns Position
    public Position getPosition(){return position;}


}
