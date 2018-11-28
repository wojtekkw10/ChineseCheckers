package chinesecheckers;

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
                    String command = input.readLine();
                    if(command.equals("9999999"))
                    {
                        output.println("Received info from game");
                    }
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
