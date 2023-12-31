package Application;

public enum Direction {
    UP,
    RIGHT,
    DOWN,
    LEFT;
    public static Direction getNext(Direction curr) {
        return switch (curr) {
            case UP -> RIGHT;
            case RIGHT -> DOWN;
            case DOWN -> LEFT;
            case LEFT -> UP;
        };
    }
}
