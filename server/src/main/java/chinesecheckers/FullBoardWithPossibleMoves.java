package chinesecheckers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class FullBoardWithPossibleMoves {

    public Character[][] board;
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

    public static FullBoardWithPossibleMoves fromJSON(String JSON){

        FullBoardWithPossibleMoves fullBoardWithPossibleMoves = null;

        try {
            fullBoardWithPossibleMoves = new ObjectMapper().readValue(JSON, FullBoardWithPossibleMoves.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fullBoardWithPossibleMoves;
    }

}
