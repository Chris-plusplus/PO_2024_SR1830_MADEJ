package agh.ics.oop.model;

public enum MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST;

    private static final String NORTH_STR = "Północ";
    private static final String EAST_STR = "Wschód";
    private static final String SOUTH_STR = "Południe";
    private static final String WEST_STR = "Zachód";

    private static final String NORTH_SHORT_STR = "^";
    private static final String EAST_SHORT_STR = ">";
    private static final String SOUTH_SHORT_STR = "v";
    private static final String WEST_SHORT_STR = "<";

    private static final Vector2d NORTH_VEC = new Vector2d(0, 1);
    private static final Vector2d EAST_VEC = new Vector2d(1, 0);
    private static final Vector2d SOUTH_VEC = new Vector2d(0, -1);
    private static final Vector2d WEST_VEC = new Vector2d(-1, 0);
    @Override
    public String toString() {
        return switch (this){
            case NORTH -> NORTH_STR;
            case EAST -> EAST_STR;
            case SOUTH -> SOUTH_STR;
            case WEST -> WEST_STR;
        };
    }

    public String toShortString() {
        return switch (this){
            case NORTH -> NORTH_SHORT_STR;
            case EAST -> EAST_SHORT_STR;
            case SOUTH -> SOUTH_SHORT_STR;
            case WEST -> WEST_SHORT_STR;
        };
    }

    public MapDirection next(){
        return switch (this){
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
        };
    }
    public MapDirection previous(){
        return switch (this){
            case EAST -> NORTH;
            case SOUTH -> EAST;
            case WEST -> SOUTH;
            case NORTH -> WEST;
        };
    }
    public Vector2d toUnitVector() {
        return switch (this) {
            case NORTH -> NORTH_VEC;
            case EAST -> EAST_VEC;
            case SOUTH -> SOUTH_VEC;
            case WEST -> WEST_VEC;
        };
    }
}
