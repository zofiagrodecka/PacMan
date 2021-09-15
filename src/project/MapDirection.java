package project;

public enum MapDirection {

    NORTH,
    EAST,
    SOUTH,
    WEST;

    public Vector2d toUnitVector(){
        return switch (this) {
            case NORTH -> new Vector2d(0, -1);
            case SOUTH -> new Vector2d(0, 1);
            case WEST -> new Vector2d(-1, 0);
            default -> new Vector2d(1, 0);
        };
    }

    public String toString() {
        switch (this) {
            case NORTH:
                return "Północ";
            case SOUTH:
                return "Południe";
            case WEST:
                return "Zachód";
            case EAST:
                return "Wschód";
            default:
                return "Nieprawidłowa wartość";
        }
    }
}
