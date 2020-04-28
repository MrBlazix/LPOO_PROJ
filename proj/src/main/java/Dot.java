import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class Dot {
    private Position position = new Position();

    //Constructor with pre defined coordinates
    public Dot(int x, int y){
        this.position.setX(x);
        this.position.setY(y);
    }

    public Position getPosition(){return position;}

    public void draw(TextGraphics graphics) throws IOException {
        graphics.setForegroundColor(TextColor.Factory.fromString("#F8FF8B"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), 	"o");
    }

}
