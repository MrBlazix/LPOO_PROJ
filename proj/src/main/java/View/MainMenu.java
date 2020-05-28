package View;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

import static com.googlecode.lanterna.input.KeyType.*;

public class MainMenu {

    private Screen screen;
    private KeyStroke key;
    private TextGraphics graphics;
    private int option = -1;

    public MainMenu() throws IOException {
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(100, 100)).createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

    }

    public void setInfoMain(){
        graphics = screen.newTextGraphics();

        screen.clear();

        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.putString(1,1, "Welcome to Pac-Man");
        graphics.putString(1,2, " ");
        graphics.putString(1,3, "F1 - Play");
        graphics.putString(1,4, "F2 - Instructions");
        graphics.putString(1,5, "F3 - Exit");
    }

    public int checkInputMenu(){

        while(option < 1 || option > 3){
            switch (key.getKeyType()){
                case F1:
                    option = 1;
                    return option;
                case F2:
                    option = 2;
                    return option;
                case F3:
                    option = 3;
                    return option;
            }
        }
        return option;
    }

    public void setInfoInstruc(){
        screen.clear();
        graphics.putString(1,1, "Arrow Up To Move Up");
        graphics.putString(1,2, "Arrow Down To Move Down");
        graphics.putString(1,3, "Arrow Left To Move Left");
        graphics.putString(1,4, "Arrow Right To Move Right");
        graphics.putString(1,5, "Q To Exit");
        graphics.putString(1,6, " ");
        graphics.putString(1,7, "F1 - Play");
    }

}
