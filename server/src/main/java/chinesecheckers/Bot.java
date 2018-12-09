package chinesecheckers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Bot {

    abstract Field[] getTheBestMove( HashMap<Field, List<Field>> possibleMoves); //scores every move and returns the best one
}
