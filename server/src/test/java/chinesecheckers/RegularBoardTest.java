package chinesecheckers;

import org.junit.Before;
import org.junit.Test;

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

        Field oldField = new Field(5,4);
        Field newField = new Field(5,5);

        PrintBoard();

        Field[] moves = regularBoard.movePin(oldField, newField);

        PrintBoard();
    }
}
