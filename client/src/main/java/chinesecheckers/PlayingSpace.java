package chinesecheckers;

import java.awt.*;

public class PlayingSpace {
    private Point coordinates = new Point();
    private Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return coordinates.x;
    }

    public int getY() {
        return coordinates.y;
    }
}
