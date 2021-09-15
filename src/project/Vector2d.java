package project;

import java.util.ArrayList;
import java.util.Objects;

public class Vector2d {

    public final int x;
    public final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString(){

        return "(" + this.x + "," + this.y + ")";
    }

    @Override
    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;

        return that.x == this.x && that.y == this.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    public boolean precedes(Vector2d other){
        return this.x <= other.x && this.y <= other.y;
    }

    public boolean follows(Vector2d other){
        return this.x >= other.x && this.y >= other.y;
    }

    public Vector2d add(Vector2d other){
        return new Vector2d(x + other.x, y + other.y);
    }

    public ArrayList<Vector2d> neighbours(Vector2d upperRight){

        ArrayList<Vector2d> result = new ArrayList<>();

        for(MapDirection direction : MapDirection.values()) {

            if(this.add(direction.toUnitVector()).precedes(upperRight) && this.add(direction.toUnitVector()).follows(new Vector2d(0,0))) {
                result.add(this.add(direction.toUnitVector()));
            }
        }
        return result;
    }
}
