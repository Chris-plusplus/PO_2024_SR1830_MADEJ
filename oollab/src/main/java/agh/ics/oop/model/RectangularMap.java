package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap {
    private final Vector2d bounds;
    private static final Vector2d LOWER_BOUNDS = new Vector2d(0, 0);

    public RectangularMap(int width, int height){
        bounds = new Vector2d(width - 1, height - 1);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return LOWER_BOUNDS.precedes(position) && bounds.follows(position) && super.canMoveTo(position);
    }

    public int getWidth() {
        return bounds.getX() + 1;
    }
    public int getHeight() {
        return bounds.getY() + 1;
    }

    public Vector2d getUpperDrawBound(){
        return bounds;
    }
    public Vector2d getLowerDrawBound(){
        return LOWER_BOUNDS;
    }
}
