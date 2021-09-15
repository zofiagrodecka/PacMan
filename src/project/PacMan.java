package project;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PacMan extends MapElement{

    public final Vector2d DEFAULT_POSITION = new Vector2d(10, 4);
    public final MapDirection DEFAULT_DIRECTION = MapDirection.NORTH;

    public int livesNumber = 3;
    private MapDirection direction;
    public int points = 0;
    private WorldMap map;

    public PacMan(WorldMap map){
        this.position = DEFAULT_POSITION;
        this.direction = DEFAULT_DIRECTION;
        this.map = map;
    }

    public Vector2d getPosition(){
        return position;
    }

    public void setPosition(Vector2d newPosition){
        position = newPosition;
    }

    public void setDirection(MapDirection direction) {
        this.direction = direction;
    }

    public void changeDirection(MoveDirection newDirection){

        if(newDirection == MoveDirection.RIGHT){
            direction = MapDirection.EAST;
        }
        else if(newDirection == MoveDirection.LEFT){
            direction = MapDirection.WEST;
        }
        else if(newDirection == MoveDirection.DOWN){
            direction = MapDirection.SOUTH;
        }
        else if(newDirection == MoveDirection.UP){
            direction = MapDirection.NORTH;
        }
    }

    public void move(){

        if(map.canMoveTo(position.add(direction.toUnitVector()))) {
            position = position.add(direction.toUnitVector());
        }
    }

    public void die(){
        livesNumber--;
    }

    public void addPoints(int number){
        points+= number;
    }

    public Image toImage() throws IOException {

        String imagePath;

        switch(direction){
            case EAST -> {
                imagePath = "pac-manE.png";
                return ImageIO.read(new File(imagePath));
            }
            case WEST -> {
                imagePath = "pacmanW.png";
                return ImageIO.read(new File(imagePath));
            }
            case NORTH -> {
                imagePath = "pacmanN.png";
                return ImageIO.read(new File(imagePath));
            }
            default -> {
                imagePath = "pacmanS.png";
                return ImageIO.read(new File(imagePath));
            }
        }
    }
}
