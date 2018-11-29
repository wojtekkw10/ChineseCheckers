package chinesecheckers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RegularBoard extends Board {


    ArrayList<Move> getPossibleMoves() {
        //TODO: implement
        return new ArrayList<Move>();
    }

    void checkMove(Move move) {
        //TODO: implement
    }

    void movePin(Move move) {
        //TODO: implement
    }

    void checkVictory() {
        //TODO: implemnt
    }

    private int numberOfPlayers = 6;

    private HashMap<Color, List<Field>> corners = new HashMap<Color, List<Field>>()     //setting default coordinates for pins at the beginning
    {
        {
            put(Color.red, new ArrayList<Field>() {  // top corner
                {
                    add(new Field(5, 1));
                    add(new Field(5, 2));
                    add(new Field(6, 2));
                    add(new Field(5, 3));
                    add(new Field(6, 3));
                    add(new Field(7, 3));
                    add(new Field(5, 4));
                    add(new Field(6, 4));
                    add(new Field(7, 4));
                    add(new Field(8, 4));
                }
            });
            put(Color.green, new ArrayList<Field>() { //bottom corner
                {
                    add(new Field(13, 17));
                    add(new Field(12, 16));
                    add(new Field(13, 16));
                    add(new Field(11, 15));
                    add(new Field(12, 15));
                    add(new Field(13, 15));
                    add(new Field(10, 14));
                    add(new Field(11, 14));
                    add(new Field(12, 14));
                    add(new Field(13, 14));
                }
            });
            put(Color.white, new ArrayList<Field>() {  //left-top corner
                {
                    add(new Field(1, 5));
                    add(new Field(2, 5));
                    add(new Field(2, 6));
                    add(new Field(3, 5));
                    add(new Field(3, 6));
                    add(new Field(3, 7));
                    add(new Field(4, 5));
                    add(new Field(4, 6));
                    add(new Field(4, 7));
                    add(new Field(4, 8));
                }
            });
            put(Color.black, new ArrayList<Field>() { //left-bottom corner
                {
                    add(new Field(5, 10));
                    add(new Field(5, 11));
                    add(new Field(5, 12));
                    add(new Field(5, 13));
                    add(new Field(6, 11));
                    add(new Field(6, 12));
                    add(new Field(6, 13));
                    add(new Field(7, 12));
                    add(new Field(7, 13));
                    add(new Field(8, 13));
                }
            });
            put(Color.yellow, new ArrayList<Field>() {  //rigth-top corner
                {
                    add(new Field(10, 5));
                    add(new Field(11, 5));
                    add(new Field(11, 6));
                    add(new Field(12, 5));
                    add(new Field(12, 6));
                    add(new Field(12, 7));
                    add(new Field(13, 5));
                    add(new Field(13, 6));
                    add(new Field(13, 7));
                    add(new Field(13, 8));
                }
            });
            put(Color.blue, new ArrayList<Field>() {  //right-bottom corner
                {
                    add(new Field(14, 10));
                    add(new Field(14, 11));
                    add(new Field(14, 12));
                    add(new Field(14, 13));
                    add(new Field(15, 11));
                    add(new Field(15, 12));
                    add(new Field(15, 13));
                    add(new Field(16, 12));
                    add(new Field(16, 13));
                    add(new Field(17, 13));
                }
            });
        }
    };

    private HashMap<Color, Character> colorsWithItsCharacterRepresentation = new HashMap<Color, Character>() {{  //identification of each color
        //as char on the board
        put(Color.red, 'r');
        put(Color.green, 'g');
        put(Color.yellow, 'y');
        put(Color.black, 'c');
        put(Color.blue, 'b');
        put(Color.white, 'w');

    }};

    /*
    setting board
    a - available field
    ' ' - not available field
    */


    private Character[][] board = {{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', 'a', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', 'a', 'a', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', 'a', 'a', 'a', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', 'a', 'a', 'a', 'a', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', ' '},
            {' ', ' ', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', ' '},
            {' ', ' ', ' ', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', ' '},
            {' ', ' ', ' ', ' ', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', ' '},
            {' ', ' ', ' ', ' ', ' ', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', ' '},
            {' ', ' ', ' ', ' ', ' ', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', ' '},
            {' ', ' ', ' ', ' ', ' ', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', ' '},
            {' ', ' ', ' ', ' ', ' ', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', ' '},
            {' ', ' ', ' ', ' ', ' ', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'a', 'a', 'a', 'a', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'a', 'a', 'a', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'a', 'a', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'a', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
    };


    public HashMap<Color, List<Field>> getPlayerMap(int numberOfPlayers)    //which corners should be filled with pins
    {
        switch (numberOfPlayers) {
            case 2:
                return new HashMap<Color, List<Field>>() {{   //for 2 players - top and bottom corners

                    put(Color.red, corners.get(Color.red));
                    put(Color.green, corners.get(Color.green));

                }};


            case 3:
                return new HashMap<Color, List<Field>>() {{ //for 3 player - top, left-top and right-top corners

                    put(Color.red, corners.get(Color.red));
                    put(Color.white, corners.get(Color.white));
                    put(Color.yellow, corners.get(Color.yellow));

                }};

            case 4:
                return new HashMap<Color, List<Field>>() {{  //for 4 players - left-top, right-top, left-bottom, right-bottom corners

                    put(Color.white, corners.get(Color.white));
                    put(Color.yellow, corners.get(Color.yellow));
                    put(Color.black, corners.get(Color.black));
                    put(Color.blue, corners.get(Color.blue));

                }};
            case 6:
            default:
                return new HashMap<Color, List<Field>>() {{  //for 6 players - all corners

                    put(Color.red, corners.get(Color.red));
                    put(Color.green, corners.get(Color.green));
                    put(Color.white, corners.get(Color.white));
                    put(Color.yellow, corners.get(Color.yellow));
                    put(Color.black, corners.get(Color.black));
                    put(Color.blue, corners.get(Color.blue));
                }};

        }
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public Character[][] getBoard() {

        return board;
    }

    public Character[][] initializeHomeCorner()         // filling corners with pins
    {
        HashMap<Color, List<Field>> mapOfCorners = getPlayerMap(numberOfPlayers);
        Character pin;

        for (Color color : colorsWithItsCharacterRepresentation.keySet()) {
            pin = colorsWithItsCharacterRepresentation.get(color);
            List<Field> corner = mapOfCorners.get(color);

            for (int j = 0; j < 10; j++) {
                board[corner.get(j).x][corner.get(j).y] = pin;
            }
        }
        return board;
    }

    public Character getCheckerByTurn(int turnIndex)  //check who's turn is it
    {
        int counter = turnIndex % numberOfPlayers;

        if (numberOfPlayers == 2) {
            switch (counter) {
                default:
                case 1:
                    return colorsWithItsCharacterRepresentation.get(Color.red);
                case 0:
                    return colorsWithItsCharacterRepresentation.get(Color.green);
            }
        }

        if (numberOfPlayers == 3) {
            switch (counter) {
                default:
                case 1:
                    return colorsWithItsCharacterRepresentation.get(Color.yellow);
                case 2:
                    return colorsWithItsCharacterRepresentation.get(Color.red);
                case 0:
                    return colorsWithItsCharacterRepresentation.get(Color.white);
            }
        }

        if (numberOfPlayers == 4) {
            switch (counter) {
                default:
                case 1:
                    return colorsWithItsCharacterRepresentation.get(Color.white);
                case 2:
                    return colorsWithItsCharacterRepresentation.get(Color.yellow);
                case 3:
                    return colorsWithItsCharacterRepresentation.get(Color.blue);
                case 0:
                    return colorsWithItsCharacterRepresentation.get(Color.black);
            }
        }

       else{
            switch (counter) {
                default:
                case 1:
                    return colorsWithItsCharacterRepresentation.get(Color.red);
                case 2:
                    return colorsWithItsCharacterRepresentation.get(Color.yellow);
                case 3:
                    return colorsWithItsCharacterRepresentation.get(Color.blue);
                case 4:
                    return colorsWithItsCharacterRepresentation.get(Color.green);
                case 5:
                    return colorsWithItsCharacterRepresentation.get(Color.black);
                case 0:
                    return colorsWithItsCharacterRepresentation.get(Color.white);

            }
        }

    }
}