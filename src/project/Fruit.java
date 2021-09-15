package project;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Fruit extends MapElement {

    public Image image;
    public final int points = 5;
    public boolean isCherry;

    public Fruit(Vector2d position, String pathToImage, boolean isCherry) throws IOException {
        this.position = position;
        this.image = importImage(pathToImage);
        this.isCherry = isCherry;
    }

    public Image importImage(String path) throws IOException {
        return ImageIO.read(new File(path));
    }
}
