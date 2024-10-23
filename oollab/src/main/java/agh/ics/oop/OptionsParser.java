package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.Arrays;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args){
        MoveDirection[] result = new MoveDirection[args.length];
        int counter = 0;
        for(var arg : args){
            switch (arg){
                case "f" -> result[counter++] = MoveDirection.FORWARD;
                case "b" -> result[counter++] = MoveDirection.BACKWARD;
                case "r" -> result[counter++] = MoveDirection.RIGHT;
                case "l" -> result[counter++] = MoveDirection.LEFT;
            }
        }
        return Arrays.copyOfRange(result, 0, counter);
    }
}
