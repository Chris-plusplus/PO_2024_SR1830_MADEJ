package agh.ics.oop.model.util;

import agh.ics.oop.model.MapChangeListener;
import agh.ics.oop.model.WorldMap;

public class ConsoleMapDisplay implements MapChangeListener {
    private int updates = 0;
    private static final String UPDATES_PREFIX = "Update count";

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        System.out.println(message);
        System.out.println("%s = %d".formatted(UPDATES_PREFIX, ++updates));
        System.out.println(worldMap.toString());
    }
}
