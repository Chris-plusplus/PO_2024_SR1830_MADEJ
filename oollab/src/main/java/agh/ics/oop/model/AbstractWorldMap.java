package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {
    protected final Map<Vector2d, Animal> animalMap = new HashMap<>();
    protected final MapVisualizer visualizer = new MapVisualizer(this);
    protected String cachedDrawing = "";
    protected final Set<MapChangeListener> listeners = new HashSet<>();

    protected static final String ON_PLACE_MESSAGE_PREFIX = "Animal placed at";
    protected static final String ON_ORIENTATION_CHANGED_MESSAGE_PREFIX = "Animal rotated";
    protected static final String ON_POSITION_CHANGED_MESSAGE_PREFIX = "Animal moved";

    protected final UUID uuid = UUID.randomUUID();

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

    protected void onPlace(Vector2d at){
        cachedDrawing = "";
        onMapChanged("%s %s".formatted(ON_PLACE_MESSAGE_PREFIX, at.toString()));
    }

    protected void onPositionChanged(Vector2d oldPos, Vector2d newPos){
        cachedDrawing = "";
        onMapChanged("%s: %s -> %s".formatted(ON_POSITION_CHANGED_MESSAGE_PREFIX, oldPos.toString(), newPos.toString()));
    }

    protected void onOrientationChanged(MapDirection oldDir, MapDirection newDir){
        cachedDrawing = "";
        onMapChanged("%s: [%s] -> [%s]".formatted(ON_ORIENTATION_CHANGED_MESSAGE_PREFIX, oldDir.toShortString(), newDir.toShortString()));
    }

    @Override
    public void place(Animal animal) throws IncorrectPositionException {
        if(canMoveTo(animal.getPosition())){
            animalMap.put(animal.getPosition(), animal);
            onPlace(animal.getPosition());
        }
        else{
            throw new IncorrectPositionException(animal.getPosition());
        }
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if(objectAt(animal.getPosition()) == animal){
            switch (direction){
                // brak zmiany pozycji
                case LEFT, RIGHT -> {
                    var oldDir = animal.getOrientation();
                    animal.move(direction, this);

                    onOrientationChanged(oldDir, animal.getOrientation());
                }

                // potencjalna zmiana pozycji
                case FORWARD, BACKWARD -> {
                    var oldPos = animal.getPosition();
                    animalMap.remove(animal.getPosition());
                    animal.move(direction, this);
                    animalMap.put(animal.getPosition(), animal);

                    onPositionChanged(oldPos, animal.getPosition());
                }
            }
        }
    }

    @Override
    public String toString() {
        if(cachedDrawing.isEmpty()){
            var bounds = getCurrentBounds();
            cachedDrawing = visualizer.draw(bounds.lowerLeft(), bounds.upperRight());
        }
        return cachedDrawing;
    }

    @Override
    public List<WorldElement> getElements() {
        return new ArrayList<>(animalMap.values());
    }

    @Override
    public void addListener(MapChangeListener listener){
        listeners.add(listener);
    }

    @Override
    public void removeListener(MapChangeListener listener){
        listeners.remove(listener);
    }

    protected void onMapChanged(String message){
        for(var listener : listeners){
            listener.mapChanged(this, message);
        }
    }

    @Override
    public UUID getId() {
        return uuid;
    }
}
