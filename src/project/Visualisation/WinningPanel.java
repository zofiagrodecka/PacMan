package project.Visualisation;

import javax.swing.*;

public class WinningPanel {

    public void showWindow(int level, int points, int lives) {

        JOptionPane.showMessageDialog(
                null,
                "YOU WON! \n You've finished level: " + level + "\n With " + points + " points and " + lives + " lives\n",
                "Congratulations!",
                JOptionPane.WARNING_MESSAGE);
    }
}
