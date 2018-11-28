package chinesecheckers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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

        Game defaultGame = new Game("Default", 0);
        //default game jako soobna klasa ktora dzidziczy po game - zeby ladnie wygladalo

        try {
            while (true) {
                //chyba tak zrobimy:
                System.out.print("Waiting for player...\n");
                Socket newPlayerSocket = listener.accept();
                System.out.print("Client connected\n");
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(newPlayerSocket.getInputStream()));
                System.out.print("Downloading request\n");
                String line = in.readLine();
                System.out.print("Downloaded request\n");
                PrintWriter output = new PrintWriter(newPlayerSocket.getOutputStream(), true);


                int ID = Integer.parseInt(line);
                System.out.print("Input: "+ID+"\n");
                if(ID==9999999) {System.out.print("Received empty string\n");}
                else if(ID==-2)
                {
                    System.out.print("AllGamesRequested");
                    StringBuffer gameList = new StringBuffer();
                    for(int i=0; i<listOfGames.size(); i++)
                    {
                        gameList.append(i);
                        gameList.append(" ");
                        gameList.append(listOfGames.get(i).name);
                        gameList.append(" ");
                        gameList.append(listOfGames.get(i).numberOfBots);
                        gameList.append(" ");
                        gameList.append(listOfGames.get(i).getNumberOfPlayers());
                        gameList.append(" ");
                    }
                    output.println(gameList.toString());
                    newPlayerSocket.close();
                    //musimy zamknac bo nie mamy osobnego watku dla niego
                }
                else if(ID==-1) {
                    System.out.print("New Game Requested");
                    String name = in.readLine();
                    String numberOfBotsString = in.readLine();
                    int numberOfBots = Integer.parseInt(numberOfBotsString);
                    String username = in.readLine();

                    listOfGames.add(new Game(name, numberOfBots));
                    listOfGames.get(listOfGames.size()-1).addPlayer(defaultGame.new Player(newPlayerSocket, username));
                    System.out.print("New game has been added. Name: "+name+" Number of Bots: "+numberOfBots+"\n");
                    System.out.print("New Player: "+username+"\n");
                    System.out.print("Number of games: "+listOfGames.size()+"\n");
                    //clientNumber++;
                }
                else {
                    String username = in.readLine();
                    listOfGames.get(ID).addPlayer(defaultGame.new Player(newPlayerSocket, username));
                    System.out.print("New Player: "+username+"\n");
                    System.out.print("Number OF Players in the Game: "+listOfGames.get(ID).getNumberOfPlayers()+"\n");
                    //clientNumber++;
                }
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
