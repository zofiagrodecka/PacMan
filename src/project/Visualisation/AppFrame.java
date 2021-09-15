package project.Visualisation;

import project.MoveDirection;
import project.Simulation;
import project.WorldMap;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AppFrame extends JFrame implements KeyListener {

    private WorldMap map;
    private GamePanel panel;
    private Simulation simulation;

    public AppFrame(WorldMap map, GamePanel panel, Simulation simulation) {
        super("PacMan");
        this.map = map;
        this.panel = panel;
        this.simulation = simulation;

        add(panel);

        addKeyListener(this);

        pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP -> map.pacMan.changeDirection(MoveDirection.UP);
            case KeyEvent.VK_DOWN -> map.pacMan.changeDirection(MoveDirection.DOWN);
            case KeyEvent.VK_LEFT -> map.pacMan.changeDirection(MoveDirection.LEFT);
            case KeyEvent.VK_RIGHT -> map.pacMan.changeDirection(MoveDirection.RIGHT);
        }

        char c = e.getKeyChar();
        switch (c) {
            case 'w' -> map.pacMan.changeDirection(MoveDirection.UP);
            case 's' -> map.pacMan.changeDirection(MoveDirection.DOWN);
            case 'a' -> map.pacMan.changeDirection(MoveDirection.LEFT);
            case 'd' -> map.pacMan.changeDirection(MoveDirection.RIGHT);
        }

        if(e.getKeyChar() == ' '){
            simulation.pause = !simulation.pause;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
