package agh.ics.oop.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Vector;

public class Vector2dTest {
    @Test
    public void equalsTest(){
        var _1 = new Vector2d(2, 4);
        var _2 = new Vector2d(1 + 1, 6 - 2);
        Vector2d _3 = null;
        var _4 = new Vector<Integer>();

        Assertions.assertEquals(_1, _2);
        Assertions.assertNotEquals(_1, _3);
        Assertions.assertNotEquals(_1, _4);
        Assertions.assertNotEquals(_2, _3);
        Assertions.assertNotEquals(_2, _4);
    }
    @Test
    public void toStringTest(){
        var _1 = new Vector2d(420, 1337);

        Assertions.assertEquals(_1.toString(), "(420, 1337)");
    }
    @Test
    public void precedesTest(){
        var _1 = new Vector2d(1, 1);
        var _2 = new Vector2d(2, 4);
        var _3 = new Vector2d(-1, 3);

        Assertions.assertTrue(_1.precedes(_2));
        Assertions.assertFalse(_1.precedes(_3));
        Assertions.assertTrue(_3.precedes(_2));
    }
    @Test
    public void followsTest(){
        var _1 = new Vector2d(1, 1);
        var _2 = new Vector2d(2, 4);
        var _3 = new Vector2d(-1, 3);

        Assertions.assertTrue(_2.follows(_1));
        Assertions.assertFalse(_3.follows(_1));
        Assertions.assertTrue(_2.follows(_3));
    }

    @Test
    public void upperRightTest(){
        var _1 = new Vector2d(2, 1);
        var _2 = new Vector2d(1, 2);

        var expected = new Vector2d(2, 2);

        Assertions.assertEquals(_1.upperRight(_2), expected);
        Assertions.assertEquals(_2.upperRight(_1), expected);
    }
    @Test
    public void lowerLeftTest(){
        var _1 = new Vector2d(2, 1);
        var _2 = new Vector2d(1, 2);

        var expected = new Vector2d(1, 1);

        Assertions.assertEquals(_1.lowerLeft(_2), expected);
        Assertions.assertEquals(_2.lowerLeft(_1), expected);
    }
    @Test
    public void addTest(){
        var _1 = new Vector2d(2, 1);
        var _2 = new Vector2d(1, 2);

        var expected = new Vector2d(3, 3);

        Assertions.assertEquals(_1.add(_2), expected);
    }
    @Test
    public void subtractTest(){
        var _1 = new Vector2d(2, 1);
        var _2 = new Vector2d(1, 2);

        var expected = new Vector2d(1, -1);

        Assertions.assertEquals(_1.subtract(_2), expected);
    }
    @Test
    public void oppositeTest(){
        var _1 = new Vector2d(2, -1);

        var expected = new Vector2d(-2, 1);

        Assertions.assertEquals(_1.opposite(), expected);
    }
}