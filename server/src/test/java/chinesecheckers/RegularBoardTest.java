package chinesecheckers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

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


      // regularBoard.movePin(oldField, newField);

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

        Field oldField = new Field(5,4);
        Field newField = new Field(6,5);


        regularBoard.movePin(oldField, newField);

        Field a = new Field(5,5);

        List<Field> jumpsy = regularBoard.getValidJumps(a);

        for (Field f : jumpsy)
        {

            System.out.println(f.x + " " + f.y );

        }

    }

    @Test
    public void testMultiStepMove(){


        Field as = new Field(6,4);
        Field bs= new Field(5,5);


        regularBoard.movePin(as, bs);
        PrintBoard();
        Field a = new Field(5,3);
        Field b = new Field(7,5);

        List<Field> jumpsy = regularBoard.isMultiStepMove(a,b);



        for (Field f : jumpsy)
        {

            System.out.println(f.x + " " + f.y );

        }

    }
}


