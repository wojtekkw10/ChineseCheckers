package chinesecheckers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SimpleBot extends Bot {
    HashMap<Field, List<Field>> getPossibleMoves() {

        Board regularBoard = new RegularBoard();

        HashMap<Field, List<Field>> possibleMoves = regularBoard.getPossibleMoves();

        return possibleMoves;
    }

    Field[] getTheBestMove(){

        Field[] bestMove = new Field[2];



        return bestMove;
    } //scores every move and returns the best one
}
