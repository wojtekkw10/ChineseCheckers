package chinesecheckers;

import java.awt.*;
import java.util.ArrayList;

public class RegularBoard extends Board {
    public void getSampleBoard()
    {
        ArrayList<PlayingSpace> b = new ArrayList<PlayingSpace>();
        for(int i=380, j=196; i<550; i+=30, j+=46){
            b.add(new PlayingSpace(new Point(i, j), Color.red));
        }

        board = b;
    }
}
