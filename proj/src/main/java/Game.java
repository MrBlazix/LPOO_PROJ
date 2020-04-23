import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
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
    private Arena arena;

    //Initializes the terminal and screen
    public Game(){
        try{
            terminal = (Terminal) new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(100,100)).createTerminal();
            screen = new TerminalScreen(terminal);
            pac = new Pac(10,10);
            arena = new Arena(39,25,pac);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }

    public void run() throws IOException {
        while(true){
            draw();
            key = screen.readInput();
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){screen.close();}
            if (key.getKeyType() == KeyType.EOF){break;}
            arena.processKey(key);
        }

    }


}
