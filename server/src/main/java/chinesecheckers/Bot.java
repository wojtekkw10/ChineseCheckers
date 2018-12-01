package chinesecheckers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Bot {
    protected HashMap<Field, List<Field>> possibleMoves = new HashMap<Field, List<Field>>();

    abstract HashMap<Field, List<Field>> getPossibleMoves();
    abstract Field[] getTheBestMove(); //scores every move and returns the best one
}
