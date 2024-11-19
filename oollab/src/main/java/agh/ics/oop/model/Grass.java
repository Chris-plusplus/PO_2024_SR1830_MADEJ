package agh.ics.oop.model;

import java.util.Objects;

public class Grass implements WorldElement {

    public static final String MAP_REPRESENTATION = "*";

    private final Vector2d position;

    public Grass(Vector2d position){
        this.position = position;
    }

    @Override
    public String toString() {
        return MAP_REPRESENTATION;
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

    @Override
    public boolean isAt(Vector2d pos) {
        return Objects.equals(position, pos);
    }
}
