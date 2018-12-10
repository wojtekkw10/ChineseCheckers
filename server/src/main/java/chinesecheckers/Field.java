package chinesecheckers;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Field  {

    public int y; // columns
    public int x; // rows


    public Field (int y, int x)
    {
        this.y = y;
        this.x = x;
    }

    public Field(String key) {
        String regex = "\\(x: (\\d+), y: (\\d+)\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(key);

        if (matcher.find()) {
            x = Integer.parseInt(matcher.group(1));
            y = Integer.parseInt(matcher.group(2));
        }
    }

    public Field (){}

    @Override
    @JsonValue
    public String toString() {
        return String.format("(x: %d, y: %d)", x, y);
    }

    //@Override
    //public String toString() {
    //    return String.format("(%d,%d)", y, x);
    //}

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