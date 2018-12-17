package chinesecheckers;

import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import java.io.IOException;
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
        packet.gameName = "game";
        packet.numberOfBots = 0;
        packet.username = "username";
        packet.currentPlayer = 'r';
        packet.id = 0;

        String json = packet.toJSON();
        Packet newPacket = Packet.fromJSON(json);
        String newJSON = newPacket.toJSON();
        System.out.println(json);

        String expected = "{\r\n" +
                "  \"username\" : \"username\",\r\n" +
                "  \"gameName\" : \"game\",\r\n" +
                "  \"numberOfBots\" : 0,\r\n" +
                "  \"numberOfPlayers\" : 0,\r\n" +
                "  \"id\" : 0,\r\n" +
                "  \"currentPlayer\" : \"r\",\r\n" +
                "  \"possibleMoves\" : {\r\n" +
                "    \"\\\"(x: 4, y: 3)\\\"\" : [ \"(x: 2, y: 1)\", \"(x: 6, y: 5)\" ]\r\n" +
                "  },\r\n" +
                "  \"isMyMove\" : false,\r\n" +
                "  \"whoseTurnIsIt\" : null,\r\n" +
                "  \"yourColor\" : null,\r\n" +
                "  \"move\" : null,\r\n" +
                "  \"board\" : null\r\n" +
                "}";

        Assert.assertEquals(expected, newJSON);

    }

    @Test
    public void testServerConnection() {
        Server server = new Server();
        try { server.connect(new JFrame()); } catch(IOException e) {}

        Server server2 = new Server();
        try { server2.connect(new JFrame()); } catch(IOException e) {}

        String message = server.requestNewGame("name", 3, 6);
        String message2 = server2.joinGame(0);

        String expected = "Welcome";
        String expected2 = "You have been added to the game";
        Assert.assertEquals(expected, message);
        Assert.assertEquals(expected2, message2);

    }
}
