package chinesecheckers;

import java.awt.*;

public class Move {
    private Point startPoint = new Point();
    private Point endPoint = new Point();

    public Point getEndPoint() {
        return endPoint;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }
}
