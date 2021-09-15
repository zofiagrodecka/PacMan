package project.Visualisation;

import project.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class GamePanel extends JPanel{
    private final int cell = 30;
    private final WorldMap map;

    public GamePanel(WorldMap map) {
        this.map = map;
        setPreferredSize(new Dimension(map.height*cell, map.height*cell));
    }

    @Override
    protected void paintComponent(Graphics graphics){

        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;

        g.setPaint(new Color(0, 0, 0));

        g.fillRect(0, 0, map.width * cell, map.height * cell);

        g.setPaint(new Color(182, 4, 4));

        g.drawRect(9*cell, 9*cell, 3*cell, 3*cell);

        g.setPaint(new Color(14, 32, 113));

        for(Wall w:map.walls.values()){
            g.fillRect(w.position.x*cell, w.position.y*cell, cell, cell);
        }

        g.setPaint(new Color(255, 216, 0));

        for(Coin c:map.coins.values()){
            g.fillOval(cell*c.position.x + cell/4, cell*c.position.y + cell/4, cell/2, cell/2);
        }

        for(Fruit f:map.fruits.values()){
            g.drawImage(f.image, f.position.x*cell, f.position.y*cell, cell, cell, new Color(0,0,0), null);
        }

        try {
            g.drawImage(map.pacMan.toImage(), map.pacMan.getPosition().x*cell, map.pacMan.getPosition().y*cell, cell, cell, new Color(0,0,0), null);
            for(Ghost ghost:map.ghosts){
                g.drawImage(ghost.toImage(), ghost.getPosition().x*cell, ghost.getPosition().y*cell, cell, cell, new Color(0,0,0), null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
