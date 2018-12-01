package chinesecheckers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public abstract class Board {
    ArrayList<Player> ListOfPlayers = new ArrayList<Player>();


    abstract void movePin(Field oldField, Field newField);
    abstract Optional<Color> checkVictory(Character board[][]);
    abstract HashMap<Field, List<Field>> getPossibleMoves(Character[][] board, int turnIndex);
   /* void changeCurrentPlayer() {
        //TODO: implement changeCurrentPlayer()
    }
    void endTheGame() {
        //TODO: implement endTheGame()
    }*/
    abstract HashMap<Color, List<Field>> getPlayerMap(int numberOfPlayers);
    abstract void setNumberOfPlayers(int numberOfPlayers);
    abstract Character[][] getBoard();
    abstract Character[][] initializeHomeCorner();
    abstract Character getCheckerByTurn(int turnIndex);
    abstract List<Field> getValidJumps(Field field, Character[][] board);
    abstract boolean isOneStepMove(Field oldfField, Field newField);
    abstract boolean isValidPosition(Field field, Character[][] board);
    abstract List<Field> getValidFromPositions(Character[][] board, int turnIndex);

}
