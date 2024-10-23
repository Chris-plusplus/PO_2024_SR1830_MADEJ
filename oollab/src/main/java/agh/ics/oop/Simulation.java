package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    List<Animal> animals = new ArrayList<>();
    List<MoveDirection> moves;

    public Simulation(List<Vector2d> initialPositions, List<MoveDirection> moves){
        // List<T> można modyfikować, niezbędna lokalna kopia
        this.moves = new ArrayList<>(moves);
        for(var initialPosition : initialPositions){
            animals.add(new Animal(initialPosition));
        }
    }

    public void run(){
        for(int i = 0; i != moves.size(); ++i){
            var animal = animals.get(i % animals.size());
            animal.move(moves.get(i));
            System.out.println("Zwierzę %d: %s".formatted(i % animals.size(), animal));
        }
    }
}
