package agh.ics.oop.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class GrassFieldTest {
    @Test
    public void placeTest(){
        GrassField map = new GrassField(5);
        Animal animal_5_6 = new Animal(new Vector2d(5, 6)); // poza mapą 1
        Animal animal_10_minus5 = new Animal(new Vector2d(10, -5)); // poza mapą 2
        Animal animal_420_minus6 = new Animal(new Vector2d(420, -6)); // poza mapą 3
        Animal animal_2_2 = new Animal(new Vector2d(2, 2)); // środek mapy
        Animal secondAnimal_2_2 = new Animal(new Vector2d(2, 2)); // środek mapy x2
        Animal animal_0_0 = new Animal(new Vector2d(0, 0)); // róg mapy

        Assertions.assertDoesNotThrow(() -> {
            map.place(animal_5_6);
            map.place(animal_10_minus5);
            map.place(animal_420_minus6);
            map.place(animal_2_2);
            map.place(animal_0_0);
        });
        Assertions.assertThrowsExactly(IncorrectPositionException.class, () -> {
            map.place(secondAnimal_2_2);
        });

        int animalCounter = 0;
        for(int x = 0; x <= 500; ++x){
            for(int y = -10; y <= 10; ++y){
                var atPos = map.objectAt(new Vector2d(x, y));
                if (atPos != null && atPos.getClass() == Animal.class){
                    ++animalCounter;
                }
            }
        }
        Assertions.assertEquals(5, animalCounter);
        Assertions.assertEquals(animal_2_2, map.objectAt(new Vector2d(2, 2)));
        Assertions.assertEquals(animal_0_0, map.objectAt(new Vector2d(0, 0)));
    }

    @Test
    void occupationTest(){
        GrassField map = new GrassField(5);
        Animal animal_5_6 = new Animal(new Vector2d(5, 6)); // poza mapą 1
        Animal animal_10_minus5 = new Animal(new Vector2d(10, -5)); // poza mapą 2
        Animal animal_1_2 = new Animal(new Vector2d(1, 2));
        Animal animal_2_1 = new Animal(new Vector2d(2, 1));
        Animal animal_4_0 = new Animal(new Vector2d(4, 0)); // róg mapy
        Animal secondAnimal_4_0 = new Animal(new Vector2d(4, 0)); // róg mapy x2

        int grassCount = 0;
        boolean anyOccupiedBeforePlacement = false;
        for(int x = 0; x <= 20 && !anyOccupiedBeforePlacement; ++x){
            for(int y = -10; y <= 20 && !anyOccupiedBeforePlacement; ++y){
                var atPos = map.objectAt(new Vector2d(x, y));
                if(atPos != null) {
                    if (atPos.getClass() == Animal.class) {
                        anyOccupiedBeforePlacement = true;
                    } else if (atPos.getClass() == Grass.class) {
                        ++grassCount;
                    }
                }
            }
        }
        Assertions.assertFalse(anyOccupiedBeforePlacement);
        Assertions.assertEquals(5, grassCount);

        Assertions.assertDoesNotThrow(() -> {
            map.place(animal_5_6);
            map.place(animal_10_minus5);
            map.place(animal_1_2);
            map.place(animal_2_1);
            map.place(animal_4_0);
        });

        Assertions.assertThrowsExactly(IncorrectPositionException.class, () -> {
            map.place(secondAnimal_4_0);
        });

        Assertions.assertTrue(map.isOccupiedByAnimal(animal_5_6.getPosition()));
        Assertions.assertTrue(map.isOccupiedByAnimal(animal_10_minus5.getPosition()));
        Assertions.assertTrue(map.isOccupiedByAnimal(animal_1_2.getPosition()));
        Assertions.assertTrue(map.isOccupiedByAnimal(animal_2_1.getPosition()));
        Assertions.assertTrue(map.isOccupiedByAnimal(animal_4_0.getPosition()));
        Assertions.assertTrue(map.isOccupiedByAnimal(secondAnimal_4_0.getPosition()));
    }

    @Test
    public void moveTest(){
        GrassField map = new GrassField(5);
        Animal animal1 = new Animal(new Vector2d(0, 0));
        Animal animal2 = new Animal(new Vector2d(4, 4));

        Assertions.assertDoesNotThrow(() -> {
            map.place(animal1);
            map.place(animal2);
        });
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
        Assertions.assertFalse(map.isOccupiedByAnimal(map.getCurrentBounds().lowerLeft()));
        Assertions.assertFalse(map.isOccupiedByAnimal(map.getCurrentBounds().upperRight()));
    }
}