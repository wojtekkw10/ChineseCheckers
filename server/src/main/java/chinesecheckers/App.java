package chinesecheckers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class App
{
    public static void main( String[] args ) {

        ArrayList<Game> listOfGames = new ArrayList<Game>();

        System.out.println("The Chinese Checkers server is running.");
        int clientNumber = 0;
        ServerSocket listener = null;
        try {
            listener = new ServerSocket(9898);
        } catch (IOException e) {
            System.out.print("Cannot create socket");
        }

        Game defaultGame = new Game();
        //default game jako soobna klasa ktora dzidziczy po game - zeby ladnie wygladalo

        try {
            while (true) {
                //chyba tak zrobimy:
                System.out.print("Waiting for player...\n");
                Socket newPlayer = listener.accept();
                System.out.print("Client connected\n");
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(newPlayer.getInputStream()));
                System.out.print("Downloading request\n");
                String line = in.readLine();
                System.out.print("Downloaded request\n");
                PrintWriter output = new PrintWriter(newPlayer.getOutputStream(), true);


                int ID = Integer.parseInt(line);
                System.out.print("Input: "+ID+"\n");
                if(ID==9999999) {System.out.print("Received empty string\n");}
                else if(ID==-2)
                {
                    System.out.print("AllGamesRequested");
                    output.println("ID1 ID2 ID3 ID4");
                    newPlayer.close();
                    //musimy zamknac bo nie mamy osobnego watku dla niego
                }
                else if(ID==-1) {
                    System.out.print("New Game Requested");
                    listOfGames.add(new Game());
                    listOfGames.get(listOfGames.size()-1).addPlayer(defaultGame.new Player(newPlayer, clientNumber));
                    clientNumber++;
                    System.out.print("Number of games: "+listOfGames.size()+"\n");
                }
                else {
                    listOfGames.get(ID).addPlayer(defaultGame.new Player(newPlayer, clientNumber));
                    clientNumber++;
                }


                /*
                Game game = new Game();
                Game.Player player1 = game.new Player(listener.accept(), 1, Color.green);
                game.addPlayer(player1);
                //Game.Player playerO = game.new Player(listener.accept(), 2);
                //playerX.setOpponent(playerO);
                //playerO.setOpponent(playerX);
                //game.currentPlayer = playerX;
                player1.start();
                //playerO.start();
                */
            }
        } catch (IOException ex) {
            System.out.print("SERVER: ERROR: couldn't connect");
        } finally {
            try {
                listener.close();
            } catch (IOException e) {
                System.out.print("SERVER: ERROR: couldn't close the connection");
            }
        }
    }

}
