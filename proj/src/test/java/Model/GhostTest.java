package Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class GhostTest {
    Ghost ghost;

    @Before
    public void init() {
        this.ghost = new Ghost(19,11,"Red");
    }

    @Test
    public void setPosition() {
        ghost.setPosition(new Position(1,2));

        Assert.assertEquals(new Position(1,2), ghost.getPosition());
    }


    @Test
    public void getColour() {
        String colour = ghost.getColour();
        assertEquals(colour, "Red");
    }

    @Test
    public void moveUp() {
        Position temp = new Position(ghost.getPosition().getX(),ghost.getPosition().getY()-1);
        Position temp2 = ghost.moveUp();
        assertEquals(temp2, temp);

    }

    @Test
    public void moveDown() {
        Position temp = new Position(ghost.getPosition().getX(),ghost.getPosition().getY()+1);
        Position temp2 = ghost.moveDown();
        assertEquals(temp2, temp);
    }

    @Test
    public void moveLeft() {
        Position temp = new Position(ghost.getPosition().getX()-1,ghost.getPosition().getY());
        Position temp2 = ghost.moveLeft();
        assertEquals(temp2, temp);
    }

    @Test
    public void moveRight() {
        Position temp = new Position(ghost.getPosition().getX()+1,ghost.getPosition().getY());
        Position temp2 = ghost.moveRight();
        assertEquals(temp2, temp);
    }
}