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
    public Game(){
        try{
            terminal = (Terminal) new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(100,100)).createTerminal();
            screen = new TerminalScreen(terminal);
            pac = new Pac(9,10);
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

        TimerTask task = new TimerTask()
        {
            public void run()
            {
                boolean res = arena.processKey(key);
                try {  draw(); } catch (IOException e) { e.printStackTrace(); }
                if (!res){
                    t.cancel(false);
                }
            }
        };

        while(true){
            draw();

            key = screen.readInput();

            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){screen.close();}
            if (key.getKeyType() == KeyType.EOF){break;}


            t = scheduler.scheduleAtFixedRate(task,0,1, TimeUnit.SECONDS);


        }

    }


}
