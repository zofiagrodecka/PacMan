package project.Visualisation;

import javax.swing.*;

public class ChooseDifficultyPanel {

    public int showWindow() {

        Object[] possibilities = {"EASY", "MEDIUM", "HARD"};
        String option = (String) JOptionPane.showInputDialog(
                null,
                "Choose difficulty level:\n After pressing OK, press pause to start or pause the game.",
                "Difficulty Level",
                JOptionPane.INFORMATION_MESSAGE,
                null,
                possibilities,
                possibilities[0]);
        return getChosenOption(option);
    }

    private int getChosenOption(String option) throws IllegalStateException {
        int res = 0;
        if ((option != null) && (option.length() > 0)) {
            res = switch (option){
                case "EASY" -> 1;
                case "MEDIUM" -> 2;
                case "HARD" -> 3;
                default -> throw new IllegalStateException("Unexpected value: " + option);
            };
        }
        return res;
    }
}
