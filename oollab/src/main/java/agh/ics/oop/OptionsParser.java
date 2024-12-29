package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OptionsParser {
    private static final String ERROR_MESSAGE = "is an illegal move";

    public static List<MoveDirection> parse(String[] args){
        List<MoveDirection> result = new ArrayList<>();
        for(var arg : args){
            switch (arg){
                case "f" -> result.add(MoveDirection.FORWARD);
                case "b" -> result.add(MoveDirection.BACKWARD);
                case "r" -> result.add(MoveDirection.RIGHT);
                case "l" -> result.add(MoveDirection.LEFT);
                default  -> throw new IllegalArgumentException("%s %s".formatted(arg, ERROR_MESSAGE));
            }
        }

        return result;
    }
}
