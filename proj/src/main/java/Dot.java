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

    public void draw(TextGraphics graphics) throws IOException {
        graphics.setForegroundColor(TextColor.Factory.fromString("#F8FF8B"));
        graphics.enableModifiers(SGR.BOLD);
        if (this.type == "Normal"){
            graphics.putString(new TerminalPosition(position.getX(), position.getY()), 	".");
        }
        else if (this.type == "Super"){
            graphics.putString(new TerminalPosition(position.getX(), position.getY()), 	"*");
        }

    }

}
