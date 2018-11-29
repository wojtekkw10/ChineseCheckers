package chinesecheckers;

import java.awt.*;

public class FieldRepresentation {

    private Point center;
    private int radius;

    public FieldRepresentation(Point center, int radius)
    {
        this.center = center;
        this.radius = radius;
    }

    public FieldRepresentation ()
    {
        this.center = new Point(0,0);
        this.radius = 0;
    }
}
