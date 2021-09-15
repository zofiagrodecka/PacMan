package project.Visualisation;

import javax.swing.*;

public class GameOverPanel {

    public void showWindow(String message, int level, int points, int lives) {

        JOptionPane.showMessageDialog(
                null,
                "You've reached level: " + level + "\n With " + points + " points and " + lives + " lives\n" + message,
                "Congratulations!",
                JOptionPane.WARNING_MESSAGE);
    }
}
