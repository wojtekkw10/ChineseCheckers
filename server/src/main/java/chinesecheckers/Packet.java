package chinesecheckers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Packet {
    //Requesting new Game
    public String username;
    public String gameName;
    public int numberOfBots;

    //Joining a game
    public int id;

    //deltaAndNextPossibleMoves
    //public Field[] delta;
    //public HashMap<Field, List<Field>> possibleMoves;
    public Character currentPlayer;

    //FullBoardWithPossibleMoves
    public Character[][] board;



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

    public static Packet fromJSON(String JSON){

        Packet packet = null;

        try {
            packet = new ObjectMapper().readValue(JSON, Packet.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return packet;
    }
}
