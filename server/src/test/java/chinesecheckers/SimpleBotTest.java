package chinesecheckers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import java.util.HashMap;
import java.util.List;


public class SimpleBotTest {

    RegularBoard regularBoard;
    SimpleBot simpleBot;
    @Before
    public void beforeMethod(){

        regularBoard = new RegularBoard();
        regularBoard.setNumberOfPlayers(6);
        regularBoard.initialize();
        simpleBot = new SimpleBot();
    }

    @Test
    public void checkingBestMove ()
    {
        regularBoard.setNumberOfPlayers(6);

        HashMap<Field, List<Field>> possibleMoves = regularBoard.getPossibleMoves();

        Field[] bestMoves = simpleBot.getTheBestMove(possibleMoves);

        Field[] expected = new Field[]{new Field(3,5), new Field(5,7)};

        Assert.assertEquals(expected, bestMoves);

    }




}
