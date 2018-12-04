package chinesecheckers;

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
        Packet newPacket = Packet.fromJSON(json);

    }
}
