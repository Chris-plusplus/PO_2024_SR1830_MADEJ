package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap {
    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final Vector2d bounds;
    private static final Vector2d LOWER_BOUNDS = new Vector2d(0, 0);
    private final MapVisualizer visualizer = new MapVisualizer(this);

    public RectangularMap(int width, int height){
        bounds = new Vector2d(width - 1, height - 1);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return LOWER_BOUNDS.precedes(position) && bounds.follows(position) && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if(objectAt(animal.getPosition()) == animal){
            switch (direction){
                // brak zmiany pozycji
                case LEFT, RIGHT -> animal.move(direction, this);

                // potencjalna zmiana pozycji
                case FORWARD, BACKWARD -> {
                    animals.remove(animal.getPosition());
                    animal.move(direction, this);
                    animals.put(animal.getPosition(), animal);
                }
            }
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }

    public int getWidth() {
        return bounds.getX() + 1;
    }
    public int getHeight() {
        return bounds.getY() + 1;
    }

    @Override
    public String toString() {
        return visualizer.draw(LOWER_BOUNDS, bounds);
    }

    public Vector2d getUpperRightCorner(){
        return bounds;
    }
    public Vector2d getLowerLeftCorner(){
        return LOWER_BOUNDS;
    }
}
