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

    public void move(MoveDirection direction){
        switch (direction){
            case LEFT -> this.orientation = this.orientation.previous();
            case RIGHT -> this.orientation = this.orientation.next();
            case FORWARD -> this.trySetPosition(this.position.add(this.orientation.toUnitVector()));
            case BACKWARD -> this.trySetPosition(this.position.add(this.orientation.toUnitVector().opposite()));
        }
    }

    private void trySetPosition(Vector2d newPosition){
        if (BOUND_LEFT_DOWN.precedes(newPosition) && BOUND_RIGHT_UP.follows(newPosition)){
            this.position = newPosition;
        }
    }

    @Override
    public String toString() {
        return "%s %s".formatted(position.toString(), orientation.toString());
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
