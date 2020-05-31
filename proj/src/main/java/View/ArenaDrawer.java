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

    // Variable initialization
    private Screen screen;

    // Constructor
    public ArenaDrawer(int width, int height) throws IOException {
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(100, 100)).createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
    }

    // Main Menu
    public boolean Menu(Model.Arena arena) throws IOException {
        boolean advance=false;
        screen.clear();

        TextGraphics graphics = screen.newTextGraphics();

        screen.clear();

        graphics.setBackgroundColor(TextColor.Factory.fromString("#111111"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(arena.getWidth() , arena.getHeight() ), ' ');
        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.putString(1,1, "Welcome to Pac-Man");
        graphics.putString(1,2, " ");
        graphics.putString(1,3, "F1 - Play");
        graphics.putString(1,4, "F2 - Instructions");
        graphics.putString(1,5, "F3 - Exit");

        screen.refresh();

        while(!advance){
            KeyStroke key = getCommandNotPoll();
                switch (key.getKeyType()){
                    case F1:
                        advance = true;
                        break;
                    case F2:
                        advance = true;
                        instructions(graphics);
                        break;
                    case F3:
                        closeScreen();
                        return true;
                    default:
                        graphics.putString(1,8, "Press a valid key");
                        screen.refresh();
        }
    }
        return false;
    }

    // Draws the arena
    public void draw(Model.Arena arena) throws IOException{
        screen.clear();

        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#111111"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(arena.getWidth() , arena.getHeight() ), ' ');
        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.putString(1,23, "Score: " + arena.getScore());
        graphics.putString(30,23, "Lives : " + arena.getLives());
        graphics.putString(15,23, "Level : " + arena.getLevel());

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(arena.getPac().getPosition().getX(), arena.getPac().getPosition().getY()), "O");


        for (Wall wall : arena.getWalls()){
            graphics.setForegroundColor(TextColor.Factory.fromString("#0E3EE1"));
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(new TerminalPosition(wall.getPosition().getX(), wall.getPosition().getY()), 	"#");
        }

        for(Dot dot : arena.getDots()){
            if(!arena.superTime){
                graphics.setForegroundColor(TextColor.Factory.fromString("#F8FF8B"));
            }
            else{
                graphics.setForegroundColor(TextColor.Factory.fromString("#0E3EE1"));
            }

            graphics.enableModifiers(SGR.BOLD);
            if (dot.getType() == "Normal"){
                graphics.putString(new TerminalPosition(dot.getPosition().getX(), dot.getPosition().getY()), 	".");
            }
            else if (dot.getType() == "Super"){
                graphics.putString(new TerminalPosition(dot.getPosition().getX(), dot.getPosition().getY()), 	"*");
            }
        }

        for (Ghost ghost : arena.getGhosts()){
            if(!arena.superTime){
                graphics.setForegroundColor(TextColor.Factory.fromString(convertToHex(ghost.getColour())));
            }
            else{
                graphics.setForegroundColor(TextColor.Factory.fromString("#0E3EE1"));
            }

            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(new TerminalPosition(ghost.getPosition().getX(), ghost.getPosition().getY()), "B");
        }
        screen.refresh();
    }

    // Polling of the keyboard input
    public KeyStroke getCommand() throws IOException {
        while (true) {
            KeyStroke key = screen.pollInput();
            return key;
        }
    }

    // Reads the keyboard input
    public KeyStroke getCommandNotPoll() throws IOException {
            KeyStroke key = screen.readInput();
            return key;
    }

    // Closes the screen
    public void closeScreen() throws IOException {
        screen.close();
    }

    // Instructions menu
    public void instructions(TextGraphics graphics) throws IOException {
        screen.clear();
        graphics.putString(1,1, "Arrow Up To Move Up");
        graphics.putString(1,2, "Arrow Down To Move Down");
        graphics.putString(1,3, "Arrow Left To Move Left");
        graphics.putString(1,4, "Arrow Right To Move Right");
        graphics.putString(1,5, "Q To Exit");
        graphics.putString(1,6, " ");
        graphics.putString(1,7, "F1 - Play");
        screen.refresh();

        while(true){
            KeyStroke key = screen.readInput();
            if (key.getKeyType()== KeyType.F1){
                return;
            }
        }
    }

    // Converts a specified color name to his hexadecimal
    public String convertToHex(String color){
        String return_color;
        switch (color){
            case "Pink":
                return_color = "#FFC0CB";
                break;

            case "Blue":
                return_color = "#0000FF";
                break;

            case "Yellow":
                return_color = "#FFFF00";
                break;

            case "Red":
                return_color = "#FF0000";
                break;

            default: return_color = "#000000";
            break;
        }
        return return_color;
    }
}
