package chinesecheckers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Board {
    ArrayList<Player> ListOfPlayers = new ArrayList<Player>();

    abstract void checkMove(Move move);
    abstract void movePin(Move move);
    abstract void checkVictory();
    abstract ArrayList<Move> getPossibleMoves();
    void changeCurrentPlayer() {
        //TODO: implement changeCurrentPlayer()
    }
    void endTheGame() {
        //TODO: implement endTheGame()
    }
    abstract HashMap<Color, List<Field>> getPlayerMap(int numberOfPlayers);
    abstract void setNumberOfPlayers(int numberOfPlayers);
    abstract Character[][] getBoard();
    abstract Character[][] initializeHomeCorner();
    abstract Character getCheckerByTurn(int turnIndex);

}
