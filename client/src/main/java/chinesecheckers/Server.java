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

    //TODO: funkcja downloadBoardState()
    public void downloadBoardState() {
        //board = ?;
        //myMove = ?
    }

    public void sendEmptyString()
    {
        out.println("9999999");
    }
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

    //TODO: funkcja downloadAllGames()
    public String downloadAllGames(){
        System.out.print("DownloadingAllgames\n");
        out.println("-2");
        while(true)
        {
            try{ return(in.readLine());}
            catch(IOException e ) { System.out.print("Error");}
        }
    }

    public ArrayList<GameInfo> getAllGames() {
        return listOfgames;
    }

    //TODO: funkcja join();
    public void join(String username) {

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

