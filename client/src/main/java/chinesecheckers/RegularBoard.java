package chinesecheckers;

import java.awt.*;
import java.util.ArrayList;

public class RegularBoard extends Board {
    public void getSampleBoard()
    {
        ArrayList<PlayingSpace> b = new ArrayList<PlayingSpace>();
        b.add(new PlayingSpace(new Point(20, 20), Color.red));
        b.add(new PlayingSpace(new Point(30, 20), Color.red));
        b.add(new PlayingSpace(new Point(40, 20), Color.red));
        b.add(new PlayingSpace(new Point(20, 50), Color.red));
        b.add(new PlayingSpace(new Point(20, 60), Color.red));
        b.add(new PlayingSpace(new Point(20, 70), Color.red));
        b.add(new PlayingSpace(new Point(20, 80), Color.red));
        b.add(new PlayingSpace(new Point(40, 90), Color.red));
        b.add(new PlayingSpace(new Point(20, 100), Color.red));
        board = b;
    }
}
