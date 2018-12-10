package chinesecheckers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.io.IOException;

public class Move {
    public Field oldField;
    public Field newField;

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

    public static Move fromJSON(String JSON){

        Move move = null;

        try {
            move = new ObjectMapper().readValue(JSON, Move.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return move;
    }
}
