package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Animal> animals = new ArrayList<>();
    private final List<MoveDirection> moves;
    private final WorldMap worldMap;

    private static final String ANIMAL_LABEL = "Zwierzę";

    public Simulation(List<Vector2d> initialPositions, List<MoveDirection> moves, WorldMap worldMap){
        this.worldMap = worldMap;

        // List<T> można modyfikować, niezbędna lokalna kopia
        this.moves = new ArrayList<>(moves);
        for(var initialPosition : initialPositions){
            animals.add(new Animal(initialPosition));
            worldMap.place(animals.getLast());
        }
        System.out.println(worldMap.isOccupied(new Vector2d(2, 2)));
        System.out.println(worldMap.isOccupied(new Vector2d(3, 4)));
    }

    public void run(){
        System.out.println(worldMap);
        for(int i = 0; i != moves.size(); ++i){
            var animal = animals.get(i % animals.size());
            String animalStrBefore = animal.toLongString();
            worldMap.move(animal, moves.get(i));
            System.out.println("%s %d: {%s} -> {%s}".formatted(ANIMAL_LABEL, i % animals.size(), animalStrBefore, animal.toLongString()));
            System.out.println(worldMap);
        }
    }
}
