package chinesecheckers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public abstract class Board {
    ArrayList<Player> ListOfPlayers = new ArrayList<Player>();


    abstract Field[] movePin(Field oldField, Field newField);
    abstract Optional<Color> checkVictory();
    abstract HashMap<Field, List<Field>> getPossibleMoves();
    abstract HashMap<Color, List<Field>> getPlayerMap();
    abstract void setNumberOfPlayers(int numberOfPlayers);
    abstract Character[][] getBoard();
    abstract Character[][] initializeHomeCorner();
    abstract Character getCheckerByTurn();
    abstract List<Field> getValidJumps(Field field);
    abstract boolean isOneStepMove(Field oldfField, Field newField);
    abstract boolean isValidPosition(Field field);
    abstract List<Field> getValidFromPositions();
    abstract void skip();
    abstract int getTurnIndex();

}
