package chinesecheckers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.awt.event.ComponentAdapter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class Game{
    //Client client = new Client();
    private ArrayList<SimpleBot> bots = new ArrayList<SimpleBot>();
    private ArrayList<Player> players = new ArrayList<Player>();
    String name;
    int numberOfBots;
    int numberOfPlayers;

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
        private Character playerColor;
        BufferedReader input;
        private PrintWriter output;
        String username;

        public Player(Socket socket, String username, Character playerColor) {
            this.socket = socket;
            this.username = username;
            this.playerColor = playerColor;
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
                //output.println("Hello, you are client #" + clientNumber + ".");
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

                            Packet packet = new Packet();
                            packet.board = regularBoard.getBoard();

                            System.out.println("Name of the game: " + name);
                            System.out.println("CurrentPlayer.Color: "+regularBoard.getCheckerByTurn()+"This.Color"+this.playerColor);
                            if(regularBoard.getCheckerByTurn().equals(this.playerColor)) packet.isMyMove = true;
                            else packet.isMyMove = false;


                            //Converting HashMap<Field, List<Field>> do HashMap<Field, Field[]>
                            HashMap<Field, Field[]> possibleMovesArray = new HashMap<Field, Field[]>();
                            for (Map.Entry<Field, List<Field>> entry : regularBoard.getPossibleMoves().entrySet()) {
                                //System.out.println(entry.getKey() + " = " + entry.getValue());
                                possibleMovesArray.put(entry.getKey(), entry.getValue().toArray(new Field[0]));
                            }

                            packet.possibleMoves = possibleMovesArray;


                            reply.commandType = CommandType.GET_BOARD_AND_POSSIBLE_MOVES;
                            reply.content = packet.toJSON();

                            //System.out.println(reply.content);

                            output.println(reply.toJSON());


                            break;
                        case MOVE_PIN:/*
                            Move move = Move.fromJSON(command.content);
                            DeltaAndNextPossibleMoves deltaAndNextPossibleMoves = new DeltaAndNextPossibleMoves();
                            deltaAndNextPossibleMoves.delta = regularBoard.movePin(move.oldField, move.newField);
                            deltaAndNextPossibleMoves.possibleMoves = regularBoard.getPossibleMoves();
                            deltaAndNextPossibleMoves.currentPlayer = regularBoard.getCheckerByTurn();
                            Command deltaReply = new Command();
                            deltaReply.commandType = CommandType.DELTA_AND_NEXT_POSSIBLE_MOVES;
                            deltaReply.content = deltaAndNextPossibleMoves.toJSON();
                            output.println(deltaReply.toJSON());*/
                            //System.out.print(command.content);
                            Packet receivedPacket = Packet.fromJSON(command.content);
                            Move move = receivedPacket.move;
                            regularBoard.movePin(move.oldField, move.newField);
                            //output.println("Move received");


                            for(int i=0; i<players.size(); i++)
                            {
                                Command broadcastCommand = new Command();

                                Packet broadcastPacket = new Packet();
                                broadcastPacket.board = regularBoard.getBoard();

                                broadcastPacket.isMyMove = false;

                                System.out.println("determining the next player...");
                                System.out.println("CurrentPlayer.Color: "+regularBoard.getCheckerByTurn()+"This.Color"+this.playerColor);
                                if(regularBoard.getCheckerByTurn().equals('r') && i==1) broadcastPacket.isMyMove = true;
                                else if(regularBoard.getCheckerByTurn().equals('y') && i==2) broadcastPacket.isMyMove = true;
                                else if(regularBoard.getCheckerByTurn().equals('b') && i==3) broadcastPacket.isMyMove = true;
                                else if(regularBoard.getCheckerByTurn().equals('g') && i==4) broadcastPacket.isMyMove = true;
                                else if(regularBoard.getCheckerByTurn().equals('c') && i==5) broadcastPacket.isMyMove = true;
                                else if(regularBoard.getCheckerByTurn().equals('w') && i==0) broadcastPacket.isMyMove = true;



                                //Converting HashMap<Field, List<Field>> do HashMap<Field, Field[]>
                                HashMap<Field, Field[]> possibleMovesArrayBroadcast = new HashMap<Field, Field[]>();
                                for (Map.Entry<Field, List<Field>> entry : regularBoard.getPossibleMoves().entrySet()) {
                                    System.out.println(entry.getKey() + " = " + entry.getValue());
                                    possibleMovesArrayBroadcast.put(entry.getKey(), entry.getValue().toArray(new Field[0]));
                                }

                                broadcastPacket.possibleMoves = possibleMovesArrayBroadcast;


                                broadcastCommand.commandType = CommandType.GET_BOARD_AND_POSSIBLE_MOVES;
                                broadcastCommand.content = broadcastPacket.toJSON();

                                System.out.println(broadcastCommand.content);

                                System.out.println(players.size());
                                players.get(i).output.println(broadcastCommand.toJSON());
                            }






                            break;
                        case SKIP:
                            System.out.println("Player has skipped");
                            regularBoard.skip();

                            //To samo co w MOVE_PIN ale bez movePin
                            for(int i=0; i<players.size(); i++)
                            {
                                Command broadcastCommand = new Command();

                                Packet broadcastPacket = new Packet();
                                broadcastPacket.board = regularBoard.getBoard();

                                broadcastPacket.isMyMove = false;

                                System.out.println("determining the next player...");
                                System.out.println("CurrentPlayer.Color: "+regularBoard.getCheckerByTurn()+"This.Color"+this.playerColor);
                                if(regularBoard.getCheckerByTurn().equals('r') && i==1) broadcastPacket.isMyMove = true;
                                else if(regularBoard.getCheckerByTurn().equals('y') && i==2) broadcastPacket.isMyMove = true;
                                else if(regularBoard.getCheckerByTurn().equals('b') && i==3) broadcastPacket.isMyMove = true;
                                else if(regularBoard.getCheckerByTurn().equals('g') && i==4) broadcastPacket.isMyMove = true;
                                else if(regularBoard.getCheckerByTurn().equals('c') && i==5) broadcastPacket.isMyMove = true;
                                else if(regularBoard.getCheckerByTurn().equals('w') && i==0) broadcastPacket.isMyMove = true;



                                //Converting HashMap<Field, List<Field>> do HashMap<Field, Field[]>
                                HashMap<Field, Field[]> possibleMovesArrayBroadcast = new HashMap<Field, Field[]>();
                                for (Map.Entry<Field, List<Field>> entry : regularBoard.getPossibleMoves().entrySet()) {
                                    System.out.println(entry.getKey() + " = " + entry.getValue());
                                    possibleMovesArrayBroadcast.put(entry.getKey(), entry.getValue().toArray(new Field[0]));
                                }

                                broadcastPacket.possibleMoves = possibleMovesArrayBroadcast;


                                broadcastCommand.commandType = CommandType.GET_BOARD_AND_POSSIBLE_MOVES;
                                broadcastCommand.content = broadcastPacket.toJSON();

                                System.out.println(broadcastCommand.content);

                                System.out.println(players.size());
                                players.get(i).output.println(broadcastCommand.toJSON());
                            }

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
