package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OptionsParserTest {
    @Test
    public void parseTestValid() {
        String[] args = {"f", "b", "l", "r", "f", "b", "b", "b", "r", "r", "l", "f"};
        var results = OptionsParser.parse(args);
        MoveDirection[] expected = {
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.LEFT,
                MoveDirection.RIGHT,
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.BACKWARD,
                MoveDirection.BACKWARD,
                MoveDirection.RIGHT,
                MoveDirection.RIGHT,
                MoveDirection.LEFT,
                MoveDirection.FORWARD
        };

        Assertions.assertEquals(results.size(), expected.length);
        for(int i = 0; i != results.size(); ++i){
            Assertions.assertEquals(results.get(i), expected[i]);
        }
    }

    @Test
    public void parseTestInvalid() {
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> {
            String[] args = {"f", "b", "l", "r", "forward", "up", "BACKWARD", "b", "r", "r", "l", "f"};
            var results = OptionsParser.parse(args);
            MoveDirection[] expected = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD};

            Assertions.assertEquals(results.size(), expected.length);
            for(int i = 0; i != results.size(); ++i){
                Assertions.assertEquals(results.get(i), expected[i]);
            }
        });
    }
}