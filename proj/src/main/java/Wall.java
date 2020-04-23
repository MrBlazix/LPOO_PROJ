import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Wall {
    private Position position = new Position();

    //Constructor with pre defined coordinates
    public Wall(int x, int y){
        this.position.setX(x);
        this.position.setY(y);
    }

    public Position getPosition(){return position;}

    public void draw(TextGraphics graphics) throws IOException {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), 	"#");
    }
}
