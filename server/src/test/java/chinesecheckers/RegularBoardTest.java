package chinesecheckers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegularBoardTest {

    RegularBoard regularBoard;

    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Before
    public void beforeMethod(){

        regularBoard = new RegularBoard();

    }


    public void PrintBoard()
    {
        Character[][] boardContent = regularBoard.getBoard();

        int c = 0;
        System.out.print(" ");
        for(int i = 0; i <15; i++)
            System.out.print(i%10);

        System.out.println("");

        for (Character[] row : boardContent) {
            System.out.print(c++%10);
            for (Character sign : row){
                System.out.print(sign);
            }
            System.out.println("");
        }
    }

    @Test
    public void performSingleCorrectMove(){

        Character[][] boardContent = regularBoard.getBoard();

        Field oldField = new Field(5,4);
        Field newField = new Field(5,5);

        PrintBoard();

        Field[] moves = regularBoard.movePin(oldField, newField);

       // PrintBoard();

        char a = boardContent[4][5];
        char b = boardContent[5][5];

        assertEquals(a, 'a');
        assertEquals(b, 'r');
    }


    @Test
    public void checkPossibleMoves()
    {

        Character[][] boardContetnt = regularBoard.getBoard();



        Field oldField = new Field(5,4);
        Field newField = new Field(6,5);


       //regularBoard.movePin(oldField, newField);

        PrintBoard();
        HashMap<Field, List<Field>> possibleMoves = regularBoard.getPossibleMoves();

        for (Field name : possibleMoves.keySet())
        {

            System.out.println(name.x + " " + name.y +"key ");
            List<Field> value = possibleMoves.get(name);



            for (Field f : value){

                System.out.print(f.x + " " + f.y + "|");

            }

            System.out.println( " " );

        }

    }

    @Test
    public void testValidJumps(){

        Field oldField = new Field(6,4);
        Field newField = new Field(6,5);


        regularBoard.movePin(oldField, newField);



        PrintBoard();
        Field a = new Field(5,5);

        List<Field> jumpsy = regularBoard.getValidJumps(a);

        for (Field f : jumpsy)
        {

            System.out.println(f.x + " " + f.y );

        }

    }

    @Test
    public void testMultiStepMove(){


        Field oldField = new Field(6,4);
        Field newField = new Field(6,5);


        regularBoard.movePin(oldField, newField);

        PrintBoard();
        Field a = new Field(5,3);
        Field b = new Field(7,5);

        List<Field> multiMove = regularBoard.isMultiStepMove(a,b);



        for (Field f : multiMove)
        {

            System.out.println(f.x + " " + f.y );

        }

    }

    @Test
    public void changePlayerTest(){

        char player1 = regularBoard.getCheckerByTurn();

        Field oldField = new Field(6,4);
        Field newField = new Field(6,5);


        regularBoard.movePin(oldField, newField);

        char player2 = regularBoard.getCheckerByTurn();

        regularBoard.movePin(oldField, newField);

        char player3 = regularBoard.getCheckerByTurn();

        regularBoard.movePin(oldField, newField);

        char player4 = regularBoard.getCheckerByTurn();

        regularBoard.movePin(oldField, newField);

        char player5 = regularBoard.getCheckerByTurn();

        regularBoard.movePin(oldField, newField);

        char player6 = regularBoard.getCheckerByTurn();

        regularBoard.movePin(oldField, newField);

        char player7 = regularBoard.getCheckerByTurn();

        Assert.assertEquals(player1, 'r');
        Assert.assertEquals(player2, 'y');
        Assert.assertEquals(player3, 'b');
        Assert.assertEquals(player4, 'g');
        Assert.assertEquals(player5, 'c');
        Assert.assertEquals(player6, 'w');
        Assert.assertEquals(player7, 'r');

    }

    @Test
    public void checkVictoryTest(){

        Field a1 = new Field(5, 1);
        Field a2 = new Field(5, 2);
        Field a3 = new Field(6, 2);
        Field a4 = new Field(5, 3);
        Field a5 = new Field(6, 3);
        Field a6 = new Field(7, 3);
        Field a7 = new Field(5, 4);
        Field a8 = new Field(6, 4);
        Field a9 = new Field(7, 4);
        Field a10 = new Field(8, 4);

        Field b1 = new Field(13, 17);
        Field b2 = new Field(12, 16);
        Field b3 = new Field(13, 16);
        Field b4 = new Field(11, 15);
        Field b5 = new Field(12, 15);
        Field b6 = new Field(13, 15);
        Field b7 = new Field(10, 14);
        Field b8 = new Field(11, 14);
        Field b9 = new Field(12, 14);
        Field b10 = new Field(13, 14);

        Assert.assertEquals(regularBoard.checkVictory(), Optional.ofNullable(null));

        regularBoard.movePin(a1, b1);
        regularBoard.movePin(a2, b2);
        regularBoard.movePin(a3, b3);
        regularBoard.movePin(a4, b4);
        regularBoard.movePin(a5, b5);

        Assert.assertEquals(regularBoard.checkVictory(), Optional.ofNullable(null));

        regularBoard.movePin(a6, b6);
        regularBoard.movePin(a7, b7);
        regularBoard.movePin(a8, b8);
        regularBoard.movePin(a9, b9);
        regularBoard.movePin(a10, b10);

        Assert.assertEquals(regularBoard.checkVictory(), Optional.of(Color.red));

    }
}


