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
        simpleBot = new SimpleBot();
    }

    @Test
    public void checkingBestMove ()
    {

        HashMap<Field, List<Field>> possibleMoves = regularBoard.getPossibleMoves();

        Field[] bestMoves = simpleBot.getTheBestMove(possibleMoves);

        Field[] expected = new Field[]{new Field(7,3), new Field(9,5)};

        Assert.assertEquals(bestMoves, expected);

    }




}
