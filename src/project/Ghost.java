package project;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Ghost extends MapElement {

    public final MapDirection DEFAULT_DIRECTION = MapDirection.NORTH;

    private MapDirection direction = MapDirection.NORTH;
    private GhostColor color;
    public final int points = 10;
    private WorldMap map;
   /* private int leftLikelihood;
    private int rightLikelihood;
    private int upLikelihood;
    private int downLikelihood;*/
    private Random random;
    private PacMan pacMan;
    private int difficultyLevel;

    public Ghost(Vector2d position, GhostColor color, WorldMap map, int difficultyLevel){
        this.position = position;
        this.color = color;
        this.map = map;
        this.pacMan = map.pacMan;
        this.difficultyLevel = difficultyLevel;

        int s = 0;
        for(int i=0; i<color.toString().length(); i++){
            s += color.toString().charAt(i);
        }
        Calendar c1 = Calendar.getInstance();
        Date dateOne = c1.getTime();
        s+= dateOne.getTime();

        this.random = new Random(s);
    }

    public Vector2d getPosition(){
        return position;
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

    public int counter = 0;

    public void move(){
        if(counter % difficultyLevel != 0) {
            countLikelihood();
        }
        else {
            direction = drawDirection();
            counter = 0;
        }
        counter++;
        if(map.canMoveTo(position.add(direction.toUnitVector()))){
            position = position.add(direction.toUnitVector());
        }

        if(position.equals(map.pacMan.position)) {
            if(!map.cherry) {
                map.gameOver = true;
                pacMan.die();
            }else{
                goHome();
            }
        }
    }

    private MapDirection drawDirection(){

        MapDirection dir;
        do {
            int x = random.nextInt(4);
            dir = switch (x) {
                case 0 -> MapDirection.NORTH;
                case 1 -> MapDirection.EAST;
                case 2 -> MapDirection.SOUTH;
                default -> MapDirection.WEST;
            };
        }while(!map.canMoveTo(position.add(dir.toUnitVector())));

        return dir;
    }

    public void goHome(){
        switch(color){
            case YELLOW -> position = new Vector2d(10, 9);
            case GREEN -> position = new Vector2d(9,10);
            case BLUE -> position = new Vector2d(10, 11);
            case RED -> position = new Vector2d(11, 10);
        }
    }

    public Image toImage() throws IOException {

        String imagePath = color.toString() + ".png";
        return ImageIO.read(new File(imagePath));
    }

    private void countLikelihood(){

        if(pacMan.position.x == position.x){
            if(pacMan.position.y == position.y){
                //System.out.println("Game over");
                map.gameOver = true;
                map.pacMan.die();
            }
            else if (pacMan.position.y < position.y){
                /*upLikelihood = 100;
                downLikelihood = 0;
                leftLikelihood = 0;
                rightLikelihood = 0;*/
                if(map.canMoveTo(position.add(MapDirection.NORTH.toUnitVector()))){
                    changeDirection(MoveDirection.UP);
                }
                else if(map.canMoveTo(position.add(MapDirection.EAST.toUnitVector()))){
                    changeDirection(MoveDirection.RIGHT);
                }
                else if(map.canMoveTo(position.add(MapDirection.WEST.toUnitVector()))){
                    changeDirection(MoveDirection.LEFT);
                }
                else{
                    changeDirection((MoveDirection.DOWN));
                }
            }
            else{
                /*upLikelihood = 0;
                downLikelihood = 100;
                leftLikelihood = 0;
                rightLikelihood = 0;*/

                if(map.canMoveTo(position.add(MapDirection.SOUTH.toUnitVector()))){
                    changeDirection(MoveDirection.DOWN);
                }
                else if(map.canMoveTo(position.add(MapDirection.EAST.toUnitVector()))){
                    changeDirection(MoveDirection.RIGHT);
                }
                else if(map.canMoveTo(position.add(MapDirection.WEST.toUnitVector()))){
                    changeDirection(MoveDirection.LEFT);
                }
                else{
                    changeDirection((MoveDirection.UP));
                }
            }
        }
        else if(pacMan.position.x < position.x){
            if(pacMan.position.y == position.y){
                /*upLikelihood = 0;
                downLikelihood = 0;
                leftLikelihood = 100;
                rightLikelihood = 0;*/

                if(map.canMoveTo(position.add(MapDirection.WEST.toUnitVector()))){
                    changeDirection(MoveDirection.LEFT);
                }
                else if(map.canMoveTo(position.add(MapDirection.NORTH.toUnitVector()))){
                    changeDirection(MoveDirection.UP);
                }
                else if(map.canMoveTo(position.add(MapDirection.SOUTH.toUnitVector()))){
                    changeDirection(MoveDirection.DOWN);
                }
                else{
                    changeDirection((MoveDirection.LEFT));
                }
            }
            else if (pacMan.position.y < position.y){
                /*upLikelihood = 50;
                downLikelihood = 0;
                leftLikelihood = 50;
                rightLikelihood = 0;*/

                if(map.canMoveTo(position.add(MapDirection.NORTH.toUnitVector()))){
                    changeDirection(MoveDirection.UP);
                }
                else if(map.canMoveTo(position.add(MapDirection.WEST.toUnitVector()))){
                    changeDirection(MoveDirection.LEFT);
                }
                else if(map.canMoveTo(position.add(MapDirection.EAST.toUnitVector()))){
                    changeDirection(MoveDirection.RIGHT);
                }
                else{
                    changeDirection((MoveDirection.DOWN));
                }
            }
            else{
                /*upLikelihood = 0;
                downLikelihood = 50;
                leftLikelihood = 50;
                rightLikelihood = 0;*/

                if(map.canMoveTo(position.add(MapDirection.SOUTH.toUnitVector()))){
                    changeDirection(MoveDirection.DOWN);
                }
                else if(map.canMoveTo(position.add(MapDirection.WEST.toUnitVector()))){
                    changeDirection(MoveDirection.LEFT);
                }
                else if(map.canMoveTo(position.add(MapDirection.EAST.toUnitVector()))){
                    changeDirection(MoveDirection.RIGHT);
                }
                else{
                    changeDirection((MoveDirection.UP));
                }
            }
        }
        else{
            if(pacMan.position.y == position.y){
                /*upLikelihood = 0;
                downLikelihood = 0;
                leftLikelihood = 0;
                rightLikelihood = 100;*/

                if(map.canMoveTo(position.add(MapDirection.EAST.toUnitVector()))){
                    changeDirection(MoveDirection.RIGHT);
                }
                else if(map.canMoveTo(position.add(MapDirection.NORTH.toUnitVector()))){
                    changeDirection(MoveDirection.UP);
                }
                else if(map.canMoveTo(position.add(MapDirection.SOUTH.toUnitVector()))){
                    changeDirection(MoveDirection.DOWN);
                }
                else{
                    changeDirection((MoveDirection.LEFT));
                }
            }
            else if (pacMan.position.y < position.y){
                /*upLikelihood = 50;
                downLikelihood = 0;
                leftLikelihood = 0;
                rightLikelihood = 50;*/

                if(map.canMoveTo(position.add(MapDirection.NORTH.toUnitVector()))){
                    changeDirection(MoveDirection.UP);
                }
                else if(map.canMoveTo(position.add(MapDirection.EAST.toUnitVector()))){
                    changeDirection(MoveDirection.RIGHT);
                }
                else if(map.canMoveTo(position.add(MapDirection.WEST.toUnitVector()))){
                    changeDirection(MoveDirection.LEFT);
                }
                else{
                    changeDirection((MoveDirection.DOWN));
                }
            }
            else{
                /*upLikelihood = 0;
                downLikelihood = 50;
                leftLikelihood = 0;
                rightLikelihood = 50;*/

                if(map.canMoveTo(position.add(MapDirection.SOUTH.toUnitVector()))){
                    changeDirection(MoveDirection.DOWN);
                }
                else if(map.canMoveTo(position.add(MapDirection.EAST.toUnitVector()))){
                    changeDirection(MoveDirection.RIGHT);
                }
                else if(map.canMoveTo(position.add(MapDirection.WEST.toUnitVector()))){
                    changeDirection(MoveDirection.LEFT);
                }
                else{
                    changeDirection((MoveDirection.UP));
                }
            }
        }
    }
}
