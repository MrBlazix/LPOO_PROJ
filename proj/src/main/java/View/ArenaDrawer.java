package View;

import Model.Dot;
import Model.Ghost;
import Model.Wall;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class ArenaDrawer {
    private Screen screen;


    public ArenaDrawer(int width, int height) throws IOException {
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(100, 100)).createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
    }

    public void draw(Model.Arena arena) throws IOException{
        screen.clear();


        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#111111"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(arena.getWidth() , arena.getHeight() ), ' ');
        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.putString(1,1, "Score: " + arena.getScore());

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(arena.getPac().getPosition().getX(), arena.getPac().getPosition().getY()), "O");


        for (Wall wall : arena.getWalls()){
            graphics.setForegroundColor(TextColor.Factory.fromString("#0E3EE1"));
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(new TerminalPosition(wall.getPosition().getX(), wall.getPosition().getY()), 	"#");
        }

        for(Dot dot : arena.getDots()){
            graphics.setForegroundColor(TextColor.Factory.fromString("#F8FF8B"));
            graphics.enableModifiers(SGR.BOLD);
            if (dot.getType() == "Normal"){
                graphics.putString(new TerminalPosition(dot.getPosition().getX(), dot.getPosition().getY()), 	".");
            }
            else if (dot.getType() == "Super"){
                graphics.putString(new TerminalPosition(dot.getPosition().getX(), dot.getPosition().getY()), 	"*");
            }
        }

        for (Ghost ghost : arena.getGhosts()){
            graphics.setForegroundColor(TextColor.Factory.fromString(ghost.getColour()));
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(new TerminalPosition(ghost.getPosition().getX(), ghost.getPosition().getY()), "B");
        }



        screen.refresh();
    }

    public KeyStroke getCommand() throws IOException {
        while (true) {
            KeyStroke key = screen.pollInput();
            return key;
        }
    }

    public void closeScreen() throws IOException {
        screen.close();
    }

}
