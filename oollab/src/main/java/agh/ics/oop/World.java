package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;

public class World {
    private static void run(List<MoveDirection> directions){
        System.out.println("Start");
        for(var arg : directions){
            switch (arg){
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
                case RIGHT -> System.out.println("Zwierzak skręca w prawo");
                case LEFT -> System.out.println("Zwierzak skręca w lewo");
            }
        }
        System.out.println("Stop");
    }
    public static void main(String[] args){
//        var animal = new Animal();
//        System.out.println(animal);
//        System.out.println();

        WorldMap worldMap = new GrassField(5);

        List<MoveDirection> directions;
        try{
             directions = OptionsParser.parse(args);
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return;
        }
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        Simulation simulation = new Simulation(positions, directions, worldMap);
        simulation.run();

//        Vector2d position1 = new Vector2d(1,2);
//        System.out.println(position1);
//        Vector2d position2 = new Vector2d(-2,1);
//        System.out.println(position2);
//        System.out.println(position1.add(position2));
//
//        System.out.println(MapDirection.NORTH.toUnitVector());
//        System.out.println(MapDirection.EAST.toUnitVector());
//        System.out.println(MapDirection.SOUTH.toUnitVector());
//        System.out.println(MapDirection.WEST.toUnitVector());
//
//        run(OptionsParser.parse(args));
    }
}

