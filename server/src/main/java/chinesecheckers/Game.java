package chinesecheckers;

import java.awt.event.ComponentAdapter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Game{
    //Client client = new Client();
    private ArrayList<SimpleBot> bots = new ArrayList<SimpleBot>();
    private ArrayList<Player> players = new ArrayList<>();
    String name;
    int numberOfBots;
    Board regularBoard = new RegularBoard();


    public void addPlayer(Player player)
    {
        players.add(player);
        players.get(players.size()-1).start();
    }

    Game(String name, int numberOfBots)
    {
        this.name = name;
        this.numberOfBots = numberOfBots;
    }

    public int getNumberOfPlayers()
    {
        return players.size();
    }

    public class Player extends Thread {
        Color color;

        private Socket socket;
        private int clientNumber;
        BufferedReader input;
        private PrintWriter output;
        String username;

        public Player(Socket socket, String username) {
            this.socket = socket;
            this.username = username;
            //this.clientNumber = clientNumber;
            System.out.print("New connection at " + socket+"\n");

            // Decorate the streams so we can send characters
            // and not just bytes.  Ensure output is flushed
            // after every newline.
            try {
                input = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream(), true);

                // Send a welcome message to the client.
                output.println("Hello, you are client #" + clientNumber + ".");
            } catch (IOException e) {
                System.out.print(("SERVER: ERROR: Couldn't connect"));
            }
        }

        public void run() {
            try {

                // Repeatedly get commands from the client and process them.
                while (true) {
                    String commandAsJSON = input.readLine();
                    Command command = Command.fromJSON(commandAsJSON);

                    switch(command.commandType){

                        case NINES:

                        output.println("Received info from game");
                        break;


                        case GET_BOARD_AND_POSSIBLE_MOVES:
                            FullBoardWithPossibleMoves fullBoardWithPossibleMoves = new FullBoardWithPossibleMoves();
                            fullBoardWithPossibleMoves.board = regularBoard.getBoard();
                            fullBoardWithPossibleMoves.possibleMoves = regularBoard.getPossibleMoves();
                            fullBoardWithPossibleMoves.currentPlayer = regularBoard.getCheckerByTurn();
                            Command reply = new Command();
                            reply.commandType = CommandType.FULL_BOARD_AND_POSSIBLE_MOVES;
                            reply.content = fullBoardWithPossibleMoves.toJSON();
                            output.println(reply);
                            break;
                        case MOVE_PIN:
                            Move move = Move.fromJSON(command.content);
                            DeltaAndNextPossibleMoves deltaAndNextPossibleMoves = new DeltaAndNextPossibleMoves();
                            deltaAndNextPossibleMoves.delta = regularBoard.movePin(move.oldField, move.newField);
                            deltaAndNextPossibleMoves.possibleMoves = regularBoard.getPossibleMoves();
                            deltaAndNextPossibleMoves.currentPlayer = regularBoard.getCheckerByTurn();
                            Command deltaReply = new Command();
                            deltaReply.commandType = CommandType.DELTA_AND_NEXT_POSSIBLE_MOVES;
                            deltaReply.content = deltaAndNextPossibleMoves.toJSON();
                            output.println(deltaReply);
                            break;
                        case QUIT:
                            return;


                }}
            } catch (IOException e) {
                System.out.println("Player died: " + e);
            } finally {
                try {socket.close();} catch (IOException e) {}
            }
        }
    }
}
