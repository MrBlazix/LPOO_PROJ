package Model;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArenaTest {

    Arena arena;

    @Before
    public void init() {
        Pac pac = new Pac(19, 3);
        this.arena = new Arena(39, 25, pac);
    }


    @Test
    public void increaseLevel() {
        int temp_level = arena.getLevel();
        arena.increaseLevel();
        Assert.assertEquals(arena.getLevel(), temp_level+1);
    }

    @Test
    public void resetGhosts() {
      //  arena.createWalls();
        Position temp = arena.getGhosts().get(0).getPosition();
        arena.moveGhost(arena.getGhosts().get(0),arena.getGhosts().get(0).moveUp());
        Position temp2 = arena.getGhosts().get(0).getPosition();
        Assert.assertNotEquals(temp,temp2);
        arena.resetGhosts();
        Position temp3 = arena.getGhosts().get(0).getPosition();
        Assert.assertEquals(temp3,temp);
    }


    @Test
    public void isSuperPoint() {
        boolean check = arena.isSuperPoint(new Position(8,1));
        Assert.assertEquals(check, true);
    }

    @Test
    public void movePac() {
        Position temp = arena.getPac().getPosition();
        arena.movePac(arena.getPac().moveRight());
        Position temp2 = arena.getPac().getPosition();
        Assert.assertEquals(temp,temp2);
    }

    @Test
    public void moveGhost() {
        Position temp = arena.getGhosts().get(0).getPosition();
        arena.moveGhost(arena.getGhosts().get(0),arena.getGhosts().get(0).moveUp());
        Position temp2 = arena.getGhosts().get(0).getPosition();
        Assert.assertNotEquals(temp,temp2);
    }

    @Test
    public void detectDeath() {
        arena.getPac().setPosition(new Position(1,1));
        arena.getGhosts().get(0).setPosition(new Position(1,1));
        boolean temp = arena.detectDeath();
        Assert.assertEquals(temp,true);
    }

    @Test
    public void checkMove() {
    }

    @Test
    public void testPath() {
    }

    @Test
    public void checkIfGhost() {
    }

    @Test
    public void someoneScored() {
    }
}