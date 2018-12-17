package chinesecheckers;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Field {

    private int y; // columns
    private int x; // rows

    Field(int y, int x) {
        this.y = y;
        this.x = x;
    }
    public Field (){}

    public Field(String key) {
        String regex = "\\(x: (\\d+), y: (\\d+)\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(key);

        if (matcher.find()) {
            x = Integer.parseInt(matcher.group(1));
            y = Integer.parseInt(matcher.group(2));
        }
    }

    @Override
    @JsonValue
    public String toString() {
        return String.format("(x: %d, y: %d)", x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return y == field.y &&
                x == field.x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
