package chinesecheckers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Packet {
    //Requesting new Game
    public String username;
    public String gameName;
    public int numberOfBots;
    public int numberOfPlayers;

    //Joining a game
    public int id;

    public Character currentPlayer;
    @JsonSerialize(keyUsing = FieldSerializer.class)
    @JsonDeserialize(keyUsing = FieldDeserializer.class)
    public Map<Field, Field[]> possibleMoves;
    public boolean isMyMove;
    public Character whoseTurnIsIt;
    public Character yourColor;

    public Move move;

    public Character[][] board;



    public String toJSON(){


        ObjectMapper mapper = new ObjectMapper();

        String json = null;

        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
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