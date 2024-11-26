package agh.ics.oop.model;

public class IncorrectPositionException extends Exception {
    private static final String ERROR_MESSAGE_PREFIX = "Position";
    private static final String ERROR_MESSAGE_SUFFIX = "is not valid";

    public IncorrectPositionException(Vector2d vec){
        super("%s %s %s".formatted(ERROR_MESSAGE_PREFIX, vec.toString(), ERROR_MESSAGE_SUFFIX));
    }
}
