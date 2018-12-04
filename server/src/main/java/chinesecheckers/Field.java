package chinesecheckers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class Field  {

    public int y; // columns
    public int x; // rows


    public Field (int y, int x)
    {
        this.y = y;
        this.x = x;
    }

    public Field (){}




}
