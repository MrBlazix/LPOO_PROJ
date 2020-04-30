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
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Game {

    private Screen screen;
    private Terminal terminal;
    private KeyStroke key;
    private Pac pac;
    private Arena arena;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    static ScheduledFuture<?> t;

    //Initializes the terminal and screen
    public Game() {
        try {
            terminal = (Terminal) new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(100, 100)).createTerminal();
            screen = new TerminalScreen(terminal);
            pac = new Pac(9, 10);
            arena = new Arena(39, 25, pac);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }


    public void run() throws IOException {

        KeyStroke temporaryKey = null;

        while (true) {
            draw();

            key = screen.pollInput();

            if (key != null) {

                boolean res1 = arena.processKey(key);
                if(!res1){
                    arena.processKey(temporaryKey);
                }
                else{
                    temporaryKey = key;

                    if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                     screen.close();
                    }

                    if (key.getKeyType() == KeyType.EOF) {
                        break;
                    }
                }

            }
            else {
                boolean res2 = arena.processKey(temporaryKey);
            }


            try { Thread.sleep(150); } catch (InterruptedException e) { e.printStackTrace(); }

        }


    }
}
