package chinesecheckers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.awt.event.ComponentAdapter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;import java.net.Socket;
import java.util.*;

public class Game{
    //Client client = new Client();
    private ArrayList<Player> players = new ArrayList<Player>();
    String name;
    int numberOfBots;
    int numberOfPlayers;
    boolean exists = true;
    boolean full = false;

    Board regularBoard = new RegularBoard();
    Bot simpleBot = new SimpleBot();

    public void addPlayer(Player player)
    {
        players.add(player);
        players.get(players.size()-1).start();
        if(players.size()+numberOfBots==numberOfPlayers) full = true;
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

    void setMaxNumberOfPlayers(int number)
    {
        this.numberOfPlayers = number;
        this.regularBoard.setNumberOfPlayers(number);
        this.regularBoard.initialize();
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

                            System.out.println("Board Download request");
                            System.out.println("CurrentPlayer.Color: "+regularBoard.getCheckerByTurn()+"This.Color"+this.playerColor);
                            if(regularBoard.getCheckerByTurn().equals(this.playerColor)) packet.isMyMove = true;
                            else packet.isMyMove = false;

                            //Converting HashMap<Field, List<Field>> do HashMap<Field, Field[]>
                            HashMap<Field, Field[]> possibleMovesArray = new HashMap<Field, Field[]>();
                            for (Map.Entry<Field, List<Field>> entry : regularBoard.getPossibleMoves().entrySet()) {
                                possibleMovesArray.put(entry.getKey(), entry.getValue().toArray(new Field[0]));
                            }

                            packet.possibleMoves = possibleMovesArray;
                            packet.whoseTurnIsIt = regularBoard.getCheckerByTurn();
                            packet.yourColor = this.playerColor;

                            reply.commandType = CommandType.GET_BOARD_AND_POSSIBLE_MOVES;
                            reply.content = packet.toJSON();

                            output.println(reply.toJSON());
                            break;
                        case MOVE_PIN:
                            Packet receivedPacket = Packet.fromJSON(command.content);
                            Move move = receivedPacket.move;
                            regularBoard.movePin(move.oldField, move.newField);

                            if (numberOfBots != 0){

                                int thisTurn = regularBoard.getTurnIndex() % numberOfPlayers;
                                int botsTurn = players.size();

                                if (thisTurn == botsTurn){

                                    for (int i = botsTurn; i < numberOfPlayers; i++){

                                        Field[] moves = simpleBot.getTheBestMove(regularBoard.getPossibleMoves());
                                        regularBoard.movePin(moves[0], moves[1]);
                                    }
                                }
                            }

                            for(int i=0; i<players.size(); i++)
                            {
                                System.out.println("Player size: "+players.size());
                                Command broadcastCommand = new Command();

                                Packet broadcastPacket = new Packet();
                                broadcastPacket.board = regularBoard.getBoard();

                                broadcastPacket.isMyMove = false;

                                System.out.println("determining the next player...");
                                System.out.println("CurrentPlayer.Color: "+regularBoard.getCheckerByTurn()+"This.Color"+this.playerColor);

                                if(regularBoard.getTurnIndex()%numberOfPlayers==i) broadcastPacket.isMyMove = true;

                                //Converting HashMap<Field, List<Field>> do HashMap<Field, Field[]>
                                HashMap<Field, Field[]> possibleMovesArrayBroadcast = new HashMap<Field, Field[]>();
                                for (Map.Entry<Field, List<Field>> entry : regularBoard.getPossibleMoves().entrySet()) {
                                    possibleMovesArrayBroadcast.put(entry.getKey(), entry.getValue().toArray(new Field[0]));
                                }
                                broadcastPacket.possibleMoves = possibleMovesArrayBroadcast;

                                broadcastCommand.commandType = CommandType.GET_BOARD_AND_POSSIBLE_MOVES;
                                broadcastPacket.whoseTurnIsIt = regularBoard.getCheckerByTurn();
                                broadcastPacket.yourColor = players.get(i).playerColor;
                                broadcastCommand.content = broadcastPacket.toJSON();

                                System.out.println(players.get(i).playerColor+" "+broadcastPacket.isMyMove);
                                System.out.println(players.get(i).socket);

                                players.get(i).output.println(broadcastCommand.toJSON());
                            }
                            break;
                        case SKIP:
                            System.out.println("Player has skipped");
                            regularBoard.skip();

                            if (numberOfBots != 0){

                                int thisTurn = regularBoard.getTurnIndex() % numberOfPlayers;
                                int botsTurn = players.size();

                                if (thisTurn == botsTurn){

                                    for (int i = botsTurn; i < numberOfPlayers; i++){

                                        Field[] moves = simpleBot.getTheBestMove(regularBoard.getPossibleMoves());
                                        regularBoard.movePin(moves[0], moves[1]);
                                    }
                                }
                            }

                            for(int i=0; i<players.size(); i++)
                            {
                                System.out.println("Player size: "+players.size());
                                Command broadcastCommand = new Command();

                                Packet broadcastPacket = new Packet();
                                broadcastPacket.board = regularBoard.getBoard();

                                broadcastPacket.isMyMove = false;

                                System.out.println("determining the next player...");
                                System.out.println("CurrentPlayer.Color: "+regularBoard.getCheckerByTurn()+"This.Color"+this.playerColor);

                                if(regularBoard.getTurnIndex()%numberOfPlayers==i) broadcastPacket.isMyMove = true;

                                //Converting HashMap<Field, List<Field>> do HashMap<Field, Field[]>
                                HashMap<Field, Field[]> possibleMovesArrayBroadcast = new HashMap<Field, Field[]>();
                                for (Map.Entry<Field, List<Field>> entry : regularBoard.getPossibleMoves().entrySet()) {
                                    possibleMovesArrayBroadcast.put(entry.getKey(), entry.getValue().toArray(new Field[0]));
                                }
                                broadcastPacket.possibleMoves = possibleMovesArrayBroadcast;

                                broadcastCommand.commandType = CommandType.GET_BOARD_AND_POSSIBLE_MOVES;
                                broadcastPacket.whoseTurnIsIt = regularBoard.getCheckerByTurn();
                                broadcastPacket.yourColor = players.get(i).playerColor;
                                broadcastCommand.content = broadcastPacket.toJSON();

                                System.out.println(players.get(i).playerColor+" "+broadcastPacket.isMyMove);
                                System.out.println(players.get(i).socket);

                                players.get(i).output.println(broadcastCommand.toJSON());
                            }
                            break;
                        case QUIT:
                            Character color = this.playerColor;
                            for(int i=players.size()-1; i>=0; i--)
                            {

                                Command killingCommand = new Command();

                                Packet killingPacket = new Packet();
                                killingCommand.commandType = CommandType.QUIT;
                                killingCommand.content = killingPacket.toJSON();

                                players.get(i).output.println(killingCommand.toJSON());
                                players.remove(i);
                            }

                            if(players.size()==0) exists = false;

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