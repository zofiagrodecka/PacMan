package project.Visualisation;

import javax.swing.*;

public class NextLevelPanel {

    public void showWindow(int level, int points, int lives) {

        JOptionPane.showMessageDialog(
                null,
                "You've reached level: " + level + "\n Your points: " + points + "\n Lives number: " + lives,
                "Congratulations!",
                JOptionPane.WARNING_MESSAGE);
    }
}
