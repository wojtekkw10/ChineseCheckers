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
    public void requestNewGame() {
        out.println("-1");

    }

    //TODO: funkcja uploadBoard
    public void uploadBoard(Board board) {

    }

    void joinGame(int id)
    {
        out.println(id);
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
    public ArrayList<Id> downloadAllGames(){
        System.out.print("DownloadingAllgames\n");
        out.println("-2");
        String receivedGames = null;

        try{ receivedGames = in.readLine();}
        catch(IOException e ){ System.out.print("Error");}
        ArrayList<Id> availableGameIDs = new ArrayList<>();
        String[] parts = receivedGames.split(" ");
        for(int i=0; i<parts.length; i++)
        {
            availableGameIDs.add(new Id(Integer.parseInt(parts[i])));
        }
        return availableGameIDs;
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

