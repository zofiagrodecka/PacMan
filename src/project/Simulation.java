package project;

import project.Visualisation.*;

public class Simulation {

    private int DEFAULT_SPEED = 300;
    private final WorldMap map;
    private final GamePanel panel;
    private int level = 1;
    public boolean pause = true;
    public int difficulty;
    private AppFrame frame;
    private NextLevelPanel nextLevelPanel = new NextLevelPanel();
    private GameOverPanel gameOverPanel = new GameOverPanel();
    public int counter = 0;
    public CherryWindow cherryWindow;

    private ChooseDifficultyPanel difficultyPanel = new ChooseDifficultyPanel();

    public Simulation() {

        this.difficulty = difficultyPanel.showWindow();

        this.map = new WorldMap(21,21, difficulty);
        this.panel = new GamePanel(map);

        this.frame = new AppFrame(map, panel, this);
    }

    public void start() throws InterruptedException {

        panel.repaint();

        while(map.pacMan.livesNumber > 0){

            if(!pause) {

                map.pacMan.move();

                for (Ghost g : map.ghosts) {
                    g.move();
                    if(map.gameOver){
                        if(map.pacMan.livesNumber > 0) {
                            restartLevel();
                        }
                        break;
                    }
                    panel.repaint();
                }

                if(map.pacMan.livesNumber > 0) {
                    collectMoney();
                }

                counter++;

                panel.repaint();
                Thread.sleep(DEFAULT_SPEED);

                if(counter == 50 && map.cherry){
                    map.cherry = false;
                    pause = true;
                    cherryWindow = new CherryWindow();
                    cherryWindow.showWindow();
                }

                if (map.pacMan.livesNumber > 0 && map.coins.size() <= 0 && map.fruits.size() <= 0) {
                    nextLevel();
                }
                if(level == 3 && map.coins.size() == 0 && map.fruits.size() == 0){
                    break;
                }
            }
            Thread.sleep(5);
        }

        gameOverPanel.showWindow("You have no more lives\n GAME OVER!", level, map.pacMan.points, map.pacMan.livesNumber);
    }

    private void collectMoney(){
        if(map.coins.get(map.pacMan.getPosition()) != null) {
            map.pacMan.addPoints(map.coins.get(map.pacMan.getPosition()).points);
        }
        else if(map.fruits.get(map.pacMan.getPosition()) != null){
            Fruit collectedFruit = map.fruits.get(map.pacMan.getPosition());
            map.pacMan.addPoints(collectedFruit.points);
            if(collectedFruit.isCherry){
                map.cherry = true;
                counter = 0;
            }
        }
        map.remove(map.pacMan.getPosition());
    }

    private void restartLevel(){
        pause = true;
        map.resetMainObjects();
        map.gameOver = false;
        panel.repaint();
        gameOverPanel.showWindow("You were caught :( Try again! :) ", level, map.pacMan.points, map.pacMan.livesNumber);
    }

    private void nextLevel(){
        pause = true;
        level++;
        DEFAULT_SPEED = DEFAULT_SPEED - 50;
        map.reset();
        panel.repaint();

        if(level <= 3) {
            nextLevelPanel.showWindow(level, map.pacMan.points, map.pacMan.livesNumber);
        }
        else
        {
            WinningPanel winner = new WinningPanel();
            winner.showWindow(level-1, map.pacMan.points, map.pacMan.livesNumber);
        }
    }
}
