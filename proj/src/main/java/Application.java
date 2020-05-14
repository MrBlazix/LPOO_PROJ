import Controller.Game;
import Model.Arena;
import Model.Pac;
import View.ArenaDrawer;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        Pac pac = new Pac(9, 10);
        Arena arena = new Arena(39, 25, pac);
        ArenaDrawer drawer = new ArenaDrawer(39, 25);
        Game game = new Game(arena,drawer);
        game.run();
    }
}