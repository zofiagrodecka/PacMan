package project;

public class Coin extends MapElement{

    public final int points = 2;

    public Coin(Vector2d position){
        this.position = position;
    }

    public String toString(){
        return "$";
    }
}
