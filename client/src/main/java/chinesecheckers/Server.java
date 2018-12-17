package chinesecheckers;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Server {
    private boolean isMyMove;
    private ArrayList<GameInfo> listOfgames = new ArrayList<GameInfo>();

    private BufferedReader in;
    private PrintWriter out;

    public void connect(JFrame frame) throws IOException {

        //Uncomment if the server is on a different computer
            /*// Get the server address from a dialog box.
            String serverAddress = JOptionPane.showInputDialog(
                    frame,
                    "Enter IP Address of the Server:",
                    "Connect",
                    JOptionPane.QUESTION_MESSAGE);

*/
        // Make connection and initialize streams
        Socket socket = new Socket("127.0.0.1", 9898);
        in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        System.out.println(socket);

    }

    void skip()
    {
        System.out.println("Skipping");
        Command command = new Command();
        command.commandType = CommandType.SKIP;
        Packet packet = new Packet();
        command.content = packet.toJSON();
        out.println(command.toJSON());
    }

    public String requestNewGame(String name, int numberOfBots, String username, int maxNumberOfPlayers) {
        System.out.print("Requested a new game\n");

        Packet packet = new Packet();
        packet.username = "username";
        packet.gameName = name;
        packet.numberOfBots = numberOfBots;
        packet.numberOfPlayers = maxNumberOfPlayers;

        Command command = new Command();
        command.commandType = CommandType.REQUEST_NEW_GAME;
        command.content = packet.toJSON();
        String welcomeMessage = new String();

        out.println(command.toJSON());
        while(true)
        {
            try{ sleep(1000);} catch (InterruptedException e){}

            try{  welcomeMessage = in.readLine();}
            catch(IOException e ) { System.out.print("Error: Didnt start a new game\n");}
            System.out.println(welcomeMessage);
            return "";
        }
    }

    public void quit() {
        System.out.print("Quitting\n");

        Packet packet = new Packet();

        Command command = new Command();
        command.commandType = CommandType.QUIT;
        command.content = packet.toJSON();

        out.println(command.toJSON());
    }


        public void uploadMove(Move move) {
        System.out.println("Uploading Move");
        Command command = new Command();
        command.commandType = CommandType.MOVE_PIN;
        Packet packet = new Packet();
        packet.move = move;
        command.content = packet.toJSON();
        out.println(command.toJSON());
    }

    String joinGame(int id, String username)
    {
        Packet packet = new Packet();

        Command command = new Command();
        command.commandType = CommandType.JOIN_A_GAME;
        command.content = packet.toJSON();

        out.println(command.toJSON());

        out.println(id);
        out.println(username);

        String feedback = new String();
        while(true)
        {
            try{ sleep(1000);} catch (InterruptedException e){}

            try{  feedback = in.readLine();}
            catch(IOException e ) { System.out.print("Error: Didnt start a new game\n");}
            System.out.println(feedback);
            return "";
        }
    }

    public Command listen()
    {
        while(true)
        {
            String commandAsJSON = new String();

            try{ sleep(100);} catch (InterruptedException e){}

            System.out.println("listening...");
            try{  commandAsJSON = in.readLine();}
            catch(IOException e ) { System.out.print("Error: Didnt receive BoardState\n");}
            Command receivedCommand = Command.fromJSON(commandAsJSON);
            if(receivedCommand!=null) return receivedCommand;
            else return new Command();
        }
    }

    public synchronized Packet downloadBoardState() {

        System.out.println("Downloading BoardState");
        Command command = new Command();
        command.commandType = CommandType.GET_BOARD_AND_POSSIBLE_MOVES;
        Packet packet = new Packet();
        command.content = packet.toJSON();
        out.println(command.toJSON());

        Packet receivedPacket;

        while(true)
        {
            String commandAsJSON = new String();

            try{ sleep(100);} catch (InterruptedException e){}

            try{  commandAsJSON = in.readLine();}
            catch(IOException e ) { System.out.print("Error: Didnt receive BoardState\n");}
            Command receivedCommand = Command.fromJSON(commandAsJSON);
            receivedPacket = Packet.fromJSON(receivedCommand.content);

            System.out.println("Current Player Color: "+receivedPacket.whoseTurnIsIt);

            return receivedPacket;
        }

    }

    //Returns an arrays of IDs
    public ArrayList<GameInfo> downloadAllGames(){
        System.out.print("Requested all games\n");

        Packet packet = new Packet();

        Command command = new Command();
        command.commandType = CommandType.REQUEST_ALL_GAMES;
        command.content = packet.toJSON();

        out.println(command.toJSON());

        String receivedGames = null;
        try{ receivedGames = in.readLine();}
        catch(IOException e ){ System.out.println("Error");}

        //Parsowanie
        ArrayList<GameInfo> availableGames = new ArrayList<>();
        String[] parts = receivedGames.split(" ");

        for(int i=0; i/5<parts.length/5; i+=5)
        {
            GameInfo gameInfo = new GameInfo();
            gameInfo.id = Integer.parseInt(parts[i]);
            gameInfo.name = parts[i+1];
            gameInfo.numberOfBots = Integer.parseInt(parts[i+2]);
            gameInfo.currentNumberOfPlayers = Integer.parseInt(parts[i+3]);
            gameInfo.maxNumberOfPlayers = Integer.parseInt(parts[i+4]);
            availableGames.add(gameInfo);
        }
        return availableGames;
    }
}

