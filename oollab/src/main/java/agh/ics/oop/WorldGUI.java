package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import javafx.application.Application;

import java.util.List;

public class WorldGUI {
    public static void main(String[] args){
//        var animal = new Animal();
//        System.out.println(animal);
//        System.out.println();

        Application.launch(SimulationApp.class, args);

//        List<MoveDirection> directions;
//        try{
//             directions = OptionsParser.parse(args);
//        }
//        catch (IllegalArgumentException e){
//            System.out.println(e.getMessage());
//            return;
//        }
//        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
//
//        List<Simulation> simulations = new ArrayList<>();
//        Random rng = new Random();
//        for(int i = 0; i != 10000; ++i){
//            simulations.add(new Simulation(positions, directions, switch (rng.nextInt(2)){
//                case 0 -> new GrassField(5);
//                case 1 -> new RectangularMap(5, 5);
//                default -> throw new IllegalStateException("impossible xd");
//            }));
//        }
//
//        SimulationEngine simEngine = new SimulationEngine(simulations);
//        simEngine.runAsyncInThreadPool();
//        try {
//            simEngine.awaitSimulationsEnd();
//        } catch (InterruptedException exception) {
//            System.out.println(exception);
//        }
//
//        System.out.println("System zakończył działanie");


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

