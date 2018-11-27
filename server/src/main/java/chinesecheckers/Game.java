package chinesecheckers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static java.rmi.server.LogStream.log;

public class Game{
    //Client client = new Client();
    ArrayList<SimpleBot> bots = new ArrayList<SimpleBot>();
    ArrayList<Player> players = new ArrayList<>();

    public void addPlayer(Player player)
    {
        players.add(player);
    }

    public class Player extends Thread {
        Color color;

        private Socket socket;
        private int clientNumber;
        BufferedReader input;
        private PrintWriter output;

        public Player(Socket socket, int clientNumber) {
            this.socket = socket;
            this.clientNumber = clientNumber;
            System.out.print("New connection with client# " + clientNumber + " at " + socket);

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
                // The thread is only started after everyone connects.
                output.println("MESSAGE All players connected");

                // Repeatedly get commands from the client and process them.
                while (true) {
                    //if(cos) sendAllgames();
                    String command = input.readLine();
                    if (command.startsWith("MOVE")) {
                        int location = Integer.parseInt(command.substring(5));
                        //if (legalMove(location, this)) {
                        //    output.println("VALID_MOVE");
                        //    output.println(hasWinner() ? "VICTORY"
                        //            : boardFilledUp() ? "TIE"
                        //            : "");
                        //} else {
                        //    output.println("MESSAGE ?");
                        // }
                    } else if (command.startsWith("QUIT")) {
                        return;
                    }

                }
            } catch (IOException e) {
                System.out.println("Player died: " + e);
            } finally {
                try {socket.close();} catch (IOException e) {}
            }
        }
    }
}
