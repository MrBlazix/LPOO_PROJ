import com.googlecode.lanterna.TextCharacter;
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

    //Initializes the terminal and screen
    public Game(){
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
        pac.draw(screen);
        screen.refresh();
    }

    public void run() throws IOException {
        while(true){
            draw();
            key = screen.readInput();
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){screen.close();}
            if (key.getKeyType() == KeyType.EOF){break;}
            processKey(key);
        }

    }

    private void processKey(KeyStroke key){
        switch (key.getKeyType()){
            case ArrowUp:
                movePac(pac.moveUp());
                break;
            case ArrowDown:
                movePac(pac.moveDown());
                break;
            case ArrowLeft:
                movePac(pac.moveLeft());
                break;
            case ArrowRight:
                movePac(pac.moveRight());
                break;
        }
    }

    private void movePac(Position position){pac.setPosition(position);}
}
