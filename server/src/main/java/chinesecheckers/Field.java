package chinesecheckers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Objects;

public class Field  {

    public int y; // columns
    public int x; // rows


    public Field (int y, int x)
    {
        this.y = y;
        this.x = x;
    }

    public Field (){}

    @Override
    public String toString() {
        return String.format("(%d,%d)", y, x);
    }

    @Override
    public boolean equals(Object object)
    {
        if (object instanceof Field)
        {
            Field field = (Field)object;
            return field.x == this.x && field.y == this.y;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x);
    }

}