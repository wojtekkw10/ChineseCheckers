package chinesecheckers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DeltaAndNextPossibleMoves {

    public Field[] delta;
    public HashMap<Field, List<Field>> possibleMoves;
    public Character currentPlayer;

    public String toJSON(){


        ObjectMapper mapper = new ObjectMapper();

        String json = null;

        try {
            json = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }

    public static DeltaAndNextPossibleMoves fromJSON(String JSON){

        DeltaAndNextPossibleMoves deltaAndNextPossibleMoves = null;

        try {
            deltaAndNextPossibleMoves = new ObjectMapper().readValue(JSON, DeltaAndNextPossibleMoves.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return deltaAndNextPossibleMoves;
    }
}
