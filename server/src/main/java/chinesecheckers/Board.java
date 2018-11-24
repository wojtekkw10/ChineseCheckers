package chinesecheckers;

import java.util.ArrayList;

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

}
