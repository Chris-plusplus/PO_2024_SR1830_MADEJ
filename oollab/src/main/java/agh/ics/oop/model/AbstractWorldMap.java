package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap {
    protected final Map<Vector2d, Animal> animalMap = new HashMap<>();
    protected final MapVisualizer visualizer = new MapVisualizer(this);
    protected String cachedDrawing = "";

    @Override
    public WorldElement objectAt(Vector2d position){
        return animalMap.get(position);
    }

    @Override
    public boolean isOccupied(Vector2d position){
        return objectAt(position) != null;
    }

    @Override
    public boolean isOccupiedByAnimal(Vector2d position) {
        return animalMap.containsKey(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        return !isOccupiedByAnimal(position);
    }

    protected void onPlace(){
        cachedDrawing = "";
    }

    protected void onPositionChanged(){
        cachedDrawing = "";
    }

    protected void onOrientationChanged(){
        cachedDrawing = "";
    }

    public abstract Vector2d getLowerDrawBound();
    public abstract Vector2d getUpperDrawBound();

    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition())){
            animalMap.put(animal.getPosition(), animal);
            onPlace();
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if(objectAt(animal.getPosition()) == animal){
            switch (direction){
                // brak zmiany pozycji
                case LEFT, RIGHT -> {
                    animal.move(direction, this);

                    onOrientationChanged();
                }

                // potencjalna zmiana pozycji
                case FORWARD, BACKWARD -> {
                    animalMap.remove(animal.getPosition());
                    animal.move(direction, this);
                    animalMap.put(animal.getPosition(), animal);

                    onPositionChanged();
                }
            }
        }
    }

    @Override
    public String toString() {
        if(cachedDrawing.isEmpty()){
            cachedDrawing = visualizer.draw(getLowerDrawBound(), getUpperDrawBound());
        }
        return cachedDrawing;
    }

    @Override
    public List<WorldElement> getElements() {
        return new ArrayList<>(animalMap.values());
    }
}
