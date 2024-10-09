package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.Arrays;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args){
        MoveDirection[] result = new MoveDirection[args.length];
        int counter = 0;
        for(int i = 0; i != args.length; i++){
            var arg = args[i];
            switch (arg){
                case "f": result[counter++] = MoveDirection.FORWARD; break;
                case "b": result[counter++] = MoveDirection.BACKWARD; break;
                case "r": result[counter++] = MoveDirection.RIGHT; break;
                case "l": result[counter++] = MoveDirection.LEFT; break;
            }
        }
        return Arrays.copyOfRange(result, 0, counter);
    }
}
