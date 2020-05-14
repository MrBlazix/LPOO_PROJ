package Controller;

import Model.Arena;
import Model.Ghost;
import Model.Pac;
import View.ArenaDrawer;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;


import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class Game {

    private KeyStroke key;
    private Arena arena;
    private ArenaDrawer drawer;


    //Initializes the terminal and screen
    public Game(Arena arena, ArenaDrawer drawer) {
        this.arena = arena;
        this.drawer = drawer;
    }


    public void run() throws IOException {

        KeyStroke temporaryKey = null;

        while (true) {
            drawer.draw(arena);

            key = drawer.getCommand();

            if (key != null) {

                boolean res1 = processKey(key);
                if(!res1){
                    processKey(temporaryKey);
                }
                else{
                    temporaryKey = key;

                    if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                     drawer.closeScreen();
                    }
                    if (key.getKeyType() == KeyType.EOF) {
                        break;
                    }
                }
            }
            else {
                boolean res2 = processKey(temporaryKey);
            }

            processGhost();

            try { Thread.sleep(150); } catch (InterruptedException e) { e.printStackTrace(); }

        }


    }

    public synchronized void processGhost(){
        Random rand = new Random();

        int rand_int1 = rand.nextInt(4);
        for (Ghost ghost : arena.getGhosts()){
            switch (rand_int1){
                case 0:
                    arena.moveGhost(ghost,ghost.moveUp());
                    break;
                case 1:
                    arena.moveGhost(ghost,ghost.moveDown());
                    break;
                case 2:
                    arena.moveGhost(ghost,ghost.moveLeft());
                    break;
                case 3:
                    arena.moveGhost(ghost,ghost.moveRight());
                    break;
            }

        }

    }

    public synchronized boolean processKey(KeyStroke key){
        if(key==null){
            return false;
        }
        boolean res = false;
        switch (key.getKeyType()){
            case ArrowUp:
                res = arena.movePac(arena.getPac().moveUp());
                break;
            case ArrowDown:
                res = arena.movePac(arena.getPac().moveDown());
                break;
            case ArrowLeft:
                res = arena.movePac(arena.getPac().moveLeft());
                break;
            case ArrowRight:
                res = arena.movePac(arena.getPac().moveRight());
                break;
        }
        return res;
    }
}
