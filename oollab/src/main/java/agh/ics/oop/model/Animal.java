package agh.ics.oop.model;

import java.util.Objects;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position;

    public static final Vector2d BOUND_LEFT_DOWN = new Vector2d(0, 0);
    public static final Vector2d BOUND_RIGHT_UP = new Vector2d(4, 4);

    public Animal(){
        this(new Vector2d(2, 2));
    }
    public Animal(Vector2d position){
        this.position = position;
    }

    public void move(MoveDirection direction, MoveValidator validator){
        switch (direction){
            case LEFT -> this.orientation = this.orientation.previous();
            case RIGHT -> this.orientation = this.orientation.next();
            case FORWARD -> this.trySetPosition(this.position.add(this.orientation.toUnitVector()), validator);
            case BACKWARD -> this.trySetPosition(this.position.add(this.orientation.toUnitVector().opposite()), validator);
        }
    }

    private void trySetPosition(Vector2d newPosition, MoveValidator validator){
        if (validator.canMoveTo(newPosition)){
            this.position = newPosition;
        }
    }

    @Override
    public String toString() {
        return orientation.toShortString();
    }
    public String toLongString() {
        return "%s %s".formatted(position, orientation.toShortString());
    }

    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    boolean isAt(Vector2d position){
        return Objects.equals(this.position, position);
    }
}
