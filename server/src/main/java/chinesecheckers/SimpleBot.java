package chinesecheckers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SimpleBot extends Bot {

    /*
    returns array of Field - Field[0] - coordinates of pin, Field[1] - coordinates of bestMove
    */

    Field[] getTheBestMove( HashMap<Field, List<Field>> possibleMoves){

        Field[] bestMove = new Field[2];

        double maxDistance = 0;

        for (Field pinLocation : possibleMoves.keySet()){

            List<Field> moves = possibleMoves.get(pinLocation);

            for (Field move : moves){

                double distance = Math.sqrt((move.x - pinLocation.x)*(move.x - pinLocation.x) + (move.y - pinLocation.y)*(move.y - pinLocation.y));

                if (distance > maxDistance) {

                    maxDistance = distance;
                    bestMove[0] = pinLocation;
                    bestMove[1] = move;

                }
            }

        }

        return bestMove;
    }
}
