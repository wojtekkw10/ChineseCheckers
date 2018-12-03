package chinesecheckers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Field {

    public int y; // columns
    public int x; // rows


    public Field (int y, int x)
    {
        this.y = y;
        this.x = x;
    }

    public Field (){}

}
