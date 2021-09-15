package project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WorldMap implements IMap {

    public final int width;
    public final int height;
    public final Vector2d lowerLeft;
    public final Vector2d upperRight;

    private final int difficultyLevel;

    public boolean gameOver = false;
    public boolean cherry = false;

    public PacMan pacMan = new PacMan(this);
    public Map<Vector2d, Wall> walls = new LinkedHashMap<>();
    private Map<Vector2d, Vector2d> freePositions = new LinkedHashMap<>();
    public ArrayList<Ghost> ghosts = new ArrayList<>();
    public Map<Vector2d, Coin> coins = new ConcurrentHashMap<>();
    public Map<Vector2d, Fruit> fruits = new ConcurrentHashMap<>();

    public WorldMap(int width, int height, int difficultyLevel){
        this.width = width;
        this.height = height;
        this.lowerLeft = new Vector2d(0,0);
        this.upperRight = new Vector2d(width-1, height-1);
        this.difficultyLevel = difficultyLevel;

        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                Vector2d position = new Vector2d(i, j);
                freePositions.put(position, position);
            }
        }

        for(int i=9; i<=11; i++){
            for(int j=9; j<=11; j++){
                freePositions.remove(new Vector2d(i, j));
            }
        }
        freePositions.remove(pacMan.DEFAULT_POSITION);

        place(pacMan);
        placeGhosts();

        buildWalls();
        reset();
    }

    public boolean canMoveTo(Vector2d position) {
        return position.follows(new Vector2d(0, 0)) && position.precedes(new Vector2d(this.width-1, this.height-1)) && walls.get(position) == null;
    }

    public void place(MapElement object) {
        if(object instanceof PacMan){
            pacMan = (PacMan) object;
        }
        else if(object instanceof Ghost){
            ghosts.add((Ghost) object);
        }
        else if(object instanceof Wall){
            walls.put(object.position, (Wall) object);
            freePositions.remove(object.position);
        }
        else if(object instanceof Coin){
            coins.put(object.position, (Coin) object);
        }
    }

    public void insertFruit(){
        try {
            Fruit f1 = new Fruit(new Vector2d(2,4), "apple.png", false);
            fruits.put(f1.position, f1);
            coins.remove(f1.position);
            Fruit f2 = new Fruit(new Vector2d(10,0), "banana.png", false);
            fruits.put(f2.position, f2);
            coins.remove(f2.position);
            Fruit f3 = new Fruit(new Vector2d(14,4), "grapes.png", false);
            fruits.put(f3.position, f3);
            coins.remove(f3.position);
            Fruit f4 = new Fruit(new Vector2d(10,14), "cherry.png", true);
            fruits.put(f4.position, f4);
            coins.remove(f4.position);
            Fruit f5 = new Fruit(new Vector2d(17,17), "watermelon.png", false);
            fruits.put(f5.position, f5);
            coins.remove(f5.position);

        } catch (IOException e) {
            System.out.println("File read error");
            e.printStackTrace();
        }


    }

    public boolean hasCoin(Vector2d position){
        return coins.get(position) != null;
    }

    //MOZNA USUNAC TYLKO STANDINGOBJECTS!
    public void remove(Vector2d position){
        if(coins.get(position) != null) {
            coins.remove(position);
        }
        else if(fruits.get(position) != null){
            fruits.remove(position);
        }
    }

    public void placeGhosts(){
        ghosts.clear();
        Ghost ghost = new Ghost(new Vector2d(10, 11), GhostColor.BLUE, this, difficultyLevel);
        Ghost ghost2 = new Ghost(new Vector2d(9, 10), GhostColor.GREEN, this, difficultyLevel);
        Ghost ghost3 = new Ghost(new Vector2d(11, 10), GhostColor.RED, this, difficultyLevel);
        Ghost ghost4 = new Ghost(new Vector2d(10, 9), GhostColor.YELLOW, this, difficultyLevel);
        place(ghost);
        place(ghost2);
        place(ghost3);
        place(ghost4);
    }

    public void fillCoins(){
        for(Vector2d position: freePositions.values()){
            if(!hasCoin(position)){
                place(new Coin(position));
            }
        }
        //place(new Coin(new Vector2d(11, 4)));
        //place(new Coin(new Vector2d(10, 4)));
        //place(new Coin(new Vector2d(11, 5)));
    }

    // do wykonywania przy tworzeniu nowego poziomu
    // umieszcza monety, duchy, pacmana w odp miejscach
    public void reset() {
        fillCoins();
        resetMainObjects();
        fruits.clear();
        insertFruit();
    }

    // do wykonania po przegranej
    public void resetMainObjects() {
        pacMan.setPosition(pacMan.DEFAULT_POSITION);
        pacMan.setDirection(pacMan.DEFAULT_DIRECTION);
        placeGhosts();
    }

    public void buildWalls(){

        for(int i=3; i<=17; i++){
            Wall x = new Wall(new Vector2d(i, 1 ));
            place(x);
        }

        for(int i=1; i<=3; i++){
            Wall x = new Wall(new Vector2d(1, i ));
            place(x);
        }

        for(int i=1; i<=3; i++){
            Wall x = new Wall(new Vector2d(19, i ));
            place(x);
        }

        Wall x1 = new Wall(new Vector2d(2,3));
        place(x1);
        Wall x2 = new Wall(new Vector2d(18,3));
        place(x2);

        for(int i=3; i<=5; i++){
            Wall x = new Wall(new Vector2d(3, i ));
            place(x);
        }

        for(int i=3; i<=5; i++){
            Wall x = new Wall(new Vector2d(17, i ));
            place(x);
        }

        for(int i=5; i<=6; i++){
            Wall x = new Wall(new Vector2d(i, 3));
            place(x);
        }

        for(int i=14; i<=15; i++){
            Wall x = new Wall(new Vector2d(i, 3));
            place(x);
        }

        for(int i=8; i<=9; i++){
            Wall x = new Wall(new Vector2d(i, 3));
            place(x);
        }

        for(int i=11; i<=12; i++){
            Wall x = new Wall(new Vector2d(i, 3));
            place(x);
        }

        Wall x3 = new Wall(new Vector2d(8, 4));
        place(x3);
        Wall x4 = new Wall(new Vector2d(12,4));
        place(x4);

        for(int i=1; i<=1; i++){
            Wall x = new Wall(new Vector2d(i, 5));
            place(x);
        }

        for(int i=19; i<=19; i++){
            Wall x = new Wall(new Vector2d(i, 5));
            place(x);
        }

        for(int i=5; i<=8; i++){
            Wall x = new Wall(new Vector2d(i, 5));
            place(x);
        }

        for(int i=12; i<=15; i++){
            Wall x = new Wall(new Vector2d(i, 5));
            place(x);
        }

        for(int i=5; i<=7; i++){
            Wall x = new Wall(new Vector2d(10, i));
            place(x);
        }

        for(int i=6; i<=8; i++){
            Wall x = new Wall(new Vector2d(i, 7));
            place(x);
        }

        for(int i=12; i<=14; i++){
            Wall x = new Wall(new Vector2d(i, 7));
            place(x);
        }

        for(int i=9; i<=11; i++){
            Wall x = new Wall(new Vector2d(6, i));
            place(x);
        }

        for(int i=9; i<=11; i++){
            Wall x = new Wall(new Vector2d(14, i));
            place(x);
        }

        for(int i=13; i<=15; i++){
            Wall x = new Wall(new Vector2d(6, i));
            place(x);
        }

        for(int i=13; i<=15; i++){
            Wall x = new Wall(new Vector2d(14, i));
            place(x);
        }

        for(int i=9; i<=11; i++){
            Wall x = new Wall(new Vector2d(i, 13));
            place(x);
        }

        for(int i=1; i<=4; i++){
            Wall x = new Wall(new Vector2d(i, 14));
            place(x);
        }

        for(int i=16; i<=19; i++){
            Wall x = new Wall(new Vector2d(i, 14));
            place(x);
        }

        for(int i=6; i<=14; i++){
            Wall x = new Wall(new Vector2d(i, 15));
            place(x);
        }

        for(int i=0; i<=2; i++){
            for(int j=16; j<= 17; j++){
                Wall x = new Wall(new Vector2d(i, j));
                place(x);
            }
        }

        for(int i=18; i<=20; i++){
            for(int j=16; j<= 17; j++){
                Wall x = new Wall(new Vector2d(i, j));
                place(x);
            }
        }

        for(int i=16; i<=19; i++){
            Wall x = new Wall(new Vector2d(4, i));
            place(x);
        }

        for(int i=16; i<=19; i++){
            Wall x = new Wall(new Vector2d(16, i));
            place(x);
        }

        for(int i=17; i<=19; i++){
            Wall x = new Wall(new Vector2d(14, i));
            place(x);
        }

        for(int i=17; i<=19; i++){
            Wall x = new Wall(new Vector2d(6, i));
            place(x);
        }

        for(int i=1; i<=4; i++){
            Wall x = new Wall(new Vector2d(i, 19));
            place(x);
        }

        for(int i=16; i<=19; i++){
            Wall x = new Wall(new Vector2d(i, 19));
            place(x);
        }

        for(int i=8; i<=12; i++){
            for(int j=17; j<= 19; j++){
                Wall x = new Wall(new Vector2d(i, j));
                place(x);
            }
        }

        for(int i=0; i<=4; i++){
            for(int j=7; j<= 12; j++){
                Wall x = new Wall(new Vector2d(i, j));
                place(x);
            }
        }

        for(int i=16; i<=20; i++){
            for(int j=7; j<= 12; j++){
                Wall x = new Wall(new Vector2d(i, j));
                place(x);
            }
        }
    }
}
