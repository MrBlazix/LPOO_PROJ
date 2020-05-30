package Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PacTest {
    Pac pac;

    @Before
    public void init() {
        this.pac = new Pac(19,11);
    }

    @Test
    public void setPosition() {
        pac.setPosition(new Position(1,2));

        Assert.assertEquals(new Position(1,2), pac.getPosition());
    }

    @Test
    public void moveUp() {
        Position temp = new Position(pac.getPosition().getX(),pac.getPosition().getY()-1);
        Position temp2 = pac.moveUp();
        assertEquals(temp2, temp);
    }

    @Test
    public void moveDown() {
        Position temp = new Position(pac.getPosition().getX(),pac.getPosition().getY()+1);
        Position temp2 = pac.moveDown();
        assertEquals(temp2, temp);
    }

    @Test
    public void moveLeft() {
        Position temp = new Position(pac.getPosition().getX()-1,pac.getPosition().getY());
        Position temp2 = pac.moveLeft();
        assertEquals(temp2, temp);
    }

    @Test
    public void moveRight() {
        Position temp = new Position(pac.getPosition().getX()+1,pac.getPosition().getY());
        Position temp2 = pac.moveRight();
        assertEquals(temp2, temp);
    }
}