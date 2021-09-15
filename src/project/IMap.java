package project;

public interface IMap {

    boolean canMoveTo(Vector2d position);

    void place(MapElement object);

    void remove(Vector2d position);

    void reset();

    void resetMainObjects();
}
