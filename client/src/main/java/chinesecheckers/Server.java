package chinesecheckers;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private Board board;
    private boolean isMyMove;
    private ArrayList<GameInfo> listOfgames = new ArrayList<GameInfo>();
    private GameVariation gameVariation;

    private BufferedReader in;
    private PrintWriter out;

    //TODO: funkcja connect()
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

    }


    //TODO: funkcja requestnewGame()
    public void requestNewGame(String name, int numberOfBots, String username) {
        out.println("-1");
        System.out.print("Requested a new game\n");
        out.println(name);
        out.println(numberOfBots);
        out.println(username);

    }

    //TODO: funkcja uploadBoard
    public void uploadBoard(Board board) {

    }

    void joinGame(int id, String username)
    {
        out.println(id);
        out.println(username);
    }

    //TODO: funkcja downloadBoardState()
    public void downloadBoardState() {
        //board = ?;
        //myMove = ?
    }

    //You have to send something when you connect to get response from the server
    public void sendEmptyString()
    {
        out.println("9999999");
    }

    //If you want to make sure that you are running in a seperate thread on the server
    public String getEmptyString()
    {
        out.println("9999999");
        while(true)
        {
            try{ return(in.readLine());}
            catch(IOException e ) { System.out.print("Error");}
        }
    }

    public Board getboard() {
        return board;
    }

    public boolean isMymove() {
        return isMyMove;
    }

    //Returns an arrays of IDs
    public ArrayList<GameInfo> downloadAllGames(){
        System.out.print("DownloadingAllgames\n");
        out.println("-2");
        String receivedGames = null;

        try{ receivedGames = in.readLine();}
        catch(IOException e ){ System.out.print("Error");}
        ArrayList<GameInfo> availableGames = new ArrayList<>();
        String[] parts = receivedGames.split(" ");

        System.out.print(receivedGames+"\n");

        for(int i=0; i/4<parts.length/4; i+=4)
        {
            GameInfo gameInfo = new GameInfo();
            gameInfo.id = Integer.parseInt(parts[i]);
            gameInfo.name = parts[i+1];
            gameInfo.numberOfBots = Integer.parseInt(parts[i+2]);
            gameInfo.currentNumberOfPlayers = Integer.parseInt(parts[i+3]);
            availableGames.add(gameInfo);

        }
        return availableGames;
    }

    //Unnecessary for now
    public ArrayList<GameInfo> getAllGames() {
        return listOfgames;
    }

    //TODO: funkcja downloadGameVariation();
    public void downloadGameVariation()
    {
        //gameVariation = ?;
    }

    public GameVariation getGameVariation() {
        return gameVariation;
    }
}

