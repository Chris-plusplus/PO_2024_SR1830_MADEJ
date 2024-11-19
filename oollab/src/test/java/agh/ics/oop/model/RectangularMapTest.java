package agh.ics.oop.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangularMapTest {
    @Test
    public void placeTest(){
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal_5_6 = new Animal(new Vector2d(5, 6)); // poza mapą 1
        Animal animal_10_minus5 = new Animal(new Vector2d(10, -5)); // poza mapą 2
        Animal animal_420_minus6 = new Animal(new Vector2d(420, -6)); // poza mapą 3
        Animal animal_2_2 = new Animal(new Vector2d(2, 2)); // środek mapy
        Animal secondAnimal_2_2 = new Animal(new Vector2d(2, 2)); // środek mapy x2
        Animal animal_0_0 = new Animal(new Vector2d(0, 0)); // róg mapy

        Assertions.assertFalse(map.place(animal_5_6));
        Assertions.assertFalse(map.place(animal_10_minus5));
        Assertions.assertFalse(map.place(animal_420_minus6));
        Assertions.assertTrue(map.place(animal_2_2));
        Assertions.assertFalse(map.place(secondAnimal_2_2));
        Assertions.assertTrue(map.place(animal_0_0));

        int animalCounter = 0;
        for(int x = map.getLowerDrawBound().getX(); x <= map.getUpperDrawBound().getX(); ++x){
            for(int y = map.getLowerDrawBound().getY(); y <= map.getUpperDrawBound().getY(); ++y){
                if (map.objectAt(new Vector2d(x, y)) != null){
                    ++animalCounter;
                }
            }
        }
        Assertions.assertEquals(2, animalCounter);
        Assertions.assertEquals(animal_2_2, map.objectAt(new Vector2d(2, 2)));
        Assertions.assertEquals(animal_0_0, map.objectAt(new Vector2d(0, 0)));
    }

    @Test
    void occupationTest(){
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal_5_6 = new Animal(new Vector2d(5, 6)); // poza mapą 1
        Animal animal_10_minus5 = new Animal(new Vector2d(10, -5)); // poza mapą 2
        Animal animal_1_2 = new Animal(new Vector2d(1, 2));
        Animal animal_2_1 = new Animal(new Vector2d(2, 1));
        Animal animal_4_0 = new Animal(new Vector2d(4, 0)); // róg mapy
        Animal secondAnimal_4_0 = new Animal(new Vector2d(4, 0)); // róg mapy x2

        boolean anyOccupiedBeforePlacement = false;
        for(int x = map.getLowerDrawBound().getX(); x <= map.getUpperDrawBound().getX() && !anyOccupiedBeforePlacement; ++x){
            for(int y = map.getLowerDrawBound().getY(); y <= map.getUpperDrawBound().getY() && !anyOccupiedBeforePlacement; ++y){
                if (map.isOccupied(new Vector2d(x, y))){
                    anyOccupiedBeforePlacement = true;
                }
            }
        }
        Assertions.assertFalse(anyOccupiedBeforePlacement);

        Assertions.assertFalse(map.place(animal_5_6));
        Assertions.assertFalse(map.place(animal_10_minus5));
        Assertions.assertTrue(map.place(animal_1_2));
        Assertions.assertTrue(map.place(animal_2_1));
        Assertions.assertTrue(map.place(animal_4_0));
        Assertions.assertFalse(map.place(secondAnimal_4_0));

        Assertions.assertFalse(map.isOccupied(animal_5_6.getPosition()));
        Assertions.assertFalse(map.isOccupied(animal_10_minus5.getPosition()));
        Assertions.assertTrue(map.isOccupied(animal_1_2.getPosition()));
        Assertions.assertTrue(map.isOccupied(animal_2_1.getPosition()));
        Assertions.assertTrue(map.isOccupied(animal_4_0.getPosition()));
        Assertions.assertTrue(map.isOccupied(secondAnimal_4_0.getPosition()));
    }

    @Test
    public void moveTest(){
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(map.getLowerDrawBound());
        Animal animal2 = new Animal(map.getUpperDrawBound());

        map.place(animal1);
        map.place(animal2);
        map.move(animal2, MoveDirection.LEFT); // (4, 4) >
        map.move(animal2, MoveDirection.LEFT); // (4, 4) v

        while (animal1.getPosition().getY() != animal2.getPosition().getY()){
            map.move(animal1, MoveDirection.FORWARD); // (0, 1); (0, 2)
            map.move(animal2, MoveDirection.FORWARD); // (4, 3); (4, 2)
        }
        map.move(animal1, MoveDirection.RIGHT); // (0, 2) >
        map.move(animal2, MoveDirection.RIGHT); // (4, 2) <
        while (map.objectAt(animal1.getPosition().add(MapDirection.EAST.toUnitVector())) != animal2){
            map.move(animal1, MoveDirection.FORWARD); // (1, 2); (2, 2)
            map.move(animal2, MoveDirection.FORWARD); // (3, 2); (3, 2)
        }
        Assertions.assertEquals(new Vector2d(2, 2), animal1.getPosition());
        Assertions.assertEquals(new Vector2d(3, 2), animal2.getPosition());
        Assertions.assertFalse(map.isOccupied(map.getLowerDrawBound()));
        Assertions.assertFalse(map.isOccupied(map.getUpperDrawBound()));
    }
}