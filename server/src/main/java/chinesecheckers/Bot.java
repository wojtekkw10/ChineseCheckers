package chinesecheckers;

import java.util.ArrayList;

public abstract class Bot {
    protected ArrayList<Move> possibleMoves = new ArrayList<Move>();

    abstract ArrayList<Move> getPossibleMoves();
    abstract Move getTheBestMove(); //scores every move and returns the best one
}
