package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;
import agh.ics.oop.model.util.RandomPositionGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrassField extends AbstractWorldMap {

    private Map<Vector2d, Grass> grassMap = new HashMap<>();
    private Vector2d lowerBound = new Vector2d(0, 0);
    private Vector2d upperBound = new Vector2d(0, 0);
    private boolean updateBounds = true;

    public GrassField(int n){
        int boundValue = (int)Math.ceil(Math.sqrt(10.0 * (double)n));

        for(var pos : new RandomPositionGenerator(boundValue, boundValue, n)){
            grassMap.put(pos, new Grass(pos));
        }
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        var animal = animalMap.get(position);
        return animal != null ? animal : grassMap.get(position);
    }

    private boolean resizesBounds(Vector2d position){
        return !(lowerBound.precedes(position) && upperBound.follows(position));
    }

    private void tryUpdateBounds(){
        if (updateBounds) {
            lowerBound = null;
            upperBound = null;

            for (Animal animal : animalMap.values()) {
                if (lowerBound == null) { // implikuje upperRightCorner równe null
                    lowerBound = animal.getPosition();
                    upperBound = animal.getPosition();
                } else {
                    lowerBound = lowerBound.lowerLeft(animal.getPosition());
                    upperBound = upperBound.upperRight(animal.getPosition());
                }
            }
            for (Grass grass : grassMap.values()) {
                if (lowerBound == null) { // implikuje upperRightCorner równe null
                    lowerBound = grass.getPosition();
                    upperBound = grass.getPosition();
                } else {
                    lowerBound = lowerBound.lowerLeft(grass.getPosition());
                    upperBound = upperBound.upperRight(grass.getPosition());
                }
            }

            if (lowerBound == null) { // implikuje upperRightCorner równe null
                lowerBound = new Vector2d(0, 0);
                upperBound = new Vector2d(0, 0);
            }

            updateBounds = false;
        }
    }

    @Override
    public Vector2d getLowerDrawBound() {
        if(updateBounds){
            tryUpdateBounds();
        }
        return lowerBound;
    }

    @Override
    public Vector2d getUpperDrawBound() {
        if(updateBounds){
            tryUpdateBounds();
        }
        return upperBound;
    }

    @Override
    protected void onPlace() {
        super.onPlace();
        updateBounds = true;
    }

    @Override
    protected void onPositionChanged() {
        super.onPositionChanged();
        updateBounds = true;
    }

    @Override
    public List<WorldElement> getElements() {
        var elems = super.getElements();
        elems.addAll(grassMap.values());
        return elems;
    }
}
