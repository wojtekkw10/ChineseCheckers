package chinesecheckers;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProtocolTest {

    @Test
    public void testPacketConstruction() {
        Packet packet = new Packet();
        List<Field> moves = new ArrayList<Field>() {{
            add(new Field(1,2));
            add(new Field(5,6));
        }};
        packet.possibleMoves = new HashMap<Field, Field[]>() {{
            put(new Field(3,4), moves.toArray(new Field[moves.size()]));
        }};
        packet.gameName = "elo";
        packet.numberOfBots = 0;
        packet.username = "elo";
        packet.currentPlayer = 'r';
        packet.id = 0;

        String json = packet.toJSON();
        System.out.println(json);

        String expected = "{\r\n" +
                "  \"username\" : \"elo\",\r\n" +
                "  \"gameName\" : \"elo\",\r\n" +
                "  \"numberOfBots\" : 0,\r\n" +
                "  \"numberOfPlayers\" : 0,\r\n" +
                "  \"id\" : 0,\r\n" +
                "  \"currentPlayer\" : \"r\",\r\n" +
                "  \"possibleMoves\" : {\r\n" +
                "    \"\\\"(x: 4, y: 3)\\\"\" : [ \"(x: 2, y: 1)\", \"(x: 6, y: 5)\" ]\r\n" +
                "  },\r\n" +
                "  \"isMyMove\" : false,\r\n" +
                "  \"move\" : null,\r\n" +
                "  \"board\" : null\r\n" +
                "}";

        Assert.assertEquals(expected, json);

    }
}
