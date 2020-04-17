import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;


import java.io.IOException;

public class Game {

    private Screen screen;
    private Terminal terminal;
    private KeyStroke key;
    private Pac pac;

    //Initializes the terminal and screen
    public Game() throws IOException {
        try{
            terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            pac = new Pac(10,10);

            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {
        screen.clear();
        screen.setCharacter(pac.getPosition().getY(), pac.getPosition().getY(), new TextCharacter('O'));
        screen.refresh();
    }

    public void run() throws IOException {
        draw();
    }

}
