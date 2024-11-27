package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.util.ConsoleMapDisplay;

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
            try {
                worldMap.place(animals.getLast());
            }
            catch (IncorrectPositionException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void run(){
        ConsoleMapDisplay consoleMapDisplay = new ConsoleMapDisplay();
        worldMap.addListener(consoleMapDisplay);

        // initial state
        System.out.println(worldMap);
        for(int i = 0; i != moves.size(); ++i){
            var animal = animals.get(i % animals.size());
            worldMap.move(animal, moves.get(i));;
        }

        worldMap.removeListener(consoleMapDisplay);
    }
}
