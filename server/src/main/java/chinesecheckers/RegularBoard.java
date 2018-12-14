
package chinesecheckers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class RegularBoard extends Board {

    private static final int HEIGHT = 18;

    private int numberOfPlayers;
    private int turnIndex = 0;

    public void initialize()
    {
        board = initializeHomeCorner();
    }

    public void setNumberOfPlayers(int numberOfPlayers){

        this.numberOfPlayers = numberOfPlayers;

    }

    public int getTurnIndex(){

        return turnIndex;

    }

    public void skip()
    {
        turnIndex++;
    }

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

    private HashMap<Color, Color> mapOfOppositeColors = new HashMap<Color, Color>() {{ //map of opposite colors for checking victory

        put(Color.red, Color.green);
        put(Color.green, Color.red);
        put(Color.yellow, Color.black);
        put(Color.black, Color.yellow);
        put(Color.white, Color.blue);
        put(Color.blue, Color.white);

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


    public HashMap<Color, List<Field>> getPlayerMap()    //which corners should be filled with pins
    {
        switch (numberOfPlayers) {
            case 2:
                return new HashMap<Color, List<Field>>() {{   //for 2 players - top and bottom corners

                    put(Color.white, corners.get(Color.white));
                    put(Color.blue, corners.get(Color.blue));

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

    public void setNumberOfPlayers() {
        this.numberOfPlayers = numberOfPlayers;
    }

    public Character[][] getBoard() {

        return board;
    }

    public Character[][] initializeHomeCorner()         // filling corners with pins
    {
        HashMap<Color, List<Field>> mapOfCorners = getPlayerMap();
        Character pin;

        for (Color color : colorsWithItsCharacterRepresentation.keySet()) {
            pin = colorsWithItsCharacterRepresentation.get(color);
            List<Field> corner = mapOfCorners.get(color);

            for (int j = 0; j < 10; j++) {
                if(corner!=null)
                board[corner.get(j).x][corner.get(j).y] = pin;
            }
        }
        return board;
    }

    //check who's turn is it

    public Character getCheckerByTurn()
    {
        int counter = turnIndex % numberOfPlayers;

        if (numberOfPlayers == 2) {
            switch (counter) {
                default:
                case 1:
                    return colorsWithItsCharacterRepresentation.get(Color.blue);
                case 0:
                    return colorsWithItsCharacterRepresentation.get(Color.white);

            }
        }

        if (numberOfPlayers == 3) {
            switch (counter) {
                default:
                case 2:
                    return colorsWithItsCharacterRepresentation.get(Color.yellow);
                case 1:
                    return colorsWithItsCharacterRepresentation.get(Color.red);
                case 0:
                    return colorsWithItsCharacterRepresentation.get(Color.white);
            }
        }

        if (numberOfPlayers == 4) {
            switch (counter) {
                default:
                case 3:
                    return colorsWithItsCharacterRepresentation.get(Color.black);
                case 1:
                    return colorsWithItsCharacterRepresentation.get(Color.yellow);
                case 2:
                    return colorsWithItsCharacterRepresentation.get(Color.blue);
                case 0:
                    return colorsWithItsCharacterRepresentation.get(Color.white);

            }
        } else {
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

    /*
    checking victory, if anyone hasn't won returns 'Optional null' otherwise
    returns Color of winner
    */

    public Optional<Color> checkVictory() {
        HashMap<Color, List<Field>> mapOfPreFilledCorners = getPlayerMap();

        for (Color currentColorChecking : mapOfPreFilledCorners.keySet()) {
            int counter = 0;

            List<Field> oppositeCorner = corners.get(mapOfOppositeColors.get(currentColorChecking));

            for (int j = 0; j < 10; j++) {
                if (board[oppositeCorner.get(j).x][oppositeCorner.get(j).y] != colorsWithItsCharacterRepresentation.get(currentColorChecking)) {
                    break;
                }
                counter++;
            }

            if (counter == 10) {
                return Optional.of(currentColorChecking);
            }

        }

        return Optional.ofNullable(null);
    }

    /*
        returning valid position for making next move
    */

    public List<Field> getValidFromPositions() {
        Character turnChecker = getCheckerByTurn();

        List<Field> validPositions = new ArrayList<Field>();

        for (int i = 0; i < 18; i++) {

            for (int j = 0; j < board[i].length; j++) {

                if (board[i][j] == turnChecker)
                    validPositions.add(new Field(j, i));
            }


        }

        return validPositions;
    }


    public boolean isValidPosition(Field field) {
        if (field.x <= 0 || field.x >= HEIGHT || field.y >= board[field.x].length || board[field.x][field.y] == ' ')
            return false;

        return true;
    }

    /*
     checking if it's one step move, without jumps
     */

    public boolean isOneStepMove(Field oldfField, Field newField) {

        if (oldfField.equals(newField)){
            return false;
        }
        if ((Math.abs(oldfField.x - newField.x) + Math.abs(oldfField.y - newField.y) == 1 ||
                (newField.x == oldfField.x + 1 && newField.y == oldfField.y + 1) ||
                (newField.x == oldfField.x - 1 && newField.y == oldfField.y - 1)) && isValidPosition(newField) && board[newField.x][newField.y] == 'a') {

            return true;


        }

        return false;
    }

    /*
     checking if any jumps are possible and returning them
     */

    public List<Field> getValidJumps(Field field) {
        List<Field> validJumps = new ArrayList<Field>();

        if (field.x >= 0 && field.x <= HEIGHT && field.y >= 0 && field.y + 2 <= board[field.x].length &&
                board[field.x][field.y + 1] != ' ' && board[field.x][field.y + 1] != 'a' && board[field.x][field.y + 2] == 'a') {

            validJumps.add(new Field(field.y + 2, field.x));
        }

        if (field.x + 2 <= HEIGHT && field.y >= 0 && field.y + 2 <= board[field.x].length &&
                board[field.x + 1][field.y + 1] != ' ' && board[field.x + 1][field.y + 1] != 'a' && board[field.x + 2][field.y + 2] == 'a') {

            validJumps.add(new Field(field.y + 2, field.x + 2));
        }

        if (field.x + 2 <= HEIGHT && field.y >= 0 && field.y <= board[field.x].length &&
                board[field.x + 1][field.y] != ' ' && board[field.x + 1][field.y] != 'a' && board[field.x + 2][field.y] == 'a') {

            validJumps.add(new Field(field.y, field.x + 2));
        }

        if (field.x >= 0 && field.x <= HEIGHT && field.y - 2 >= 0 && field.y <= board[field.x].length &&
                board[field.x][field.y - 1] != ' ' && board[field.x][field.y - 1] != 'a' && board[field.x][field.y - 2] == 'a') {

            validJumps.add(new Field(field.y - 2, field.x));
        }

        if (field.x - 2 >= 0 && field.x <= HEIGHT && field.y - 2 >= 0 && field.y <= board[field.x].length &&
                board[field.x - 1][field.y - 1] != ' ' && board[field.x - 1][field.y - 1] != 'a' && board[field.x - 2][field.y - 2] == 'a') {

            validJumps.add(new Field(field.y - 2, field.x - 2));
        }

        if (field.x - 2 >= 0 && field.x <= HEIGHT && field.y >= 0 && field.y <= board[field.x].length &&
                board[field.x - 1][field.y] != ' ' && board[field.x - 1][field.y] != 'a' && board[field.x - 2][field.y] == 'a') {

            validJumps.add(new Field(field.y, field.x - 2));
        }

        return validJumps;
    }

    // interface for method in isMultiStepMove

    interface SubfunctionHelper {
        boolean isValidHopMove(Field newField);


    }
    /*
        checking if it's multi step move - with jumps,
        and returning possible moves
        returns empty ArrayList if it's not Multi Step Move
     */

    public List<Field> isMultiStepMove(Field oldField, Field field) {

        List<Field> hops = new ArrayList<Field>();

        if (oldField.equals(field)){
            return hops;
        }

        SubfunctionHelper isValidHopMove = new SubfunctionHelper() {

            public boolean isValidHopMove(Field pos) {

                if (pos.x == field.x && pos.y == field.y) {
                    return true;
                }
                else if (!isValidPosition(pos))
                {
                    return false;
                }

                hops.add(pos);
                List<Field> jumps = getValidJumps(pos);
                boolean valid = false;
                for (int i = 0; i < jumps.size(); i++){

                    if (!hops.contains(jumps.get(i)))
                    {
                        valid |= isValidHopMove(jumps.get(i));
                    }

                }
                if ( !valid ) {

                    hops.remove(hops.size() - 1);

                }
                return valid;
            }



        };

        if (  isValidHopMove.isValidHopMove(oldField))
        {
            hops.add(field);
            return hops;
        }

        return new ArrayList<Field>(); // returns empty ArrayList if it's not Multi Step Move
    }

    HashMap<Field, List<Field>> getPossibleMoves(){

        HashMap<Field, List<Field>> possibleMoves = new HashMap<Field, List<Field>>();
        List<Field> validFromPosition = getValidFromPositions();

        for (Field pos : validFromPosition) {

            possibleMoves.put(pos, new ArrayList<Field>());
            List<Field> movesForPin = possibleMoves.get(pos);

            for (int j = 0; j < 18; j++) {
                for (int k = 1; k < board[j].length; k++) {

                    Field newField = new Field(j,k);
                    if (pos.equals(newField)){
                        continue;
                    }
                    List<Field> var = isMultiStepMove(pos, newField);
                    if (var.size() > 0){
                        movesForPin.addAll(var);
                    }

                    if (isOneStepMove(pos, newField)){
                        movesForPin.add(newField);
                    }
                }
                while(movesForPin.remove(pos)){}
            }
        }

        return possibleMoves;
    }


    Field[] movePin(Field oldField, Field newField) {

        board[newField.x][newField.y] = board[oldField.x][oldField.y];
        board[oldField.x][oldField.y] = 'a';
        turnIndex++;

        Field[] delta = new Field[2];
        delta[0] = oldField;
        delta[1] = newField;

        return delta;

    }

}

