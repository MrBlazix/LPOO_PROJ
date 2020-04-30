import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.Objects;

public class Wall {
    private Position position = new Position();

    //Constructor with pre defined coordinates
    public Wall(int x, int y){
        this.position.setX(x);
        this.position.setY(y);
    }

    public Position getPosition(){return position;}

    public void draw(TextGraphics graphics) throws IOException {
        graphics.setForegroundColor(TextColor.Factory.fromString("#0E3EE1"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), 	"#");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wall)) return false;
        Wall wall = (Wall) o;
        return position.equals(wall.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
