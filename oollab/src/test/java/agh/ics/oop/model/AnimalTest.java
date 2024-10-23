package agh.ics.oop.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnimalTest {
    @Test
    public void upperBoundaryTest(){
        Animal animal = new Animal(new Vector2d(0, 4));

        animal.move(MoveDirection.FORWARD); // 0, 4 ^

        Assertions.assertEquals(animal.getPosition().getY(), Animal.BOUND_RIGHT_UP.getY());
    }
    @Test
    public void lowerBoundaryTest(){
        Animal animal = new Animal(new Vector2d(0, 0));

        animal.move(MoveDirection.RIGHT); // >
        animal.move(MoveDirection.RIGHT); // v
        animal.move(MoveDirection.FORWARD); // 0, 0 v

        Assertions.assertEquals(animal.getPosition().getY(), Animal.BOUND_LEFT_DOWN.getY());
    }
    @Test
    public void leftBoundaryTest(){
        Animal animal = new Animal(new Vector2d(0, 0));

        animal.move(MoveDirection.LEFT); // <
        animal.move(MoveDirection.FORWARD); // 0, 0 <

        Assertions.assertEquals(animal.getPosition().getX(), Animal.BOUND_LEFT_DOWN.getX());
    }
    @Test
    public void rightBoundaryTest(){
        Animal animal = new Animal(new Vector2d(4, 4));

        animal.move(MoveDirection.RIGHT); // >
        animal.move(MoveDirection.FORWARD); // 4, 4 >

        Assertions.assertEquals(animal.getPosition().getX(), Animal.BOUND_RIGHT_UP.getX());
    }

    @Test
    public void positionOrientationTest(){
        Animal animal = new Animal(new Vector2d(0, 0));

        animal.move(MoveDirection.RIGHT);   // 0, 0; >
        animal.move(MoveDirection.FORWARD); // 1, 0; >
        animal.move(MoveDirection.RIGHT);   // 1, 0; v
        animal.move(MoveDirection.LEFT);    // 1, 0; >
        animal.move(MoveDirection.FORWARD); // 2, 0; >
        animal.move(MoveDirection.BACKWARD);// 1, 0; >
        animal.move(MoveDirection.BACKWARD);// 0, 0; >
        animal.move(MoveDirection.BACKWARD);// 0, 0; >
        animal.move(MoveDirection.LEFT);    // 0, 0; ^
        animal.move(MoveDirection.LEFT);    // 0, 0; <
        animal.move(MoveDirection.LEFT);    // 0, 0; v
        animal.move(MoveDirection.FORWARD); // 0, 0; v
        animal.move(MoveDirection.RIGHT);   // 0, 0; >
        animal.move(MoveDirection.FORWARD); // 0, 0; >
        animal.move(MoveDirection.LEFT);    // 0, 0; v

        Assertions.assertEquals(animal.getOrientation(), MapDirection.SOUTH);
        Assertions.assertEquals(animal.getPosition(), new Vector2d(0, 0));

        // animal znosi jajo

        for(int i = 0; i != 5; ++i){
            animal.move(MoveDirection.BACKWARD);
        }
        Assertions.assertEquals(animal.getPosition(), new Vector2d(0, 4));
        for(int i = 0; i != 5; ++i){
            animal.move(MoveDirection.RIGHT);
        }
        Assertions.assertEquals(animal.getOrientation(), MapDirection.WEST);
        for(int i = 0; i != 5; ++i){
            animal.move(MoveDirection.BACKWARD);
        }
        Assertions.assertEquals(animal.getPosition(), new Vector2d(4, 4));
        // Assertions.assertTrue(animal.canMoonwalk());

        // piskle wykluwa się
        Animal chick = new Animal(new Vector2d(0, 0));
        chick.move(MoveDirection.RIGHT);

        MoveDirection[] GPSData = {
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.LEFT,
                MoveDirection.FORWARD,
                MoveDirection.LEFT,
                MoveDirection.FORWARD,
                MoveDirection.RIGHT,
                MoveDirection.FORWARD
        };

        // dwa zwierzaki chcą się spotkać
        for(MoveDirection move : GPSData){
            animal.move(move);
            chick.move(move);
        }

        // zwierzaki powinny być na tej samej pozycji
        // i powinny się patrzeć na siebie
        Assertions.assertEquals(animal.getPosition(), chick.getPosition());
        Assertions.assertEquals(animal.getOrientation().next(), chick.getOrientation().previous());
    }
}